package com.dawei.dwandroid.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.dawei.dwandroid.util.LogUtils.makeLogTag;

/**
 * Utilities and constants related to app preferences based on iosched.
 */
public class PrefUtils {
    /**
     * Boolean indicating whether we performed the (one-time) welcome flow.
     */
    public static final String PREF_WELCOME_DONE = "pref_welcome_done";
    private static final String TAG = makeLogTag("PrefUtils");

    public static void init(final Context context) {
        // Init Some SharedPreference
    }

    public static boolean isWelcomeDone(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(PREF_WELCOME_DONE, false);
    }

    public static void markWelcomeDone(final Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(PREF_WELCOME_DONE, true).commit();
    }

}
