package com.dawei.dwandroid.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawei.dwandroid.R;

public class PlaceHolderFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupContent();
    }

    protected void setupContent() {
    }

    public int getIconResourceId() {

        return R.drawable.ic_launcher;
    }

    public int getTitleResourceId() {
        return R.string.title_place_holder;
    }

    public int getFragmentLayout() {
        return R.layout.layout_base;
    }
}
