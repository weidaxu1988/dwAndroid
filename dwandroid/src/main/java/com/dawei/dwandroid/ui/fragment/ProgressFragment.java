package com.dawei.dwandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawei.dwandroid.R;
import com.dawei.dwandroid.util.AppHelper;

/**
 * Created by Weida on 5/11/2015.
 */
public class ProgressFragment extends PlaceHolderFragment {
    private View mContainerProgressView, mContainerContentView;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mContainerContentView = view.findViewById(R.id.container_content);
        mContainerProgressView = view.findViewById(R.id.container_progress);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        showProgress(true);

        attemptUpdateContent();
    }

    public void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);
        AppHelper.showProgress(show, mContainerProgressView, mContainerContentView, shortAnimTime);
    }

    protected void attemptUpdateContent() {
        showProgress(false);
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.layout_progress_container;
    }
}
