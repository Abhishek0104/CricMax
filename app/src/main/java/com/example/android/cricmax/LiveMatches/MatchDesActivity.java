package com.example.android.cricmax.LiveMatches;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.example.android.cricmax.FragmentGenerator;

/**
 * Created by Abhishek on 21-09-2017.
 */

public class MatchDesActivity extends FragmentGenerator {

    public static final String TAG = "LiveMatches";

    public static Intent newIntent(Context context, String id) {
        Intent intent = new Intent(context, MatchDesActivity.class);
        intent.putExtra(TAG, id);
        return intent;
    }


    @Override
    protected Fragment createFragment() {
        return new MatchDesFragment();
    }
}
