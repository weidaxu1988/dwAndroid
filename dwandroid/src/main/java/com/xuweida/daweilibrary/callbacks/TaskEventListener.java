package com.xuweida.daweilibrary.callbacks;

/**
 * Created by wdxu on 9/12/2014.
 */
public interface TaskEventListener {
    public void updateMessage(String msg);
    public void onStepFail(String msg);
    public void onStepFinished();
}
