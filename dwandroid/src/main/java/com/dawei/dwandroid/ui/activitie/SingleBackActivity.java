package com.dawei.dwandroid.ui.activitie;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;

import com.dawei.dwandroid.R;
import com.dawei.dwandroid.model.BaseDataItem;
import com.dawei.dwandroid.ui.BaseActivity;
import com.dawei.dwandroid.util.AppHelper;

/**
 * Created by Dawei on 2015/3/8.
 */
public class SingleBackActivity extends BaseActivity {

    protected BaseDataItem mItem;

    private View mContainerProgressView, mContainerContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());

        setupContent();
    }

    @Override
    protected void setupActionBar() {
        mItem = getDataItem();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            if (mItem != null) {
                ab.setTitle(mItem.getTitle());
            }
        }
    }

    @Override
    protected void setupContent() {
        mContainerContentView = findViewById(R.id.main_content);
        mContainerProgressView = findViewById(R.id.container_progress);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        AppHelper.showProgress(show, mContainerProgressView, mContainerContentView, shortAnimTime);
    }

    private BaseDataItem getDataItem() {
        return (BaseDataItem) getIntent().getSerializableExtra(
                BaseDataItem.KEY_ITEM_DATA);
    }

    protected int getActivityLayout() {
        return R.layout.layout_base;
    }
}
