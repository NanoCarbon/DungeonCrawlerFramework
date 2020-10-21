package com.example.dungeoncrawlerframework.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dungeoncrawlerframework.Items.Item;
import com.example.dungeoncrawlerframework.Items.ItemDictionary;
import com.example.dungeoncrawlerframework.Limbs.Feet;
import com.example.dungeoncrawlerframework.Limbs.Hand;
import com.example.dungeoncrawlerframework.Limbs.Head;
import com.example.dungeoncrawlerframework.Limbs.Legs;
import com.example.dungeoncrawlerframework.Limbs.Limb;
import com.example.dungeoncrawlerframework.Limbs.Torso;
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
    private ArrayList<Integer> uniquePlayerInventory;
    private ArrayList<Integer> filteredPlayerInventory;
    private ImageView playerLimb1EquippmentDisplay;
    private ImageView playerLimb2EquippmentDisplay;
    private ImageView playerLimb3EquippmentDisplay;
    private ImageView playerLimb4EquippmentDisplay;
    private ImageView playerLimb5EquippmentDisplay;
    private ImageView playerLimb6EquippmentDisplay;
    private final ItemDictionary itemDictionary = new ItemDictionary();

    Head playerLimb1 = new Head();
    Hand playerLimb2 = new Hand();
    Torso playerLimb3 = new Torso();
    Hand playerLimb4 = new Hand();
    Legs playerLimb5 = new Legs();
    Feet playerLimb6 = new Feet();
    Limb selectedLimb;
    //Add regular views and other nonsenese here

    //todo: [High] create a way to select the type of equipment you want to bring into battle
    //todo: [High] create a way to filter recycler view based on the body part selected
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_menu);
        Intent intent = getIntent();
        playerInventory = intent.getIntegerArrayListExtra(SelectPlayer.EXTRA_PLAYERINVENTORY);
        //todo:[Critical] Create an on button click listener that creates a new filteredPlayerInventory from playerInventory and based on the limb associated with that image view
        //todo:[Critical] Pass the filteredPlayerInventory through the Build Recycler View method instead
        //todo:[Critical] Use the filteredPlayerInventory to generate the uniquePlayerInventory then rebuild

        Set<Integer> hashSet = new LinkedHashSet(playerInventory);
        uniquePlayerInventory = new ArrayList(hashSet);

        playerLimb1EquippmentDisplay = findViewById(R.id.limb1EquippedItem);
        playerLimb2EquippmentDisplay = findViewById(R.id.limb2EquippedItem);
        playerLimb3EquippmentDisplay = findViewById(R.id.limb3EquippedItem);
        playerLimb4EquippmentDisplay = findViewById(R.id.limb4EquippedItem);
        playerLimb5EquippmentDisplay = findViewById(R.id.limb5EquippedItem);
        playerLimb6EquippmentDisplay = findViewById(R.id.limb6EquippedItem);

        //todo: [High] replace with the selected character's limbs - see Player class for serializable implementation


        buildRecyclerView();

        playerLimb1EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //todo:[Low] replace with a better looking "selected" visual
                playerLimb1EquippmentDisplay.setBackgroundColor(Color.RED);
                //filteredPlayerInventory.clear();
                filteredPlayerInventory = getFilteredInventory(playerLimb1);
                Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                uniquePlayerInventory = new ArrayList(hashSet);
                selectedLimb = playerLimb1;
                buildRecyclerView();
            }
        });

        playerLimb2EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //todo:[Low] replace with a better looking "selected" visual
                playerLimb2EquippmentDisplay.setBackgroundColor(Color.RED);
                //filteredPlayerInventory.clear();
                filteredPlayerInventory = getFilteredInventory(playerLimb2);
                Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                uniquePlayerInventory = new ArrayList(hashSet);
                selectedLimb = playerLimb2;
                buildRecyclerView();
            }
        });

        playerLimb3EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //todo:[Low] replace with a better looking "selected" visual
                playerLimb2EquippmentDisplay.setBackgroundColor(Color.RED);
                //filteredPlayerInventory.clear();
                filteredPlayerInventory = getFilteredInventory(playerLimb3);
                Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                uniquePlayerInventory = new ArrayList(hashSet);
                selectedLimb = playerLimb3;
                buildRecyclerView();
            }
        });

        playerLimb4EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //todo:[Low] replace with a better looking "selected" visual
                playerLimb2EquippmentDisplay.setBackgroundColor(Color.RED);
                //filteredPlayerInventory.clear();
                filteredPlayerInventory = getFilteredInventory(playerLimb4);
                Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                uniquePlayerInventory = new ArrayList(hashSet);
                selectedLimb = playerLimb4;
                buildRecyclerView();
            }
        });

        playerLimb5EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //todo:[Low] replace with a better looking "selected" visual
                playerLimb2EquippmentDisplay.setBackgroundColor(Color.RED);
                //filteredPlayerInventory.clear();
                filteredPlayerInventory = getFilteredInventory(playerLimb5);
                Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                uniquePlayerInventory = new ArrayList(hashSet);
                selectedLimb = playerLimb5;
                buildRecyclerView();
            }
        });

        playerLimb6EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //todo:[Low] replace with a better looking "selected" visual
                playerLimb2EquippmentDisplay.setBackgroundColor(Color.RED);
                //filteredPlayerInventory.clear();
                filteredPlayerInventory = getFilteredInventory(playerLimb6);
                Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                uniquePlayerInventory = new ArrayList(hashSet);
                selectedLimb = playerLimb6;
                buildRecyclerView();
            }
        });
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
    //fixme: java.lang.NullPointerException: Attempt to invoke virtual method 'com.example.dungeoncrawlerframework.Items.Item com.example.dungeoncrawlerframework.Items.ItemDictionary.getItem(int)' on a null object reference
    private ArrayList<Integer> getFilteredInventory(Limb limb){
        ArrayList<Integer> filteredInventory = new ArrayList<Integer>();
        for (int i = 0; i < playerInventory.size(); i++){
            Item item = itemDictionary.getItem(playerInventory.get(i));
            if(limb.getLimbType() == item.getLimbRestriction()){
                filteredInventory.add(playerInventory.get(i));
            }
        }
        return filteredInventory;
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.itemMenuRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new InventoryAdapter(uniquePlayerInventory);
        mAdapter.setPlayerInventory(playerInventory);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new InventoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
            //todo:[High] create a method to get the item in that position
                //todo:[High] create a method to equip the item
                //todo:[High] within the equip item method, unequip item if there is already an item
                
                playerEquipItem(uniquePlayerInventory.get(position));
            }
        });
    }

    private void playerEquipItem(Integer itemIndex) {
        Item equippedItem = itemDictionary.getItem(itemIndex);
    }
}