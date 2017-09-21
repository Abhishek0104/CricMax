package com.example.android.cricmax.LiveMatches;

import android.support.v4.app.Fragment;

import com.example.android.cricmax.FragmentGenerator;

/**
 * Created by Abhishek on 10-09-2017.
 */

public class LiveMatchesActivity extends FragmentGenerator {


    @Override
    protected Fragment createFragment() {
        return new LiveMatchesFragment();
    }
}
