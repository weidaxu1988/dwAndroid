package com.dawei.dwandroid.ui.fragment.recyclerview;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;

import com.dawei.dwandroid.R;
import com.dawei.dwandroid.ui.fragment.ProgressFragment;
import com.dawei.dwandroid.util.AppHelper;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;

public class RecyclerViewFragment extends ProgressFragment {
    protected UpdateTask mUpdateTask;

    protected CardRecyclerView mRecyclerView;

    protected CardArrayRecyclerViewAdapter mCardArrayAdapter;

    protected List<Card> mCardList;

    @Override
    public int getFragmentLayout() {
        return R.layout.layout_card_recyclerview;
    }

    @Override
    protected void setupContent() {
        mCardList = new ArrayList<>();
        mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), mCardList);

        mRecyclerView = (CardRecyclerView) getActivity().findViewById(R.id.recyclerview);

        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRecyclerView.setAdapter(mCardArrayAdapter);
        }
    }

    @Override
    protected void attemptUpdateContent() {
        mUpdateTask = new UpdateTask();
        mUpdateTask.execute();
    }

    protected boolean updateContent() {
        return true;
    }

    protected class UpdateTask extends AsyncTask<Void, Void, Boolean> {
        private String message = null;
        private Exception exception = null;

        @Override
        protected Boolean doInBackground(Void... params) {
            return updateContent();
        }

        @Override
        protected void onCancelled() {
            mUpdateTask = null;
            showProgress(false);
        }

        @Override
        protected void onPostExecute(Boolean result) {
            mUpdateTask = null;

            if (result) {
                mCardArrayAdapter.notifyDataSetChanged();
            } else
                AppHelper.showExceptionDialog(getActivity(), message, exception);

            showProgress(false);
        }
    }
}
