package com.xuweida.daweilibrary.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import it.gmariotti.cardslib.library.internal.CardThumbnail;

/**
 * Created by wdxu on 3/14/2015.
 */
public class PicassoCardThumbnail extends CardThumbnail {

    public PicassoCardThumbnail(Context context) {
        super(context);
        setExternalUsage(true);
    }

    public PicassoCardThumbnail(Context context, int innerLayout) {
        super(context, innerLayout);
        setExternalUsage(true);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View imageView) {
        ImageView thumbnailImage = (ImageView) imageView;
        if (getDrawableResource() > 0) {
            Picasso.with(getContext()).load(getDrawableResource()).into(thumbnailImage);
        } else if (getUrlResource() != null && !getUrlResource().isEmpty()) {
            Picasso.with(getContext()).load(getUrlResource()).error(android.R.drawable.stat_notify_error).into(thumbnailImage);
        }
    }
}
