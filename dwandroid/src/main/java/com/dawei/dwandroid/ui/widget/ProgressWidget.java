package com.dawei.dwandroid.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dawei.dwandroid.R;

/**
 * Created by wdxu on 17/12/2014.
 */
public class ProgressWidget extends FrameLayout {

    private final ProgressBar mBar;
    private final TextView mText;

    public ProgressWidget(Context context) {
        this(context, null);
    }

    public ProgressWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressWidget(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.widget_progress_bar, this);
        if (isInEditMode()) {
            mBar = null;
            mText = null;
            return;
        }
        mBar = (ProgressBar) findViewById(R.id.progress_cycle);
        mText = (TextView) findViewById(R.id.txt_progress);
        setIndeterminate(true);
        setMax(100);
        setProgress(0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return false;
    }

    public void setProgress(int progress) {
        mBar.setProgress(progress);
        updateProgress();
    }

    public void setMax(int max) {
        mBar.setMax(max);
        updateProgress();
    }

    public void setIndeterminate(boolean indeterminate) {
        mBar.setIndeterminate(indeterminate);
        updateProgress();
    }

    private void updateProgress() {
        if (mBar.isIndeterminate()) {
            mText.setText(R.string.txt_wait);
        } else {
            mText.setText(Math.round(mBar.getProgress() * 100.0 / mBar.getMax())
                    + "%");
        }
    }
}