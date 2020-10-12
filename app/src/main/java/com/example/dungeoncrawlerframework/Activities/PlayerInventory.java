package com.example.dungeoncrawlerframework.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dungeoncrawlerframework.R;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;


public class PlayerInventory extends AppCompatActivity {
    //RECYCLER VIEW STUFF - DO NOT TOUCH
    private RecyclerView mRecyclerView;
    private InventoryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Integer> playerInventory;
    private ImageView playerLimb1EquippmentDisplay;
    private ImageView playerLimb2EquippmentDisplay;
    private ImageView playerLimb3EquippmentDisplay;
    private ImageView playerLimb4EquippmentDisplay;
    private ImageView playerLimb5EquippmentDisplay;
    private ImageView playerLimb6EquippmentDisplay;
    private ArrayList<Integer> uniquePlayerInventory;

    //Add regular views and other nonsenese here

    //todo: [High] create a way to select the type of equipment you want to bring into battle
    //todo: [High] create a way to filter recycler view based on the body part selected
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_menu);
        Intent intent = getIntent();
        playerInventory = intent.getIntegerArrayListExtra(SelectPlayer.EXTRA_PLAYERINVENTORY);
        Set<Integer> hashSet = new LinkedHashSet(playerInventory);
        uniquePlayerInventory = new ArrayList(hashSet);

        buildRecyclerView();

        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish_activity")) {
                    finish();
                    // DO WHATEVER YOU WANT.
                }
            }
        };

    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.itemMenuRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new InventoryAdapter(uniquePlayerInventory);
        ((InventoryAdapter) mAdapter).setPlayerInventory(playerInventory);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new InventoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        });
    }
}