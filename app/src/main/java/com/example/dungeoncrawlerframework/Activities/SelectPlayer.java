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

import com.example.dungeoncrawlerframework.Limbs.Feet;
import com.example.dungeoncrawlerframework.Limbs.Hand;
import com.example.dungeoncrawlerframework.Limbs.Head;
import com.example.dungeoncrawlerframework.Limbs.Legs;
import com.example.dungeoncrawlerframework.Limbs.Torso;
import com.example.dungeoncrawlerframework.Players.Player;
import com.example.dungeoncrawlerframework.Players.PlayerClassDictionary;
import com.example.dungeoncrawlerframework.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
    private Head playerHead;
    private Torso playerTorso;
    private Hand playerHand1;
    private Hand playerHand2;
    private Legs playerLegs;
    private Feet playerFeet;


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
    //todo: [Low] refactor Select Player to pass player parcelable rather than just primitives

    //==========INTENT STRING NAMES  - USED TO DEFINE WHAT TO PASS TO BATTLE ACTIVITY=================//
    public static final String EXTRA_ENERGY = "com.example.dungeoncrawlerframework.EXTRA_ENERGY";
    public static final String EXTRA_ATTACK = "com.example.dungeoncrawlerframework.EXTRA_ATTACK";
    public static final String EXTRA_DEFENSE = "com.example.dungeoncrawlerframework.EXTRA_DEFENSE";
    public static final String EXTRA_HP = "com.example.dungeoncrawlerframework.EXTRA_HP";
    public static final String EXTRA_LIMB1 = "com.example.dungeoncrawlerframework.EXTRA_LIMB1";
    public static final String EXTRA_PLAYERDESCRIPTION="com.example.dungeoncrawlerframework.EXTRA_PLAYERDESCRIPTION";
    public static final String EXTRA_SKILLPOWER = "com.example.dungeoncrawlerframework.EXTRA_SKILLPOWER";
    public static final String EXTRA_KILLCOUNT = "com.example.dungeoncrawlerframework.EXTRA_KILLCOUNT";
    public static final String EXTRA_EXPERIENCE = "com.example.dungeoncrawlerframework.EXTRA_EXPERIENCE";
    public static final String EXTRA_LEVEL = "com.example.dungeoncrawlerframework.EXTRA_LEVEL";
    public static final String EXTRA_COINPURSE = "com.example.dungeoncrawlerframework.EXTRA_COINPURSE";
    public static final String EXTRA_MAXENERGY = "EXTRA_MAXENERGY";
    public static final String EXTRA_MAXHP = "com.example.dungeoncrawlerframework.EXTRA_MAXHP";
    public static final String EXTRA_SHAREDPREF = "com.example.dungeoncrawlerframework.EXTRA_SHAREDPREF";
    public static final String EXTRA_PLAYERINVENTORY = "com.example.dungeoncrawlerframework.EXTRA_PLAYERINVENTORY";
    //==========INTENT STRING NAMES  - USED TO DEFINE WHAT TO PASS TO BATTLE ACTIVITY=================//


    //===================SHARED PREFERENCES STRING NAMES - USED TO NAME PRIMITIVES BEING SAVED===================//
    public static final String PLAYERENERGY = "playerenergy";
    public static final String PLAYERATTACK = "playerattack";
    public static final String PLAYERDEFENSE = "playerdefense";
    public static final String PLAYERHP = "playerhp";
    public static final String KILLCOUNT = "killcount";
    public static final String PLAYEREXPERIENCE = "playerexperience";
    public static final String PLAYERLEVEL = "playerlevel";
    public static final String PLAYERMAXHP = "playermaxhp";
    public static final String PLAYERMAXENERGY = "playermaxenergy";
    public static final String PLAYERSKILLPOWER = "playerskillpower";
    public static final String PLAYERCOINPURSE = "playercoinpurse";
    public static final String PLAYERIMAGEID = "playerimageid";
    public static final String PLAYERINVENTORY = "playerinventory";
    public static final String PLAYERHEAD = "playerhead";
    public static final String PLAYERTORSO = "playertorso";
    public static final String PLAYERHAND1 = "playerhand1";
    public static final String PLAYERHAND2 = "playerhand2";
    public static final String PLAYERLEGS = "playerlegs";
    public static final String PLAYERFEET = "playerfeet";

    //===================SHARED PREFERENCES STRING NAMES - USED TO NAME PRIMITIVES BEING SAVED===================//

    @Override
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
        String json = sharedPreferences.getString(PLAYERINVENTORY,null);
        Type type = new TypeToken<ArrayList<Integer>>() {}.getType();

        playerInventory = gson.fromJson(json,type);
        if (playerInventory == null){
            playerInventory = new ArrayList<Integer>();
        }
        playerEnergy = sharedPreferences.getInt(PLAYERMAXENERGY,playerEnergy);
        playerAttack = sharedPreferences.getInt(PLAYERATTACK,playerAttack);
        playerDefense = sharedPreferences.getInt(PLAYERDEFENSE,playerDefense);
        playerHP = sharedPreferences.getInt(PLAYERMAXHP,playerHP);
        killCount = sharedPreferences.getInt(KILLCOUNT,killCount);
        playerExperience = sharedPreferences.getInt(PLAYEREXPERIENCE,playerExperience);
        playerLevel = sharedPreferences.getInt(PLAYERLEVEL,playerLevel);
        playerImageId = sharedPreferences.getInt(PLAYERIMAGEID, playerImageId);
        playerMaxHP = sharedPreferences.getInt(PLAYERMAXHP, playerMaxHP);
        playerMaxEnergy = sharedPreferences.getInt(PLAYERMAXENERGY, playerMaxEnergy);
        playerSkillPower = sharedPreferences.getInt(PLAYERSKILLPOWER, playerSkillPower);
        playerCoinPurse = sharedPreferences.getInt(PLAYERCOINPURSE, playerCoinPurse);

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
        intent.putExtra(EXTRA_HP,playerHP);
        intent.putExtra(EXTRA_ATTACK,playerAttack);
        intent.putExtra(EXTRA_DEFENSE,playerDefense);
        intent.putExtra(EXTRA_ENERGY,playerEnergy);
        intent.putExtra(EXTRA_LIMB1, playerImageId);
        intent.putExtra(EXTRA_PLAYERDESCRIPTION,playerDescription);
        intent.putExtra(EXTRA_SKILLPOWER,playerSkillPower);
        intent.putExtra(EXTRA_KILLCOUNT,killCount);
        intent.putExtra(EXTRA_EXPERIENCE,playerExperience);
        intent.putExtra(EXTRA_LEVEL,playerLevel);
        intent.putExtra(EXTRA_COINPURSE,playerCoinPurse);
        intent.putExtra(EXTRA_MAXENERGY,playerMaxEnergy);
        intent.putExtra(EXTRA_MAXHP,playerMaxHP);
        intent.putExtra(EXTRA_SHAREDPREF,playerSharedPrefrences);
        intent.putExtra(EXTRA_PLAYERINVENTORY,playerInventory);
        startActivity(intent);
    }

    public void openPlayerInventory(){
        //todo:[High] get and the player limbs and generate the corresponding intent variables
        //todo:[High] pass the limbs as a gson intent to the InventoryMenu activity
        Intent intent = new Intent(this, PlayerInventory.class);
        intent.putExtra(EXTRA_PLAYERINVENTORY,playerInventory);
        startActivity(intent);
    }
}