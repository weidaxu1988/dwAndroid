package com.dawei.dwandroid.util;

import android.app.Activity;
import android.content.Context;

import java.lang.ref.WeakReference;

import static com.dawei.dwandroid.util.LogUtils.LOGD;
import static com.dawei.dwandroid.util.LogUtils.LOGW;
import static com.dawei.dwandroid.util.LogUtils.makeLogTag;

/**
 * This helper handles the UI flow for signing in and authenticating an account based on iosched. It handles
 * connecting to the Google+ API to fetch profile data (name, cover photo, etc) and
 * also getting the auth token for the necessary scopes. The life of this object is
 * tied to an Activity. Do not attempt to share it across Activities, as unhappiness will
 * result.
 */
public class LoginAndAuthHelper {
    private static final String TAG = makeLogTag(LoginAndAuthHelper.class);

    Context mAppContext;


    // Name of the account to log in as (e.g. "foo@example.com")
    String mAccountName;

    // The Activity this object is bound to (we use a weak ref to avoid context leaks)
    WeakReference<Activity> mActivityRef;

    // Callbacks interface we invoke to notify the user of this class of useful events
    WeakReference<Callbacks> mCallbacksRef;

    // Are we in the started state? Started state is between onStart and onStop.
    boolean mStarted = false;

    // True if we are currently showing UIs to resolve a connection error.
    boolean mResolving = false;

    public interface Callbacks {
        void onAuthSuccess(String accountName, boolean newlyAuthenticated);
        void onAuthFailure(String accountName);
    }

    public LoginAndAuthHelper(Activity activity, Callbacks callbacks, String accountName) {
        LOGD(TAG, "Helper created. Account: " + mAccountName);
        mActivityRef = new WeakReference<>(activity);
        mCallbacksRef = new WeakReference<>(callbacks);
        mAppContext = activity.getApplicationContext();
        mAccountName = accountName;
    }

    public boolean isStarted() {
        return mStarted;
    }

    public String getAccountName() {
        return mAccountName;
    }

    private Activity getActivity(String methodName) {
        Activity activity = mActivityRef.get();
        if (activity == null) {
            LOGD(TAG, "Helper lost Activity reference, ignoring (" + methodName + ")");
        }
        return activity;
    }

    /**
     * Starts the helper. Call this from your Activity's onStart().
     */
    public void start() {
        Activity activity = getActivity("start()");
        if (activity == null) {
            return;
        }

        if (mStarted) {
            LOGW(TAG, "Helper already started. Ignoring redundant call.");
            return;
        }

        mStarted = true;
        if (mResolving) {
            // if resolving, don't reconnect the plus client
            LOGD(TAG, "Helper ignoring signal to start because we're resolving a failure.");
            return;
        }

        LOGD(TAG, "Helper starting. Connecting " + mAccountName);

        // try to login and authenticate, if we don't have a token yet
        if (!AccountUtils.hasToken(activity, mAccountName)) {
            LOGD(TAG, "We don't have auth token for " + mAccountName + " yet, so getting it.");
//            mTokenTask = new GetTokenTask();
//            mTokenTask.execute();
        }else {
            LOGD(TAG, "No need for auth token, we already have it.");
            reportAuthSuccess(false);
        }
    }

    /**
     * Stop the helper. Call this from your Activity's onStop().
     */
    public void stop() {
        if (!mStarted) {
            LOGW(TAG, "Helper already stopped. Ignoring redundant call.");
            return;
        }

        LOGD(TAG, "Helper stopping.");
//        if (mTokenTask != null) {
//            LOGD(TAG, "Helper cancelling token task.");
//            mTokenTask.cancel(false);
//        }
        mStarted = false;
        mResolving = false;
    }

    private void reportAuthSuccess(boolean newlyAuthenticated) {
        LOGD(TAG, "Auth success for account " + mAccountName + ", newlyAuthenticated=" + newlyAuthenticated);
        Callbacks callbacks;
        if (null != (callbacks = mCallbacksRef.get())) {
            callbacks.onAuthSuccess(mAccountName, newlyAuthenticated);
        }
    }

    private void reportAuthFailure() {
        LOGD(TAG, "Auth FAILURE for account " + mAccountName);
        Callbacks callbacks;
        if (null != (callbacks = mCallbacksRef.get())) {
            callbacks.onAuthFailure(mAccountName);
        }
    }
}
