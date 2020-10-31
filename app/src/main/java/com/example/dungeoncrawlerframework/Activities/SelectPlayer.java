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
    private int playerMaxHP;
    private int playerEnergy;
    private int playerMaxEnergy;
    private int playerAttack;
    private int playerDefense;
    private int playerSkillPower;
    private Limb playerHead;
    private Limb playerTorso;
    private Limb playerHand1;
    private Limb playerHand2;
    private Limb playerLegs;
    private Limb playerFeet;


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
    //todo:[Medium] should display the hero's latest save data upon start of this activity (i.e. Create an onResume main method)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_player);

        initializeViews();

        classDictionary = new PlayerClassDictionary();
        newPlayer = classDictionary.getPlayer(classIndex);
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
            playerInventory = newPlayer.getPlayerInventory();
            playerEnergy = newPlayer.getPlayerEnergy();
            playerAttack = newPlayer.getPlayerAttack();
            playerDefense = newPlayer.getPlayerDefense();
            playerHP = newPlayer.getPlayerHealth();
            killCount = newPlayer.getPlayerKillCount();
            playerExperience = newPlayer.getPlayerKillCount();
            playerLevel = newPlayer.getPlayerLevel();
            playerImageId = newPlayer.getPlayerImageId();
            playerMaxHP = newPlayer.getPlayerMaxHealth();
            playerMaxEnergy = newPlayer.getPlayerMaxEnergy();
            playerSkillPower = newPlayer.getPlayerSkillPower();
            playerCoinPurse = newPlayer.getPlayerCoinPurse();
        }
    }

    private void getPlayerStats() {
        //todo:[BUG] player image overwritten with a monster image ID for some reason upon loading
        Drawable image = ContextCompat.getDrawable(this, newPlayer.getPlayerImageId());
        selectedClassDisplay.setImageDrawable(image);
        playerEnergy = newPlayer.getPlayerEnergy();
        playerAttack = newPlayer.getPlayerAttack();
        playerDefense = newPlayer.getPlayerDefense();
        playerHP = newPlayer.getPlayerHealth();
        killCount = newPlayer.getPlayerKillCount();
        playerExperience = newPlayer.getPlayerExperience();
        playerLevel = newPlayer.getPlayerLevel();
        playerImageId = newPlayer.getPlayerImageId();
        playerMaxHP = newPlayer.getPlayerMaxHealth();
        playerMaxEnergy = newPlayer.getPlayerMaxEnergy();
        playerDescription = newPlayer.getPlayerDescription();
        playerSkillPower = newPlayer.getPlayerSkillPower();
        playerCoinPurse = newPlayer.getPlayerCoinPurse();
        playerSharedPrefrences = newPlayer.getPlayerSharedPreferences();
        playerInventory = new ArrayList<Integer>();
    }

    private void updatePlayerStats(){
        Resources res = getResources();
        playerEnergyDisplay.setText(res.getString(R.string.playerEnergy_StringValue,playerEnergy,playerMaxEnergy));
        playerHPDisplay.setText(res.getString(R.string.playerHP_StringValue,playerHP,playerMaxHP));
        playerDefenseDisplay.setText(res.getString(R.string.playerDefense_StringValue,playerDefense));
        playerAttackDisplay.setText(res.getString(R.string.playerAttack_StringValue,playerAttack));
        playerDescriptionDisplay.setText(newPlayer.getPlayerDescription());
        playerSkillPowerDisplay.setText(res.getString(R.string.playerSP_StringValue,playerSkillPower));
        playerInventoryCountDisplay.setText(res.getString(R.string.inventoryCount_StringValue,playerInventory.size()));
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