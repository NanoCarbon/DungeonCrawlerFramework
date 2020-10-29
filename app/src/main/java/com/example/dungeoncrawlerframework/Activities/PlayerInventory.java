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
import com.example.dungeoncrawlerframework.Limbs.Limb;
import com.example.dungeoncrawlerframework.Players.Player;
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

    Limb playerLimb1;
    Limb playerLimb2;
    Limb playerLimb3;
    Limb playerLimb4;
    Limb playerLimb5;
    Limb playerLimb6;
    Limb selectedLimb;
    ImageView selectedView;
    //Add regular views and other nonsenese here

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_menu);
        Intent intent = getIntent();
        //fixme:[Critical] the player being pulled up from SharedPreferences is not the same player being passed in the intent
        Player newPlayer = intent.getParcelableExtra("NEWPLAYER");
        playerLimb1 = newPlayer.getPlayerHead();
        playerLimb2 = newPlayer.getPlayerHand1();
        playerLimb3 = newPlayer.getPlayerTorso();
        playerLimb4 = newPlayer.getPlayerHand2();
        playerLimb5 = newPlayer.getPlayerLegs();
        playerLimb6 = newPlayer.getPlayerFeet();
        int playerAttack = newPlayer.getPlayerAttack();
        playerInventory = newPlayer.getPlayerInventory();

        menuLayoutDisplay = findViewById(R.id.itemMenuLayoutLayer);
        playerLimb1EquippmentDisplay = findViewById(R.id.limb1EquippedItem);
        playerLimb2EquippmentDisplay = findViewById(R.id.limb2EquippedItem);
        playerLimb3EquippmentDisplay = findViewById(R.id.limb3EquippedItem);
        playerLimb4EquippmentDisplay = findViewById(R.id.limb4EquippedItem);
        playerLimb5EquippmentDisplay = findViewById(R.id.limb5EquippedItem);
        playerLimb6EquippmentDisplay = findViewById(R.id.limb6EquippedItem);

        //todo: [Critical] replace with the selected character's limbs - see Player class for parcelable implementation
        if (playerInventory != null ){
            Set<Integer> hashSet = new LinkedHashSet(playerInventory);
            uniquePlayerInventory = new ArrayList(hashSet);
            buildRecyclerView();
        }


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

                selectedLimb = playerLimb1;
                selectedView = playerLimb1EquippmentDisplay;
                applySelectionFormat();
                if(playerInventory != null){
                    filteredPlayerInventory = getFilteredInventory(playerLimb1);
                    Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                    uniquePlayerInventory = new ArrayList(hashSet);
                    buildRecyclerView();
                }
            }
        });

        playerLimb2EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //filteredPlayerInventory.clear();

                selectedLimb = playerLimb2;
                selectedView = playerLimb2EquippmentDisplay;
                applySelectionFormat();
                if(playerInventory != null){
                    filteredPlayerInventory = getFilteredInventory(playerLimb2);
                    Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                    uniquePlayerInventory = new ArrayList(hashSet);
                    buildRecyclerView();
                }
            }
        });

        playerLimb3EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //filteredPlayerInventory.clear();

                selectedLimb = playerLimb3;
                selectedView = playerLimb3EquippmentDisplay;
                applySelectionFormat();
                if(playerInventory != null){
                    filteredPlayerInventory = getFilteredInventory(playerLimb3);
                    Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                    uniquePlayerInventory = new ArrayList(hashSet);
                    buildRecyclerView();
                }
            }
        });

        playerLimb4EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //filteredPlayerInventory.clear();

                selectedLimb = playerLimb4;
                selectedView = playerLimb4EquippmentDisplay;
                applySelectionFormat();
                if(playerInventory != null){
                    filteredPlayerInventory = getFilteredInventory(playerLimb4);
                    Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                    uniquePlayerInventory = new ArrayList(hashSet);
                    buildRecyclerView();
                }
            }
        });

        playerLimb5EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //filteredPlayerInventory.clear();

                selectedLimb = playerLimb5;
                selectedView = playerLimb5EquippmentDisplay;
                applySelectionFormat();
                if(playerInventory != null){
                    filteredPlayerInventory = getFilteredInventory(playerLimb5);
                    Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                    uniquePlayerInventory = new ArrayList(hashSet);
                    buildRecyclerView();
                }
            }
        });

        playerLimb6EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                //filteredPlayerInventory.clear();

                selectedLimb = playerLimb6;
                selectedView = playerLimb6EquippmentDisplay;
                applySelectionFormat();
                if(playerInventory != null){
                    filteredPlayerInventory = getFilteredInventory(playerLimb6);
                    Set<Integer> hashSet = new LinkedHashSet(filteredPlayerInventory);
                    uniquePlayerInventory = new ArrayList(hashSet);
                    buildRecyclerView();
                }
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