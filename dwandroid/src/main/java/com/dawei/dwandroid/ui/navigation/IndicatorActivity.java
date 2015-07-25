package com.dawei.dwandroid.ui.navigation;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.dawei.dwandroid.R;
import com.dawei.dwandroid.ui.BaseNavActivity;
import com.viewpagerindicator.PageIndicator;

/**
 * Created by Dawei on 2015/2/13.
 */
public class IndicatorActivity extends BaseNavActivity {
    protected ViewPager mPager;
    protected PageIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);
    }

    @Override
    protected void trySetupNavigation() {
        super.trySetupNavigation();

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mFragmentAdapter);

        mIndicator = (PageIndicator) findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }
}
