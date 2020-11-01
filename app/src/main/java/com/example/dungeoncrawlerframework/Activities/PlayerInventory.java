package com.example.dungeoncrawlerframework.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

import static com.example.dungeoncrawlerframework.Activities.SelectPlayer.EXTRA_PLAYER;


public class PlayerInventory extends AppCompatActivity {
    //RECYCLER VIEW STUFF - DO NOT TOUCH
    private RecyclerView mRecyclerView;
    private InventoryAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    private View menuLayoutDisplay;
    private ImageView selectedView;
    private Limb selectedLimb;
    private ImageView playerLimb1EquippmentDisplay;
    private ImageView playerLimb2EquippmentDisplay;
    private ImageView playerLimb3EquippmentDisplay;
    private ImageView playerLimb4EquippmentDisplay;
    private ImageView playerLimb5EquippmentDisplay;
    private ImageView playerLimb6EquippmentDisplay;
    private Button unequipButton;
    private Button return2SelectPlayerButton;
    private TextView inventoryPlayerHealthDisplay;
    private TextView inventoryPlayerAttackDisplay;
    private TextView inventoryPlayerDefenseDisplay;
    private TextView inventoryPlayerSkillPowerDisplay;
    private TextView inventoryPlayerEnergyDisplay;

    Resources res;

    Player newPlayer;
    private int playerHealth;
    private int itemHealthEffect = 0;
    private int playerAttack;
    private int itemAttackEffect = 0;
    private int playerDefense;
    private int itemDefenseEffect = 0;
    private int playerMaxHealth;
    private int itemMaxHealthEffect = 0;
    private int playerEnergy;
    private int itemEnergyEffect = 0;
    private int playerMaxEnergy;
    private int itemMaxEnergyEffect = 0;
    private int playerSkillPower;
    private int itemSkillPowerEffect = 0;

    Limb playerLimb1;
    Limb playerLimb2;
    Limb playerLimb3;
    Limb playerLimb4;
    Limb playerLimb5;
    Limb playerLimb6;

