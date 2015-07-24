package com.xuweida.daweilibrary.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xuweida.daweilibrary.dataitems.BaseDataItem;
import com.xuweida.daweilibrary.R;

/**
 * Created by wdxu on 2/12/2014.
 */
public class EditableView extends LinearLayout {

    private TextView mTitleView;
    private TextView mContentView;

    private BaseDataItem mItem;

    public EditableView(Context context) {
        this(context, null);
    }

    public EditableView(Context context, AttributeSet attrs) {
        super (context, attrs);

        LayoutInflater.from(context)
                .inflate(R.layout.widget_editable, this, true);

        mTitleView = (TextView) findViewById(R.id.title);
        mContentView = (TextView) findViewById(R.id.content);

        setClickable(true);
        setFocusable(true);
    }

    public void setTitle(int id) {
        mTitleView.setText(id);
    }


    public void setContent(String s) {
        mContentView.setText(s);
    }

    public BaseDataItem getItem() {
        return mItem;
    }

}
