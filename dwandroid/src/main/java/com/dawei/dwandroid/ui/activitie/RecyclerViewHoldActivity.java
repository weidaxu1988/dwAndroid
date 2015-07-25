package com.dawei.dwandroid.ui.activitie;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.dawei.dwandroid.R;
import com.dawei.dwandroid.ui.fragment.recyclerview.RecyclerViewFragment;

/**
 * Created by Weida on 5/20/2015.
 */
public class RecyclerViewHoldActivity extends SingleBackActivity {
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_content, getRecyclerViewFragment()).commit();
    }

    public RecyclerViewFragment getRecyclerViewFragment() {
        return new RecyclerViewFragment();
    }
}
