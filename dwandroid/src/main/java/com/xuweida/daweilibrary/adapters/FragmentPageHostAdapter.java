package com.xuweida.daweilibrary.adapters;

import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.viewpagerindicator.IconPagerAdapter;
import com.xuweida.daweilibrary.fragments.PlaceHolderFragment;

public class FragmentPageHostAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

	SparseArray<PlaceHolderFragment> mFragments;
	Resources mRes;
	
	public FragmentPageHostAdapter(FragmentManager fm, Resources res, SparseArray<PlaceHolderFragment> fgs) {
		super(fm);
		mRes = res;
		mFragments = fgs;
	}
	
	@Override
	public PlaceHolderFragment getItem(int arg0) {
		
		if (mFragments.indexOfKey(arg0) >= 0)
			return mFragments.get(arg0); 
		
		return null;
	}

    @Override
    public int getIconResId(int i) {
        return getItem(i).getIconResourceId();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mRes.getString(getItem(position).getTitleResourceId());
    }

    @Override
	public int getCount() {
		return mFragments.size();
	}

}
