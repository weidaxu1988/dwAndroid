package com.dawei.dwandroid.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dawei.dwandroid.R;
import com.dawei.dwandroid.util.AccountUtils;
import com.dawei.dwandroid.util.LoginAndAuthHelper;
import com.dawei.dwandroid.util.PrefUtils;
import com.dawei.dwandroid.util.UIUtils;

import static com.dawei.dwandroid.util.LogUtils.LOGD;
import static com.dawei.dwandroid.util.LogUtils.LOGW;
import static com.dawei.dwandroid.util.LogUtils.makeLogTag;

/**
 * A base activity based on iosched that handles common functionality in the app.
 * This includes the login and authentication, Action Bar tweaks, amongst others.
 */
public class BaseActivity extends AppCompatActivity {
    private static final String TAG = makeLogTag(BaseActivity.class);

    // fade in and fade out durations for the main content when switching between
    // different Activities of the app through the Nav Drawer
    private static final int MAIN_CONTENT_FADEOUT_DURATION = 150;
    private static final int MAIN_CONTENT_FADEIN_DURATION = 250;

    // the LoginAndAuthHelper handles signing in to Google Play Services and OAuth
    private LoginAndAuthHelper mLoginAndAuthHelper;

    private Toolbar mActionBarToolBar;

    /**
     * Converts an intent into a {@link Bundle} suitable for use as fragment arguments.
     */
    public static Bundle intentToFragmentArguments(Intent intent) {
        Bundle arguments = new Bundle();
        if (intent == null) {
            return arguments;
        }

        final Uri data = intent.getData();
        if (data != null) {
            arguments.putParcelable("_uri", data);
        }

        final Bundle extras = intent.getExtras();
        if (extras != null) {
            arguments.putAll(intent.getExtras());
        }

        return arguments;
    }

    /**
     * Converts a fragment arguments bundle into an intent.
     */
    public static Intent fragmentArgumentsToIntent(Bundle arguments) {
        Intent intent = new Intent();
        if (arguments == null) {
            return intent;
        }

        final Uri data = arguments.getParcelable("_uri");
        if (data != null) {
            intent.setData(data);
        }

        intent.putExtras(arguments);
        intent.removeExtra("_uri");
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PrefUtils.init(this);

        // Enable or disable each Activity depending on the form factor. This is necessary
        // because this app uses many implicit intents where we don't name the exact Activity
        // in the Intent, so there should only be one enabled Activity that handles each
        // Intent in the app.
        UIUtils.enableDisableActivitiesByFormFactor(this);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * should be add later
     */
    private void trySetupSwipeRefresh() {

    }

    private void trySetupNavigation() {

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        getActionBarToolBar();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        trySetupNavigation();

        trySetupSwipeRefresh();

        View mainContent = findViewById(R.id.main_content);
        if (mainContent != null) {
            mainContent.setAlpha(0);
            mainContent.animate().alpha(1).setDuration(MAIN_CONTENT_FADEIN_DURATION);
        } else {
            LOGW(TAG, "No view with ID main_content to fade in.");
        }
    }

    protected void requestDataRefresh() {

    }

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

    protected Toolbar getActionBarToolBar() {
        if (mActionBarToolBar == null) {
            mActionBarToolBar = findViewById(R.id.toolbar_actionbar);
            if (mActionBarToolBar != null) {
                setSupportActionBar(mActionBarToolBar);
            }
        }
        return mActionBarToolBar;
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
}
