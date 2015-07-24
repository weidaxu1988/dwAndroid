package com.xuweida.daweilibrary.adapters;

import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Dawei on 2015/2/24.
 */
public class ImagePageHostAdapter extends PagerAdapter {

    SparseArray<ImageView> mImages;
    Resources mRes;

    public ImagePageHostAdapter(Resources res, SparseArray<ImageView> imgs) {
        super();
        mRes = res;

        if (imgs != null)
            mImages = imgs;
        else
            mImages = new SparseArray<>();
    }

    public ImageView getItem(int position) {

        if (mImages.indexOfKey(position) >= 0)
            return mImages.get(position);

        return null;
    }

    @Override
    public int getCount() {
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mImages.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mImages.get(position));
        return mImages.get(position);
    }

//    @Override
//    public int getIconResId(int i) {
//        return getItem(i).getIconResourceId();
//    }
//
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mRes.getString(getItem(position).getTitleResourceId());
//    }

}