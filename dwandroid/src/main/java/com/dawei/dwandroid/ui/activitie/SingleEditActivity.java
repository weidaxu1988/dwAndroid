package com.dawei.dwandroid.ui.activitie;

import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.dawei.dwandroid.R;
import com.dawei.dwandroid.model.BaseDataItem;

/**
 * Created by wdxu on 2/12/2014.
 */
public class SingleEditActivity extends ItemActivity {

    protected TextView mTitleView;
    protected EditText mEditView;

    @Override
    public void restoreActionBar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (mItem != null)
            setTitle(mItem.getTitle());
//        toolbar.setTitle(mItem.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == android.R.id.home) {
            cancelAndFinish();
            return true;
        } else if (id == R.id.action_done) {
            attemptSaveChange();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void setupContent() {
        mTitleView = (TextView) findViewById(R.id.txt_title);
        mEditView = (EditText) findViewById(R.id.edit_entry);
        mEditView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.action_done || id == EditorInfo.IME_NULL) {
                    attemptSaveChange();
                    return true;
                }
                return false;
            }
        });

        if (mItem != null) {
            mTitleView.setText("Please entry your " + mItem.getTitle());
            mEditView.setText(mItem.getContent());
        }
    }

    protected void attemptSaveChange() {
        String text = mEditView.getText().toString();

        if (!text.isEmpty() && text != mItem.getContent()) {
//            mItem.setContent(text);
            saveChange(text);
        } else {
            cancelAndFinish();
        }
    }

    protected void saveChange(final String change) {
        saveAndFinish();
    }

    protected void restoreChange(String change) {
    }

    protected void cancelAndFinish() {
        setResult(RESULT_CANCELED);
        finish();
    }

    protected void saveAndFinish() {
        setResult(RESULT_OK,
                getIntent().putExtra(BaseDataItem.KEY_ITEM_DATA, mItem));
        finish();
    }

    @Override
    public int getActivityLayout() {
        return R.layout.activity_single_edit;
    }
}
