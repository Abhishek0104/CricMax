package com.example.android.cricmax.URLFetcher;

import android.net.Uri;
import android.util.Log;

import com.example.android.cricmax.LiveMatches.MatchDesActivity;
import com.example.android.cricmax.LiveMatches.MatchDescription;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Abhishek on 21-09-2017.
 */

public class MatchDesFetcher {

    private static final String TAG = "MatchDescription";
    private static final String API_KEY = "BR5IO0ku9iWCnUK4tnSYe3Ptjof1";



    public byte[] getUrlBytes(String urlSpecs) throws IOException {

        URL url = new URL(urlSpecs);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in  = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK){

                throw new IOException(connection.getResponseMessage()+ ": with" + urlSpecs);

            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];

            while((bytesRead = in.read(buffer)) > 0) {

                out.write(buffer, 0, bytesRead);

            }

            out.close();

            return out.toByteArray();
        } finally {

            connection.disconnect();

        }
    }



    public String getUrlString(String urlSpecs) throws IOException {

        return new String(getUrlBytes(urlSpecs));

    }


    public MatchDescription fetchItems(String id) {
        MatchDescription matchDescription = new MatchDescription();

        try{
            String url = Uri.parse("https://cricapi.com/api/cricketScore/").buildUpon()
                    .appendQueryParameter("unique_id", id)
                    .appendQueryParameter("apikey", API_KEY)
                    .build().toString();
            String jsonUrl = getUrlString(url);

            Log.i(TAG,"Message: "+jsonUrl);

            JSONObject jsonObject =  new JSONObject(jsonUrl);


            matchDescription.setStat(jsonObject.getString("stat"));
            matchDescription.setScore(jsonObject.getString("score"));
            matchDescription.setTeam1(jsonObject.getString("team-1"));
            matchDescription.setTeam2(jsonObject.getString("team-2"));


        } catch (Exception e) {
            Log.e(TAG, "Error");
        }
        return matchDescription;
    }

}
