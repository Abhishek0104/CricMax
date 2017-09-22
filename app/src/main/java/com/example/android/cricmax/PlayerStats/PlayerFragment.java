package com.example.android.cricmax.PlayerStats;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.cricmax.R;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Abhishek on 22-09-2017.
 */

public class PlayerFragment extends Fragment {

    String mId;
    TextView mPlayerName, mPlayerProfile;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player, container, false);
        mPlayerName = view.findViewById(R.id.player_name);
        mPlayerProfile = view.findViewById(R.id.player_profile);

        mId = (String) getActivity().getIntent()
                .getSerializableExtra(PlayerActivity.TAG);

        new FetchItems().execute();

        return view;
    }

    private class FetchItems extends AsyncTask<Void, Void, PlayerProfile>{
        @Override
        protected PlayerProfile doInBackground(Void... voids) {
            return new PlayerFetcher().fetchItems(mId);
        }

        @Override
        protected void onPostExecute(PlayerProfile playerProfile) {
            mPlayerName.setText(playerProfile.getName());
            mPlayerProfile.setText(playerProfile.getProfile());
        }
    }

    private class PlayerFetcher{
        private static final String TAG = "PlayerProfile";
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

        public PlayerProfile fetchItems(String id){
            PlayerProfile playerProfile = new PlayerProfile();

            try{
                String url = Uri.parse("https://cricapi.com/api/playerStats/").buildUpon()
                        .appendQueryParameter("pid", id)
                        .appendQueryParameter("apikey", API_KEY)
                        .build().toString();
                String jsonUrl = getUrlString(url);

                Log.i(TAG,"Message: "+jsonUrl);

                JSONObject jsonObject =  new JSONObject(jsonUrl);


                playerProfile.setName(jsonObject.getString("name"));
                playerProfile.setProfile(jsonObject.getString("profile"));


            } catch (Exception e) {
                Log.e(TAG, "Error");
            }

            return playerProfile;
        }
    }
}
