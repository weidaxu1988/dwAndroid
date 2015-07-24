package com.xuweida.daweilibrary.activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

import com.xuweida.daweilibrary.R;
import com.xuweida.daweilibrary.adapters.FragmentPageHostAdapter;
import com.xuweida.daweilibrary.fragments.PlaceHolderFragment;

/**
 * Base Activity for Fragments Host.
 */
public class HostActivity extends AppCompatActivity {
    protected FragmentPageHostAdapter mFragmentAdapter;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        mFragmentAdapter = new FragmentPageHostAdapter(getSupportFragmentManager(),
                getResources(), getAdapterData());
    }

    public void restoreActionBar() {

    }

    protected SparseArray getAdapterData() {
        SparseArray<PlaceHolderFragment> fs = new SparseArray<>();
        fs.put(0, new PlaceHolderFragment());
        fs.put(1, new PlaceHolderFragment());
        fs.put(2, new PlaceHolderFragment());
        fs.put(3, new PlaceHolderFragment());
        return fs;
    }

    protected void selectFragmentItem(int position) {
        PlaceHolderFragment frgmt = mFragmentAdapter.getItem(position);

        setTitle(getString(frgmt.getTitleResourceId()));

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, frgmt).setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
    }
}
