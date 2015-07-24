package com.xuweida.daweilibrary.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.xuweida.daweilibrary.dataitems.BaseDataItem;
import com.xuweida.daweilibrary.R;

/**
 * Created by wdxu on 2/12/2014.
 */
public class ItemActivity extends AppCompatActivity {

    protected BaseDataItem mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getActivityLayout());

        restoreDataItem();
        restoreActionBar();

        setupContent();
    }

    public void restoreActionBar() {
        final ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);

        if (mItem != null)
            bar.setTitle(mItem.getTitle());
    }

    protected void restoreDataItem() {
        mItem = (BaseDataItem) getIntent().getSerializableExtra(
                BaseDataItem.KEY_ITEM_DATA);
    }

    protected void setupContent() {

    }

    public int getActivityLayout() {
        return R.layout.layout_base;
    }
}
