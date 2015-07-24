package com.xuweida.daweilibrary.dataitems;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wdxu on 1/12/2014.
 */
public class BaseDataItem implements Serializable {
    public final static String KEY_ITEM_DATA = "bass_data_item";

    protected String title, content;

    protected String itemId;

    public BaseDataItem() {
    }

    public BaseDataItem(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return new Date();
    }

    public void setItemId(String id) {
        itemId = id;
    }

    public String getItemId() {
        return itemId;
    }
}
