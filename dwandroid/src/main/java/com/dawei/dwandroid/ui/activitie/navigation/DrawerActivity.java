package com.dawei.dwandroid.ui.activitie.navigation;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.dawei.dwandroid.R;
import com.dawei.dwandroid.ui.activitie.HostActivity;
import com.dawei.dwandroid.ui.fragment.PlaceHolderFragment;

public class DrawerActivity extends HostActivity {
    protected DrawerLayout mDrawerLayout;
    protected ListView mDrawer;

    protected ActionBarDrawerToggle mDrawerToggle;
    protected ActionBarHelper mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        restoreActionBar();
        setupDrawer();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void restoreActionBar() {
        mActionBar = createActionBarHelper();
        mActionBar.init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    protected void selectItem(int position) {
        mDrawer.setItemChecked(position, true);

        PlaceHolderFragment frgmt = mFragmentAdapter.getItem(position);
        mActionBar.setTitle(getString(frgmt.getTitleResourceId()));

        if (mDrawerLayout.isDrawerOpen(mDrawer))
            mDrawerLayout.closeDrawer(mDrawer);
        else
            mActionBar.onDrawerClosed();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, frgmt).commit();
    }

    protected ListAdapter createDrawerAdapter() {
        return new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                new String[]{"Place Holder 1", "Place Holder 2", "Place Holder 3", "Place Holder 4"});
    }

    protected PlaceHolderFragment getCurrentFragment() {
        return mFragmentAdapter.getItem(mDrawer.getCheckedItemPosition());
    }

    private void setupDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer = (ListView) findViewById(R.id.drawer);

        mDrawerLayout.setDrawerListener(new SimpleDrawerListener());
        mDrawerLayout.setDrawerTitle(GravityCompat.START, getString(R.string.app_name));

        mDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
        mDrawer.setAdapter(createDrawerAdapter());

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close);
    }

    private ActionBarHelper createActionBarHelper() {
        return new ActionBarHelper();
    }

    private class SimpleDrawerListener implements DrawerLayout.DrawerListener {
        @Override
        public void onDrawerOpened(View drawerView) {
            mDrawerToggle.onDrawerOpened(drawerView);
            mActionBar.onDrawerOpened();
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            mDrawerToggle.onDrawerClosed(drawerView);
            mActionBar.onDrawerClosed();
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            mDrawerToggle.onDrawerStateChanged(newState);
        }
    }

    private class ActionBarHelper {
        private final ActionBar mActionBar;
        private CharSequence mDrawerTitle;
        private CharSequence mTitle;

        ActionBarHelper() {
            mActionBar = getSupportActionBar();
        }

        public void init() {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mActionBar.setDisplayShowHomeEnabled(false);
            mTitle = mDrawerTitle = getTitle();
        }

        /**
         * When the drawer is closed we restore the action bar state reflecting
         * the specific contents in view.
         */
        public void onDrawerClosed() {
            mActionBar.setTitle(mTitle);
        }

        /**
         * When the drawer is open we set the action bar to a generic title.
         * The action bar should only contain data relevant at the top level of
         * the nav hierarchy represented by the drawer, as the rest of your content
         * will be dimmed down and non-interactive.
         */
        public void onDrawerOpened() {
            mActionBar.setTitle(mDrawerTitle);
        }

        public void setTitle(CharSequence title) {
            mTitle = title;
        }
    }
}
