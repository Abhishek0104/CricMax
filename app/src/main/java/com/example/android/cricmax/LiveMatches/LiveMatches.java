package com.example.android.cricmax.LiveMatches;

/**
 * Created by Abhishek on 12-09-2017.
 */

public class LiveMatches {

    private String mTitle;
    private String mId;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    @Override
    public String toString() {
        return mTitle;
    }

}
