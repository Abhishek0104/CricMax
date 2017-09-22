package com.example.android.cricmax.PlayerStats;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.cricmax.LiveMatches.PlayerFetcher;
import com.example.android.cricmax.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Abhishek on 22-09-2017.
 */

public class PlayerStatsListFragment extends Fragment {

    private RecyclerView mPlayerStatsRecyclerView;
    private List<Player> mItems = new ArrayList<>();
    private PlayerStatAdapter mPlayerStatAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        new FetchItems().execute();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);

        mPlayerStatsRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mPlayerStatsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();


        return view;
    }


    private void updateUI() {
        if (isAdded()){
            mPlayerStatAdapter = new PlayerStatAdapter(mItems);
            mPlayerStatsRecyclerView.setAdapter(mPlayerStatAdapter);
        }
    }

    private class PlayerStatHolder extends RecyclerView.ViewHolder {

        TextView mPlayerName;

        public PlayerStatHolder(LayoutInflater inflater, ViewGroup view){
            super(inflater.inflate(R.layout.player_list, view, false));

            mPlayerName = (TextView) itemView.findViewById(R.id.player_name_view);
        }


        private Player mPlayerView;

        public void Bind(Player player){
            mPlayerView = player;
            mPlayerName.setText(mPlayerView.getName());
        }
    }

    private class PlayerStatAdapter extends RecyclerView.Adapter<PlayerStatHolder> {

        private List<Player> mPlayers;

        private PlayerStatAdapter(List<Player> players) {
            mPlayers = players;
        }

        @Override
        public PlayerStatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new PlayerStatHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(PlayerStatHolder holder, int position) {

            Player player = mPlayers.get(position);

            holder.Bind(player);

        }

        @Override
        public int getItemCount() {
            return mPlayers.size();
        }
    }

    private class FetchItems extends AsyncTask<Void, Void, List<Player>>{
        @Override
        protected List<Player> doInBackground(Void... voids) {
            return new PlayerFetcher().fetchItem(getActivity());
        }

        @Override
        protected void onPostExecute(List<Player> players) {
            mItems = players;
            updateUI();
        }
    }


}
