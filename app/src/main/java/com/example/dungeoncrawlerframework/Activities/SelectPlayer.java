package com.example.dungeoncrawlerframework.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.dungeoncrawlerframework.Limbs.Limb;
import com.example.dungeoncrawlerframework.Players.Player;
import com.example.dungeoncrawlerframework.Players.PlayerClassDictionary;
import com.example.dungeoncrawlerframework.R;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SelectPlayer extends AppCompatActivity {
    private Button startButton;
    private Button changeClassButton;
    private Button loadButton;
    private Button toPlayerInventoryButton;
    private Player newPlayer;
    private ImageView selectedClassDisplay;
    private TextView playerHPDisplay;
    private TextView playerAttackDisplay;
    private TextView playerDefenseDisplay;
    private TextView playerEnergyDisplay;
    private TextView playerDescriptionDisplay;
    private TextView playerSkillPowerDisplay;
    private TextView playerInventoryCountDisplay;

    private int playerImageId;
    private int playerHP;
    private int itemHPEffect;

    private int playerMaxHP;
    private int itemMaxHPEffect;

    private int playerEnergy;
    private int itemEnergyEffect;

    private int playerMaxEnergy;
    private int itemMaxEnergyEffect;

    private int playerAttack;
    private int itemAttackEffect;

    private int playerDefense;
    private int itemDefenseEffect;

    private int playerSkillPower;
    private int itemSkillPowerEffect;

    private Limb playerLimb1;
    private Limb playerLimb2;
    private Limb playerLimb3;
    private Limb playerLimb4;
    private Limb playerLimb5;
    private Limb playerLimb6;


    private int killCount;
    private int playerExperience;
    private int playerLevel;
    private int playerCoinPurse;
    private String playerDescription;
    private String playerSharedPrefrences;

    private int classIndex = 0;
    private PlayerClassDictionary classDictionary;
    private ArrayList<Integer> playerInventory;

    //todo: [Medium] Add a settings tab to adjust bgm

    //==========INTENT STRING NAMES  - USED TO DEFINE WHAT TO PASS TO BATTLE ACTIVITY=================//
    public static final String EXTRA_PLAYER = "com.example.dungeoncrawlerframework.EXTRA_PLAYER";
    //==========INTENT STRING NAMES  - USED TO DEFINE WHAT TO PASS TO BATTLE ACTIVITY=================//


    //===================SHARED PREFERENCES STRING NAMES - USED TO NAME PRIMITIVES BEING SAVED===================//
    public static final String PLAYERSAVE = "playersave";

    //===================SHARED PREFERENCES STRING NAMES - USED TO NAME PRIMITIVES BEING SAVED===================//

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);

        initializeViews();

        classDictionary = new PlayerClassDictionary();

        Intent intent = getIntent();
        if(intent.getParcelableExtra(EXTRA_PLAYER) != null){
            newPlayer = intent.getParcelableExtra(EXTRA_PLAYER);
        }else{
            newPlayer = classDictionary.getPlayer(classIndex);
        }

        getPlayerStats();
        updatePlayerStats();

        changeClassButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(classIndex+1>classDictionary.getSize()-1){
                    classIndex = 0;
                }else{
                    classIndex = classIndex + 1;
                }
                newPlayer = classDictionary.getPlayer(classIndex);
                getPlayerStats();
                updatePlayerStats();
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                loadData();
                updatePlayerStats();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startBattleActivity();
            }
        });

        toPlayerInventoryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openPlayerInventory();
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




    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(playerSharedPrefrences,MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonOldPlayer = sharedPreferences.getString(PLAYERSAVE,null);
        Player oldPlayer = gson.fromJson(jsonOldPlayer,Player.class);
        if (oldPlayer!= null) {
            newPlayer = oldPlayer;
            playerHP = newPlayer.getPlayerHealth();
            itemHPEffect = newPlayer.getItemHealthEffect();
            playerMaxHP = newPlayer.getPlayerMaxHealth();
            itemMaxHPEffect = newPlayer.getItemMaxHealthEffect();
            playerAttack = newPlayer.getPlayerAttack();
            itemAttackEffect = newPlayer.getItemAttackEffect();
            playerDefense = newPlayer.getPlayerDefense();
            itemDefenseEffect = newPlayer.getItemDefenseEffect();
            playerEnergy = newPlayer.getPlayerEnergy();
            itemEnergyEffect = newPlayer.getItemEnergyEffect();
            playerMaxEnergy = newPlayer.getPlayerMaxEnergy();
            itemMaxEnergyEffect = newPlayer.getItemMaxEnergyEffect();
            playerSkillPower = newPlayer.getPlayerSkillPower();
            itemSkillPowerEffect = newPlayer.getItemSkillPowerEffect();

            playerInventory = newPlayer.getPlayerInventory();
            killCount = newPlayer.getPlayerKillCount();
            playerExperience = newPlayer.getPlayerKillCount();
            playerLevel = newPlayer.getPlayerLevel();
            playerImageId = newPlayer.getPlayerImageId();
            playerCoinPurse = newPlayer.getPlayerCoinPurse();

            playerLimb1 = newPlayer.getPlayerHead();
            playerLimb2 = newPlayer.getPlayerHand1();
            playerLimb3 = newPlayer.getPlayerTorso();
            playerLimb4 = newPlayer.getPlayerHand2();
            playerLimb5 = newPlayer.getPlayerLegs();
            playerLimb6 = newPlayer.getPlayerFeet();
        }
    }

    private void getPlayerStats() {
        //todo:[Bug] player image overwritten with a monster image ID for some reason upon loading
        Drawable image = ContextCompat.getDrawable(this, newPlayer.getPlayerImageId());
        selectedClassDisplay.setImageDrawable(image);
        playerHP = newPlayer.getPlayerHealth();
        itemHPEffect = newPlayer.getItemHealthEffect();
        playerMaxHP = newPlayer.getPlayerMaxHealth();
        itemMaxHPEffect = newPlayer.getItemMaxHealthEffect();
        playerAttack = newPlayer.getPlayerAttack();
        itemAttackEffect = newPlayer.getItemAttackEffect();
        playerDefense = newPlayer.getPlayerDefense();
        itemDefenseEffect = newPlayer.getItemDefenseEffect();
        playerEnergy = newPlayer.getPlayerEnergy();
        itemEnergyEffect = newPlayer.getItemEnergyEffect();
        playerMaxEnergy = newPlayer.getPlayerMaxEnergy();
        itemMaxEnergyEffect = newPlayer.getItemMaxEnergyEffect();
        playerSkillPower = newPlayer.getPlayerSkillPower();
        itemSkillPowerEffect = newPlayer.getItemSkillPowerEffect();

        killCount = newPlayer.getPlayerKillCount();
        playerExperience = newPlayer.getPlayerExperience();
        playerLevel = newPlayer.getPlayerLevel();
        playerImageId = newPlayer.getPlayerImageId();
        playerDescription = newPlayer.getPlayerDescription();
        playerCoinPurse = newPlayer.getPlayerCoinPurse();
        playerSharedPrefrences = newPlayer.getPlayerSharedPreferences();
        playerInventory = newPlayer.getPlayerInventory();

        playerLimb1 = newPlayer.getPlayerHead();
        playerLimb2 = newPlayer.getPlayerHand1();
        playerLimb3 = newPlayer.getPlayerTorso();
        playerLimb4 = newPlayer.getPlayerHand2();
        playerLimb5 = newPlayer.getPlayerLegs();
        playerLimb6 = newPlayer.getPlayerLegs();
    }

    private void updatePlayerStats(){
        Resources res = getResources();
        playerEnergyDisplay.setText(res.getString(R.string.playerEnergy_StringValue,playerEnergy+itemEnergyEffect,playerMaxEnergy+itemMaxEnergyEffect));
        playerHPDisplay.setText(res.getString(R.string.playerHP_StringValue,playerHP+itemHPEffect,playerMaxHP+itemMaxHPEffect));
        playerDefenseDisplay.setText(res.getString(R.string.playerDefense_StringValue,playerDefense+itemDefenseEffect));
        playerAttackDisplay.setText(res.getString(R.string.playerAttack_StringValue,playerAttack+itemAttackEffect));
        playerSkillPowerDisplay.setText(res.getString(R.string.playerSP_StringValue,playerSkillPower+itemSkillPowerEffect));
        playerInventoryCountDisplay.setText(res.getString(R.string.inventoryCount_StringValue,playerInventory.size()));
        playerDescriptionDisplay.setText(newPlayer.getPlayerDescription());
    }

    private void initializeViews() {
        selectedClassDisplay = findViewById(R.id.limb1ImageView);
        playerHPDisplay = findViewById(R.id.playerStartHPTextView);
        playerAttackDisplay = findViewById(R.id.playerStartAttackTextView);
        playerDefenseDisplay = findViewById(R.id.playerStartDefenseTextView);
        playerEnergyDisplay = findViewById(R.id.playerStartEnergyTextView);
        changeClassButton = findViewById(R.id.changeClassButtonView);
        playerDescriptionDisplay = findViewById(R.id.startingPlayerDescriptionTextView);
        startButton = findViewById(R.id.startButtonView);
        playerSkillPowerDisplay = findViewById(R.id.playerStartSPTextView);
        loadButton = findViewById(R.id.loadButtonView);
        playerInventoryCountDisplay = findViewById(R.id.playerBattleInventoryCountTextView);
        toPlayerInventoryButton = findViewById(R.id.playerInventoryButton);
    }

    public void startBattleActivity(){
        Intent intent = new Intent(this, Battle.class);
        intent.putExtra(EXTRA_PLAYER,newPlayer);
        startActivity(intent);
    }

    public void openPlayerInventory(){
        Intent intent = new Intent(this, PlayerInventory.class);
        intent.putExtra(EXTRA_PLAYER,newPlayer);
        startActivity(intent);
    }
}