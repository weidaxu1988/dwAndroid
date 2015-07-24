package com.xuweida.daweilibrary.activities.navigation;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;

import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;
import com.xuweida.daweilibrary.R;
import com.xuweida.daweilibrary.activities.HostActivity;

/**
 * Created by Weida on 5/9/2015.
 */
public class ResideMenuActivity extends HostActivity implements ResideMenu.OnMenuListener {

    public static final float MENU_SCALE = 0.6f;

    private ResideMenu mResideMenu;

    private SparseArray<ResideMenuItem> mResideMenuItemArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reside_menu);
        restoreActionBar();

        setupMenu();
        if (savedInstanceState == null)
            selectResideMenuItem(0);
    }

    @Override
    public void restoreActionBar() {
        final ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == android.R.id.home) {
            mResideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return mResideMenu.dispatchTouchEvent(ev);
//    }

    @Override
    public void openMenu() {
//        Toast.makeText(ResideMenuActivity.this, "Menu is opened!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeMenu() {
//        Toast.makeText(ResideMenuActivity.this, "Menu is closed!", Toast.LENGTH_SHORT).show();
    }

    public void setResideMenuBackground(int id) {
        if (mResideMenu != null)
            mResideMenu.setBackground(id);
    }

    public void setScaleValue(float scale) {
        if (mResideMenu != null)
            mResideMenu.setScaleValue(scale);
    }

    protected void selectResideMenuItem(int position) {
        mResideMenu.clearIgnoredViewList();
        selectFragmentItem(position);
    }

    protected SparseArray<ResideMenuItem> getMenuItemData() {
        SparseArray<ResideMenuItem> itemArray = new SparseArray<>();

        return itemArray;
    }

    private void setupMenu() {
        mResideMenu = new ResideMenu(this);
        mResideMenu.setBackground(R.drawable.bg_reside_menu);
        mResideMenu.attachToActivity(this);
        mResideMenu.setMenuListener(this);
        mResideMenu.setScaleValue(MENU_SCALE);

        mResideMenuItemArray = getMenuItemData();

        for (int i = 0; i < mResideMenuItemArray.size(); i++) {
            final int position = i;
            ResideMenuItem item = mResideMenuItemArray.get(i);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectResideMenuItem(position);
                    mResideMenu.closeMenu();
                }
            });
            mResideMenu.addMenuItem(item, ResideMenu.DIRECTION_LEFT);
        }
    }
}
