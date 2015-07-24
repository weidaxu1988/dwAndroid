package com.xuweida.daweilibrary.widgets;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.special.ResideMenu.ResideMenuItem;
import com.xuweida.daweilibrary.R;

/**
 * Created by Weida on 5/11/2015.
 */
public class SimpleResideMenuItemCard extends ResideMenuItem {
    public static final float ICON_TEXT_SIZE = 16;
    public static final float SINGLE_TEXT_SIZE = 14;

    protected TextView mTitle;

    public SimpleResideMenuItemCard(Context context, int title) {
        super(context);
        View iv_icon = this.findViewById(R.id.iv_icon);
        iv_icon.setVisibility(View.GONE);

        mTitle  = (TextView)this.findViewById(R.id.tv_title);
        mTitle.setSingleLine();
        mTitle.setTextSize(SINGLE_TEXT_SIZE);
        mTitle.setText(title);
    }

    public SimpleResideMenuItemCard(Context context, int icon, int title) {
        super(context, icon, title);
        mTitle  = (TextView)this.findViewById(R.id.tv_title);
        mTitle.setSingleLine();
        mTitle.setTextSize(ICON_TEXT_SIZE);
    }
}
