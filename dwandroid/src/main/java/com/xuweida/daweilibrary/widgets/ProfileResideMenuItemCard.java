package com.xuweida.daweilibrary.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.special.ResideMenu.ResideMenuItem;
import com.squareup.picasso.Picasso;
import com.xuweida.daweilibrary.R;

/**
 * Created by Weida on 5/9/2015.
 */
public class ProfileResideMenuItemCard extends ResideMenuItem {

    final private Context mContext;

    private ImageView mIcon;
    private TextView mTitle, mSubTitle;

    public ProfileResideMenuItemCard(Context context) {
        super(context);
        mContext = context;
        initViews(context);
    }

    private void initViews(Context context) {
        this.removeAllViews();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.widget_profile_item_card, this);
        mIcon = (ImageView) findViewById(R.id.img_icon);
        mTitle = (TextView) findViewById(R.id.txt_title);
        mSubTitle = (TextView) findViewById(R.id.txt_sub_title);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setSubTitle(String title) {
        mSubTitle.setText(title);
    }

    public void setIcon(String url) {
        if (url != null && !url.isEmpty())
            Picasso.with(mContext).load(url).error(R.drawable.ic_account_circle_grey600_48dp).into(mIcon);
    }

    public void setIcon(int icon) {
        Picasso.with(mContext).load(icon).into(mIcon);
    }
}
