package com.dawei.dwandroid.ui.activitie;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.dawei.dwandroid.R;
import com.dawei.dwandroid.util.AppHelper;

/**
 * Created by Dawei on 2015/3/8.
 */
public class SingleBackActivity extends AppCompatActivity {

    private View mContainerProgressView, mContainerContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());
        restoreActionBar();
        mContainerContentView = findViewById(R.id.main_content);
        mContainerProgressView = findViewById(R.id.container_progress);
        setupContent();
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

    public void restoreActionBar() {
        final ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
    }

    public void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        AppHelper.showProgress(show, mContainerProgressView, mContainerContentView, shortAnimTime);
    }

    protected void setupContent() {

    }

    protected int getActivityLayout() {
        return R.layout.layout_base;
    }
}
