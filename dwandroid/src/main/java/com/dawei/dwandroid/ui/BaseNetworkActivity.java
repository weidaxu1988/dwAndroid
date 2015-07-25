package com.dawei.dwandroid.ui;

import android.accounts.Account;

import com.dawei.dwandroid.util.AccountUtils;
import com.dawei.dwandroid.util.LoginAndAuthHelper;

import static com.dawei.dwandroid.util.LogUtils.LOGD;
import static com.dawei.dwandroid.util.LogUtils.makeLogTag;

/**
 * A base activity based on iosched that handles common functionality in the app.
 * This includes the login and authentication, Action Bar tweaks, amongst others.
 */
public abstract class BaseNetworkActivity extends BaseActivity implements LoginAndAuthHelper.Callbacks {
    private static final String TAG = makeLogTag(BaseNetworkActivity.class);

    // the LoginAndAuthHelper handles signing in to Google Play Services and OAuth
    private LoginAndAuthHelper mLoginAndAuthHelper;

    @Override
    protected void onResume() {
        super.onResume();

        // Verifies services or add listeners

        // Watch for sync state changes
        // Add later
    }

    @Override
    protected void onPause() {
        super.onPause();

        // remove services ori listeners
        // add later
    }

    @Override
    public void onStart() {
        LOGD(TAG, "onStart");
        super.onStart();

        startLoginProcess();
    }

    private void startLoginProcess() {
        LOGD(TAG, "Starting Login process.");
        if (AccountUtils.hasActiveAccount(this)) {
            String accountName = AccountUtils.getActiveAccountName(this);
            LOGD(TAG, "Chosen account: " + AccountUtils.getActiveAccountName(this));

            if (mLoginAndAuthHelper != null && mLoginAndAuthHelper.getAccountName().equals(accountName)) {
                LOGD(TAG, "Helper already set up; simply starting it.");
                mLoginAndAuthHelper.start();
                return;
            }

            LOGD(TAG, "Starting login process with account " + accountName);

            if (mLoginAndAuthHelper != null) {
                LOGD(TAG, "Tearing down old Helper, was " + mLoginAndAuthHelper.getAccountName());
                if (mLoginAndAuthHelper.isStarted()) {
                    LOGD(TAG, "Stopping old Helper");
                    mLoginAndAuthHelper.stop();
                }
                mLoginAndAuthHelper = null;
            }

            LOGD(TAG, "Creating and starting new Helper with account: " + accountName);
            mLoginAndAuthHelper = new LoginAndAuthHelper(this, this, accountName);
            mLoginAndAuthHelper.start();
        } else {
            LOGD(TAG, "No active account, back to Login Activity.");

        }
        // add code to back login activity
    }

    /**
     * Called when authentication succeeds. This may either happen because the user just
     * authenticated for the first time (and went through the sign in flow), or because it's
     * a returning user.
     * @param accountName name of the account that just authenticated successfully.
     * @param newlyAuthenticated If true, this user just authenticated for the first time.
     * If false, it's a returning user.
     */
    @Override
    public void onAuthSuccess(String accountName, boolean newlyAuthenticated) {
//        Account account = new Account(accountName, GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
//        LOGD(TAG, "onAuthSuccess, account " + accountName + ", newlyAuthenticated=" + newlyAuthenticated);
//
//        refreshAccountDependantData();
//
//        if (newlyAuthenticated) {
//            LOGD(TAG, "Enabling auto sync on content provider for account " + accountName);
//            SyncHelper.updateSyncInterval(this, account);
//            SyncHelper.requestManualSync(account);
//        }
//
//        setupAccountBox();
//        populateNavDrawer();
//        registerGCMClient();
    }

    @Override
    public void onAuthFailure(String accountName) {
        LOGD(TAG, "Auth failed for account " + accountName);
//        refreshAccountDependantData();
    }
}
