package com.example.dungeoncrawlerframework;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;


public class ItemMenu extends AppCompatActivity {
    //RECYCLER VIEW STUFF - DO NOT TOUCH
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Integer> playerInventory;

    //Add regular views and other nonsenese here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_menu);
        Intent intent = getIntent();
        playerInventory = intent.getIntegerArrayListExtra(SelectPlayer.EXTRA_PLAYERINVENTORY);


        mRecyclerView = findViewById(R.id.itemMenuRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);

        Set<Integer> hashSet = new LinkedHashSet(playerInventory);
        ArrayList<Integer> uniquePlayerInventory = new ArrayList(hashSet);
        mAdapter = new InventoryAdapter(uniquePlayerInventory);
        ((InventoryAdapter) mAdapter).setPlayerInventory(playerInventory);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}