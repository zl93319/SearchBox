package com.bupa.searchbox.app;/**
 * Created by Administrator on 2017/4/25.
 */

import android.app.Activity;
import android.app.Application;

import com.bupa.searchbox.util.UIUtils;
import com.wyt.searchbox.SearchFragment;

import java.util.LinkedList;
import java.util.List;

/**
 * 作者：ZLei on 2017/4/25 09:20
 * 邮箱：93319@163.com
 * 备注: (该类的作用)
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;
    private List<Activity> mList;

    @Override
    public void onCreate() {
        super.onCreate();
        mList = new LinkedList<>();
        UIUtils.init(this);
    }

    /**
     * 获取MyApplication 的实例
     *
     * @return
     */
    public synchronized static MyApplication getAppInstance() {
        if (null == mInstance) {
            mInstance = new MyApplication();
        }
        return mInstance;
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    // 退出整个App 应用
    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    // 回收垃圾
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
    public SearchFragment getSearchFragment(){
        return SearchFragment.newInstance();
    }
}
