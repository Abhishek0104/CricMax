package com.example.android.cricmax.LiveMatches;

import android.content.Context;
import android.util.Log;

import com.example.android.cricmax.PlayerStats.Player;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Abhishek on 22-09-2017.
 */

public class PlayerFetcher {

    public List<Player> fetchItem(Context context){

        List<Player> playerList = new ArrayList<>();
        String json = "";

        try {
            InputStream is = context.getAssets().open("PlayerStats.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer);

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("stats");

            for (int i =0 ; i< jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                Player player = new Player();
                player.setId(object.getString("id"));
                player.setName(object.getString("name"));
                player.setJpg(object.getString("image"));

                playerList.add(player);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return playerList;
    }

}
