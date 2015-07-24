package com.dawei.dwandroid.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import static com.dawei.dwandroid.util.LogUtils.makeLogTag;

/**
 * Account and login utilities based on iosched. This class manages a local shared preferences object
 * that stores which account is currently active, and can store associated information
 * such as customized profile info (name, image URL, cover URL) and also the auth token
 * associated with the account.
 */
public class AccountUtils {
    private static final String TAG = makeLogTag(AccountUtils.class);

    private static final String PREF_ACTIVE_ACCOUNT = "chosen_account";

    // these names are are prefixes; the account is appended to them
    private static final String PREFIX_PREF_AUTH_TOKEN = "auth_token_";
    private static final String PREFIX_PREF_PROFILE_ID = "profile_id_";
    private static final String PREFIX_PREF_NAME = "name_";
    private static final String PREFIX_PREF_IMAGE_URL = "image_url_";

    private static SharedPreferences getSharedPreferences(final Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static boolean hasActiveAccount(final Context context) {
        return !TextUtils.isEmpty(getActiveAccountName(context));
    }

    public static String getActiveAccountName(final Context context) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getString(PREF_ACTIVE_ACCOUNT, null);
    }
}
