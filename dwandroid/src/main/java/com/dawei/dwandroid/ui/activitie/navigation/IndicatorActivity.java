package com.dawei.dwandroid.ui.activitie.navigation;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.dawei.dwandroid.R;
import com.dawei.dwandroid.adapter.FragmentPageHostAdapter;
import com.dawei.dwandroid.ui.activitie.HostActivity;
import com.viewpagerindicator.PageIndicator;

/**
 * Created by Dawei on 2015/2/13.
 */
public class IndicatorActivity extends HostActivity {
    protected ViewPager mPager;
    protected PageIndicator mIndicator;

    protected int mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeLayout();
        setContentView(mLayout);

        mFragmentAdapter = new FragmentPageHostAdapter(getSupportFragmentManager(),
                getResources(), getAdapterData());

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mFragmentAdapter);

        mIndicator = (PageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }

    protected void initializeLayout() {
        mLayout = R.layout.activity_indicator;
    }
}
