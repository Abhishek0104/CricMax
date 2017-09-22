package com.example.android.cricmax.PlayerStats;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.android.cricmax.FragmentGenerator;
import com.example.android.cricmax.LiveMatches.MatchDesActivity;

/**
 * Created by Abhishek on 22-09-2017.
 */

public class PlayerActivity extends FragmentGenerator {

    public static final String TAG = "LiveMatches";

    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra(TAG, id);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return new PlayerFragment();
    }
}
