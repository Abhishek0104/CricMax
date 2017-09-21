package com.example.android.cricmax.LiveMatches;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.android.cricmax.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Abhishek on 21-09-2017.
 */

public class MatchDesFragment extends Fragment {
    String mId;
    private static final String DES = "MatchDescription";
    TextView mTextView;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_des, container, false);
        mTextView = view.findViewById(R.id.MatchId);
        mId = (String) getActivity().getIntent().getSerializableExtra(MatchDesActivity.TAG);
        mTextView.setText(mId);


        return view;
    }

}
