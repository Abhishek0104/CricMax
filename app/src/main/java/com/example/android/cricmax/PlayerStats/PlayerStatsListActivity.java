package com.example.android.cricmax.PlayerStats;

import android.support.v4.app.Fragment;

import com.example.android.cricmax.FragmentGenerator;

/**
 * Created by Abhishek on 22-09-2017.
 */

public class PlayerStatsListActivity extends FragmentGenerator {


    @Override
    protected Fragment createFragment() {
        return new PlayerStatsListFragment();
    }
}
