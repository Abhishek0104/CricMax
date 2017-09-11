package com.example.android.cricmax.LiveMatches;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.cricmax.R;
import com.example.android.cricmax.URLFetcher.URLFetcher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek on 10-09-2017.
 */

public class LiveMatchesFragment extends Fragment {

    private RecyclerView mLiveMatchRecyclerView;
    private LiveMatchesAdapter mLiveMatchesAdapter;
    private static final String TAG = "LiveMatches";
    private List<LiveMatches> mItems = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItemTask().execute();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);

        mLiveMatchRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mLiveMatchRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }


    private void updateUI() {
        if (isAdded()){
            mLiveMatchesAdapter = new LiveMatchesAdapter(mItems);
            mLiveMatchRecyclerView.setAdapter(mLiveMatchesAdapter);
        }
    }

    private class LiveMatchesHolder extends RecyclerView.ViewHolder {

        private TextView mSportDescription;
        private TextView mSportId;

        public LiveMatchesHolder(LayoutInflater inflater, ViewGroup view) {
            super(inflater.inflate(R.layout.live_matches, view, false));

            mSportDescription = (TextView) itemView.findViewById(R.id.score_title);
            mSportId = (TextView) itemView.findViewById(R.id.score_id);
        }



        private LiveMatches mScoreItems;
        public  void Bind(LiveMatches sports) {
            mScoreItems = sports;
            mSportDescription.setText(mScoreItems.getTitle());
            mSportId.setText(mScoreItems.getId());
        }


    }



    //test
    private class LiveMatchesAdapter extends RecyclerView.Adapter<LiveMatchesHolder> {
        private List<LiveMatches> mSportses;

        private LiveMatchesAdapter(List<LiveMatches> sportses) {
            mSportses = sportses;
        }

        @Override
        public LiveMatchesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new LiveMatchesHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(LiveMatchesHolder holder, int position) {
            LiveMatches sports = mSportses.get(position);
            holder.Bind(sports);
        }

        @Override
        public int getItemCount() {
            return mSportses.size();
        }
    }


    //test
    private class FetchItemTask extends AsyncTask<Void, Void, List<LiveMatches>> {
        @Override
        protected List<LiveMatches> doInBackground(Void... voids) {
            return new URLFetcher().fetchItems();

        }

        @Override
        protected void onPostExecute(List<LiveMatches> scoreItemses) {
            mItems = scoreItemses;
            updateUI();
        }
    }

}
