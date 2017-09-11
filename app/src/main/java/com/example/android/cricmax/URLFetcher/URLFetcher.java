package com.example.android.cricmax.URLFetcher;

import android.net.Uri;
import android.util.Log;
import com.example.android.cricmax.LiveMatches.LiveMatches;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Abhishek on 12-09-2017.
 */

public class URLFetcher {

    private static final String TAG = "LiveMatches";
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



    public List<LiveMatches> fetchItems() {

        List<LiveMatches> itemsList = new ArrayList<>();

        try {

            String url = Uri.parse("https://cricapi.com/api/cricket/").buildUpon()
                    .appendQueryParameter("apikey", API_KEY)
                    .build().toString();
            String jsonUrl = getUrlString(url);

            Log.i(TAG,"Message: "+jsonUrl);

            JSONObject jsonBody =  new JSONObject(jsonUrl);
            parseItem(itemsList, jsonBody);

        } catch (IOException e) {

            Log.e(TAG, "Error");

        } catch (JSONException e) {

            Log.e(TAG, "Failed to parse json", e);

        }

        return itemsList;
    }



    private void parseItem(List<LiveMatches> items, JSONObject jsonObject) throws IOException, JSONException {

        JSONArray scoreJsonArray = jsonObject.getJSONArray("data");

        for (int i =0 ; i< scoreJsonArray.length(); i++){

            JSONObject jsonObject1 = scoreJsonArray.getJSONObject(i);
            LiveMatches scoreItems = new LiveMatches();
            scoreItems.setId(jsonObject1.getString("unique_id"));
            scoreItems.setTitle(jsonObject1.getString("title"));
            items.add(scoreItems);

        }
    }

}
