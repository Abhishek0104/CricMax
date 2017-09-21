package com.example.android.cricmax.LiveMatches;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.cricmax.R;
import com.example.android.cricmax.URLFetcher.MatchDesFetcher;

/**
 * Created by Abhishek on 21-09-2017.
 */

public class MatchDesFragment extends Fragment {
    String mId;
    private static final String DES = "MatchDescription";
    TextView mTeamView, mScoreView, mStatView;





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_des, container, false);
        mTeamView = view.findViewById(R.id.TeamId);
        mStatView = view.findViewById(R.id.StatId);
        mScoreView = view.findViewById(R.id.ScoreId);
        mId = (String) getActivity().getIntent().getSerializableExtra(MatchDesActivity.TAG);


        new FetchItemTask().execute();

        return view;
    }


    private class FetchItemTask extends AsyncTask<Void, Void, MatchDescription> {
        @Override
        protected MatchDescription doInBackground(Void... voids) {
            return new MatchDesFetcher().fetchItems(mId);

        }

        @Override
        protected void onPostExecute(MatchDescription matchDescription) {
            mTeamView.setText(matchDescription.getTeam1() + " Vs. " + matchDescription.getTeam2());
            mStatView.setText(matchDescription.getStat());
            mScoreView.setText(matchDescription.getScore());
        }
    }

}
