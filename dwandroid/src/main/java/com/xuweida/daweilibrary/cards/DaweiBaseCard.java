package com.xuweida.daweilibrary.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xuweida.daweilibrary.R;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by Dawei on 2015/3/8.
 */
public class DaweiBaseCard extends Card {

    TextView mTitleView, mSubTitleView, mContentView;
//    String mSubTitle, mContent;

    public DaweiBaseCard(Context context) {
        this(context, R.layout.inner_card_simple);
    }

    public DaweiBaseCard(Context context, int innerLayout) {
        super(context, innerLayout);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        mTitleView = (TextView) view.findViewById(R.id.txt_title);
        mSubTitleView = (TextView) view.findViewById(R.id.txt_sub_title);
        mContentView = (TextView) view.findViewById(R.id.txt_content);

        if (mTitleView != null && mTitle != null && !mTitle.isEmpty()) {
            mTitleView.setText(mTitle);
        }
    }

    public void setSubTitle(String title) {
        //mSubTitle = title;
        if (mSubTitleView != null && title != null && !title.isEmpty()) {
            mSubTitleView.setText(title);
        }
    }

    public void setContent(String content) {
        if (mContentView != null && content != null && !content.isEmpty()) {
            mContentView.setText(content);
        }
    }
}
