package com.example.android.cricmax;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomePageActivity extends FragmentGenerator {


    /*Fragment Formation*/
    @Override
    protected Fragment createFragment() {
        return new HomePageFragment();
    }


}
