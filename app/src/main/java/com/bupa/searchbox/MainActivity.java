package com.bupa.searchbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import static com.bupa.searchbox.app.MyApplication.getAppInstance;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener, IOnSearchClickListener {

    private Toolbar mToolbar;
    private TextView mSearchInfo;
    private SearchFragment mSearchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mToolbar.setTitle("SearchDialog");//标题
        setSupportActionBar(mToolbar);
        mToolbar.setOnMenuItemClickListener(this);
        mSearchFragment = getAppInstance().getSearchFragment();
        mSearchFragment.setOnSearchClickListener(this);
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mSearchInfo = (TextView) findViewById(R.id.search_info);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载菜单文件
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search://点击搜索
                mSearchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
                break;
        }
        return true;
    }

    @Override
    public void OnSearchClick(String keyword) {
        mSearchInfo.setText(keyword);
    }
}
