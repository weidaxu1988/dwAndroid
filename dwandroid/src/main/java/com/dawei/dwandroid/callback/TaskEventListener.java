package com.dawei.dwandroid.callback;

/**
 * Created by wdxu on 9/12/2014.
 */
public interface TaskEventListener {
    void updateMessage(String msg);

    void onStepFail(String msg);

    void onStepFinished();
}
