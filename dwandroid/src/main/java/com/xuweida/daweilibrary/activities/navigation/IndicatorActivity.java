package com.xuweida.daweilibrary.activities.navigation;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.PageIndicator;
import com.xuweida.daweilibrary.R;
import com.xuweida.daweilibrary.activities.HostActivity;
import com.xuweida.daweilibrary.adapters.FragmentPageHostAdapter;

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
