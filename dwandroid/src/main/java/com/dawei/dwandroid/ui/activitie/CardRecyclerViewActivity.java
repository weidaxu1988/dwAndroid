package com.dawei.dwandroid.ui.activitie;

import android.support.v4.app.FragmentManager;

import com.dawei.dwandroid.R;
import com.dawei.dwandroid.ui.fragment.recyclerview.RecyclerViewFragment;

/**
 * Created by Weida on 5/20/2015.
 */
public class CardRecyclerViewActivity extends SingleBackActivity {
    @Override
    protected void setupContent() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container_content, getRecyclerViewFragment()).commit();
    }

    public RecyclerViewFragment getRecyclerViewFragment() {
        return new RecyclerViewFragment();
    }
}
