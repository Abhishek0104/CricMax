package com.example.android.cricmax;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.android.cricmax.LiveMatches.LiveMatchesActivity;
import com.example.android.cricmax.PlayerStats.PlayerStatsListActivity;

/**
 * Created by Abhishek on 10-09-2017.
 */

public class HomePageFragment extends Fragment {

    Button mLiveMatchesBtn;
    Button mPlayerStatsBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page, container, false);

        mLiveMatchesBtn = view.findViewById(R.id.live_matches_btn);
        mPlayerStatsBtn = view.findViewById(R.id.player_stat_btn);

        mLiveMatchesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LiveMatchesActivity.class);
                startActivity(intent);
            }
        });
        mPlayerStatsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlayerStatsListActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