    private ArrayList<Integer> playerInventory;
    private ArrayList<Integer> uniquePlayerInventory;
    private ArrayList<Integer> filteredPlayerInventory;
    private final ItemDictionary itemDictionary = new ItemDictionary();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_menu);
        Intent intent = getIntent();
        newPlayer = intent.getParcelableExtra(EXTRA_PLAYER);
        playerLimb1 = newPlayer.getPlayerHead();
        playerLimb2 = newPlayer.getPlayerHand1();
        playerLimb3 = newPlayer.getPlayerTorso();
        playerLimb4 = newPlayer.getPlayerHand2();
        playerLimb5 = newPlayer.getPlayerLegs();
        playerLimb6 = newPlayer.getPlayerFeet();
        playerInventory = newPlayer.getPlayerInventory();
        newPlayer.playerActivateEquipment(playerLimb1);
        newPlayer.playerActivateEquipment(playerLimb2);
        newPlayer.playerActivateEquipment(playerLimb3);
        newPlayer.playerActivateEquipment(playerLimb4);
        newPlayer.playerActivateEquipment(playerLimb5);
        newPlayer.playerActivateEquipment(playerLimb6);
        initializeViews();
        getPlayerStats();
        updatePlayerViews();

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
                unequipButton.setText(R.string.unequipAll_StringValue);
                applySelectionFormat();
                buildRecyclerView();
            }
        });

        playerLimb1EquippmentDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

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

        unequipButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                unequipItem();
            }
        });

        return2SelectPlayerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                return2SelectPlayerActivity();
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

    public void return2SelectPlayerActivity(){
        Intent intent = new Intent(this,SelectPlayer.class);
        intent.putExtra(EXTRA_PLAYER,newPlayer);
        startActivity(intent);
    }

    private void unequipItem() {
        Drawable shape = getResources().getDrawable(R.drawable.shape);
        //filteredPlayerInventory.clear();
        if(selectedLimb != null){
            if(selectedLimb.getEquippedItem() != null){
                int itemEffectType = selectedLimb.getEquippedItem().getItemEffectType();
                int itemEffectValue = selectedLimb.getEquippedItem().getEffectValue();
                switch (itemEffectType) {
                    case 1:
                        //affects playerMaxHealth
                        itemMaxHealthEffect = itemMaxHealthEffect - itemEffectValue;
                        break;
                    case 2:
                        //affects playerHealth
                        itemHealthEffect = itemHealthEffect - itemEffectValue;
                        break;
                    case 3:
                        //affects playerAttack
                        itemAttackEffect = itemAttackEffect - itemEffectValue;
                        break;
                    case 4:
                        //affects playerDefense
                        itemDefenseEffect = itemDefenseEffect - itemEffectValue;
                        break;
                    case 5:
                        //affects playerMaxEnergy;
                        itemMaxEnergyEffect = itemMaxEnergyEffect - itemEffectValue;
                        break;
                    case 6:
                        //affects playerEnergy;
                        itemEnergyEffect = itemEnergyEffect - itemEffectValue;
                    case 7:
                        //affects playerSkillPower;
                        itemSkillPowerEffect = itemSkillPowerEffect - itemEffectValue;
                        break;
                }
            }
            selectedLimb.setEquippedItem(null);
            selectedView.setImageDrawable(null);
            setPlayerStats();
            getPlayerStats();
            updatePlayerViews();
        }else{
            playerLimb1.setEquippedItem(null);
            playerLimb2.setEquippedItem(null);
            playerLimb3.setEquippedItem(null);
            playerLimb4.setEquippedItem(null);
            playerLimb5.setEquippedItem(null);
            playerLimb6.setEquippedItem(null);
            playerLimb1EquippmentDisplay.setImageDrawable(null);
            playerLimb2EquippmentDisplay.setImageDrawable(null);
            playerLimb3EquippmentDisplay.setImageDrawable(null);
            playerLimb4EquippmentDisplay.setImageDrawable(null);
            playerLimb5EquippmentDisplay.setImageDrawable(null);
            playerLimb6EquippmentDisplay.setImageDrawable(null);
            itemHealthEffect = 0;
            itemAttackEffect = 0;
            itemDefenseEffect = 0;
            itemMaxHealthEffect = 0;
            itemEnergyEffect = 0;
            itemMaxEnergyEffect = 0;
            itemSkillPowerEffect = 0;
            setPlayerStats();
            getPlayerStats();
            updatePlayerViews();
        }
    }

    private void initializeViews() {
        //fixme:[BUG] equipment images aren't showing after resuming from Select Player
        menuLayoutDisplay = findViewById(R.id.itemMenuLayoutLayer);
        playerLimb1EquippmentDisplay = findViewById(R.id.limb1EquippedItem);
        playerLimb2EquippmentDisplay = findViewById(R.id.limb2EquippedItem);
        playerLimb3EquippmentDisplay = findViewById(R.id.limb3EquippedItem);
        playerLimb4EquippmentDisplay = findViewById(R.id.limb4EquippedItem);
        playerLimb5EquippmentDisplay = findViewById(R.id.limb5EquippedItem);
        playerLimb6EquippmentDisplay = findViewById(R.id.limb6EquippedItem);
        unequipButton = findViewById(R.id.unequipButtonView);
        return2SelectPlayerButton = findViewById(R.id.returnSelectPlayerButton);
        inventoryPlayerHealthDisplay = findViewById(R.id.inventoryPlayerHPTextView);
        inventoryPlayerAttackDisplay = findViewById(R.id.inventoryPlayerAttackTextView);
        inventoryPlayerDefenseDisplay = findViewById(R.id.inventoryPlayerDefenseTextView);
        inventoryPlayerSkillPowerDisplay = findViewById(R.id.inventoryPlayerSkillPowerTextView);
        inventoryPlayerEnergyDisplay = findViewById(R.id.inventoryPlayerEnergyTextView);
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

    private void getPlayerStats(){
        //todo:[Critical] get player equipment as well
        playerHealth = newPlayer.getPlayerHealth();
        itemHealthEffect = newPlayer.getItemHealthEffect();
        playerAttack = newPlayer.getPlayerAttack();
        itemAttackEffect = newPlayer.getItemAttackEffect();
        playerDefense = newPlayer.getPlayerDefense();
        itemDefenseEffect = newPlayer.getItemDefenseEffect();
        playerMaxHealth = newPlayer.getPlayerMaxHealth();
        itemMaxHealthEffect = newPlayer.getItemMaxHealthEffect();
        playerEnergy = newPlayer.getPlayerEnergy();
        itemEnergyEffect = newPlayer.getItemEnergyEffect();
        playerMaxEnergy = newPlayer.getPlayerMaxEnergy();
        itemMaxEnergyEffect = newPlayer.getItemMaxEnergyEffect();
        playerSkillPower = newPlayer.getPlayerSkillPower();
        itemSkillPowerEffect = newPlayer.getItemSkillPowerEffect();
    }

    private void setPlayerStats(){
        newPlayer.setPlayerHealth(playerHealth);
        newPlayer.setItemHealthEffect(itemHealthEffect);
        newPlayer.setPlayerAttack(playerAttack);
        newPlayer.setItemAttackEffect(itemAttackEffect);
        newPlayer.setPlayerDefense(playerDefense);
        newPlayer.setItemDefenseEffect(itemDefenseEffect);
        newPlayer.setPlayerMaxHealth(playerMaxHealth);
        newPlayer.setItemMaxHealthEffect(itemMaxHealthEffect);
        newPlayer.setPlayerEnergy(playerEnergy);
        newPlayer.setItemEnergyEffect(itemEnergyEffect);
        newPlayer.setPlayerMaxEnergy(playerMaxEnergy);
        newPlayer.setItemMaxEnergyEffect(itemMaxEnergyEffect);
        newPlayer.setPlayerSkillPower(playerSkillPower);
        newPlayer.setItemSkillPowerEffect(itemSkillPowerEffect);
    }

    private void updatePlayerViews(){
        //todo:[Critical] update limb views with equipment drawables
        res = getResources();
        inventoryPlayerHealthDisplay.setText(res.getString(R.string.inventoryHP_StringValue,playerHealth,playerMaxHealth,itemHealthEffect,itemMaxHealthEffect,playerHealth+itemHealthEffect,playerMaxHealth+itemMaxHealthEffect));
        inventoryPlayerAttackDisplay.setText(res.getString(R.string.inventoryAttack_StringValue,playerAttack,itemAttackEffect,playerAttack+itemAttackEffect));
        inventoryPlayerDefenseDisplay.setText(res.getString(R.string.inventoryDefense_StringValue,playerDefense,itemDefenseEffect,playerDefense+itemDefenseEffect));
        inventoryPlayerSkillPowerDisplay.setText(res.getString(R.string.inventorySkillPower_StringValue,playerSkillPower,itemSkillPowerEffect,playerSkillPower+itemSkillPowerEffect));
        inventoryPlayerEnergyDisplay.setText(res.getString(R.string.inventoryEnergy_StringValue,playerEnergy,playerMaxEnergy,itemEnergyEffect,itemMaxEnergyEffect,playerEnergy+itemEnergyEffect,playerMaxEnergy+itemMaxEnergyEffect));

        if(playerLimb1.getEquippedItem() != null){
            Drawable limb1ItemImage = ContextCompat.getDrawable(this,playerLimb1.getEquippedItem().getItemImageId());
        }
        if(playerLimb2.getEquippedItem() != null){
            Drawable limb2ItemImage = ContextCompat.getDrawable(this,playerLimb2.getEquippedItem().getItemImageId());
        }
        if(playerLimb3.getEquippedItem() != null){
            Drawable limb3ItemImage = ContextCompat.getDrawable(this,playerLimb3.getEquippedItem().getItemImageId());
        }
        if(playerLimb4.getEquippedItem() != null){
            Drawable limb4ItemImage = ContextCompat.getDrawable(this,playerLimb4.getEquippedItem().getItemImageId());
        }
        if(playerLimb5.getEquippedItem() != null){
            Drawable limb5ItemImage = ContextCompat.getDrawable(this,playerLimb5.getEquippedItem().getItemImageId());
        }
        if(playerLimb6.getEquippedItem() != null){
            Drawable limb6ItemImage = ContextCompat.getDrawable(this,playerLimb6.getEquippedItem().getItemImageId());
        }
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
            unequipButton.setText(R.string.unequipSingle_StringValue);
        }

    }

    private void playerEquipItem(Integer itemIndex) {

        //todo:[High] set a limit on the number of items you can equip based on the number of items in equipment

        if (selectedLimb.getEquippedItem() != null){
            unequipItem();
        }
        Item equippedItem = itemDictionary.getItem(itemIndex);
        selectedLimb.setEquippedItem(equippedItem);
        newPlayer.playerActivateEquipment(selectedLimb);
        Drawable itemImage = ContextCompat.getDrawable(this,selectedLimb.getEquippedItem().getItemImageId());
        selectedView.setImageDrawable(itemImage);
        getPlayerStats();
        updatePlayerViews();

    }
}