package com.example.dungeoncrawlerframework.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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

    private View menuLayoutDisplay;
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
    ImageView selectedView;
    //Add regular views and other nonsenese here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_menu);
        Intent intent = getIntent();
        playerInventory = intent.getIntegerArrayListExtra(SelectPlayer.EXTRA_PLAYERINVENTORY);

        Set<Integer> hashSet = new LinkedHashSet(playerInventory);
        uniquePlayerInventory = new ArrayList(hashSet);

        menuLayoutDisplay = findViewById(R.id.itemMenuLayoutLayer);
        playerLimb1EquippmentDisplay = findViewById(R.id.limb1EquippedItem);
        playerLimb2EquippmentDisplay = findViewById(R.id.limb2EquippedItem);
        playerLimb3EquippmentDisplay = findViewById(R.id.limb3EquippedItem);
        playerLimb4EquippmentDisplay = findViewById(R.id.limb4EquippedItem);
        playerLimb5EquippmentDisplay = findViewById(R.id.limb5EquippedItem);
        playerLimb6EquippmentDisplay = findViewById(R.id.limb6EquippedItem);

        //todo: [Critical] replace with the selected character's limbs - see Player class for parcelable implementation


        buildRecyclerView();

        menuLayoutDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                filteredPlayerInventory.clear();
                Set<Integer> hashSet = new LinkedHashSet(playerInventory);
                uniquePlayerInventory = new ArrayList(hashSet);
                selectedLimb = null;
                selectedView = null;
                applySelectionFormat();
                buildRecyclerView();
            }
        });

        playerLimb1EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //filteredPlayerInventory.clear();
                filteredPlayerInventory = getFilteredInventory(playerLimb1);
                Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                uniquePlayerInventory = new ArrayList(hashSet);
                selectedLimb = playerLimb1;
                selectedView = playerLimb1EquippmentDisplay;
                applySelectionFormat();
                buildRecyclerView();
            }
        });

        playerLimb2EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //filteredPlayerInventory.clear();
                filteredPlayerInventory = getFilteredInventory(playerLimb2);
                Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                uniquePlayerInventory = new ArrayList(hashSet);
                selectedLimb = playerLimb2;
                selectedView = playerLimb2EquippmentDisplay;
                applySelectionFormat();
                buildRecyclerView();
            }
        });

        playerLimb3EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //filteredPlayerInventory.clear();
                filteredPlayerInventory = getFilteredInventory(playerLimb3);
                Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                uniquePlayerInventory = new ArrayList(hashSet);
                selectedLimb = playerLimb3;
                selectedView = playerLimb3EquippmentDisplay;
                applySelectionFormat();
                buildRecyclerView();
            }
        });

        playerLimb4EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //filteredPlayerInventory.clear();
                filteredPlayerInventory = getFilteredInventory(playerLimb4);
                Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                uniquePlayerInventory = new ArrayList(hashSet);
                selectedLimb = playerLimb4;
                selectedView = playerLimb4EquippmentDisplay;
                applySelectionFormat();
                buildRecyclerView();
            }
        });

        playerLimb5EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //filteredPlayerInventory.clear();
                filteredPlayerInventory = getFilteredInventory(playerLimb5);
                Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                uniquePlayerInventory = new ArrayList(hashSet);
                selectedLimb = playerLimb5;
                selectedView = playerLimb5EquippmentDisplay;
                applySelectionFormat();
                buildRecyclerView();
            }
        });

        playerLimb6EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //filteredPlayerInventory.clear();
                filteredPlayerInventory = getFilteredInventory(playerLimb6);
                Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                uniquePlayerInventory = new ArrayList(hashSet);
                selectedLimb = playerLimb6;
                selectedView = playerLimb6EquippmentDisplay;
                applySelectionFormat();
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
                if(selectedLimb != null){
                    playerEquipItem(uniquePlayerInventory.get(position));
                }
            }
        });
    }

    private void applySelectionFormat(){
        //todo: [Low] update format/include a new shape drawable with a red border
        Drawable shape = getResources().getDrawable(R.drawable.shape);
        playerLimb1EquippmentDisplay.setBackground(shape);
        playerLimb2EquippmentDisplay.setBackground(shape);
        playerLimb3EquippmentDisplay.setBackground(shape);
        playerLimb4EquippmentDisplay.setBackground(shape);
        playerLimb5EquippmentDisplay.setBackground(shape);
        playerLimb6EquippmentDisplay.setBackground(shape);
        if(selectedView != null){
            selectedView.setBackgroundColor(Color.RED);
        }

    }

    private void playerEquipItem(Integer itemIndex) {
        Item equippedItem = itemDictionary.getItem(itemIndex);
        selectedLimb.setEquippedItem(equippedItem);
        Drawable itemImage = ContextCompat.getDrawable(this,selectedLimb.getEquippedItem().getItemImageId());
        selectedView.setImageDrawable(itemImage);
    }
}