package com.example.dungeoncrawlerframework.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.dungeoncrawlerframework.DungeonStructure.DungeonFloor;
import com.example.dungeoncrawlerframework.DungeonStructure.DungeonFloorDictionary;
import com.example.dungeoncrawlerframework.Items.Item;
import com.example.dungeoncrawlerframework.Items.ItemDictionary;
import com.example.dungeoncrawlerframework.Limbs.Limb;
import com.example.dungeoncrawlerframework.Monsters.Monster;
import com.example.dungeoncrawlerframework.Monsters.MonsterDictionary;
import com.example.dungeoncrawlerframework.Players.Player;
import com.example.dungeoncrawlerframework.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

import static com.example.dungeoncrawlerframework.Activities.SelectPlayer.EXTRA_PLAYER;
import static com.example.dungeoncrawlerframework.Activities.SelectPlayer.PLAYERSAVE;

public class Battle extends AppCompatActivity {

    //==============MONSTER RELATED VARS================//
    private int floorNumber=0;
    private int roomNumber=0;
    //todo: [Critical] refactor so that it takes selected floor and room as intents from another activity (new actvity or Select Player Activity doesnt matter)
    private final DungeonFloorDictionary dungeonFloorDictionary = new DungeonFloorDictionary();
    private DungeonFloor dungeonFloor = dungeonFloorDictionary.getDungeonFloor(floorNumber);
    private ArrayList<Monster> floorMonsters = dungeonFloor.getFloorMonsters();
    private MonsterDictionary monsterDictionary;
    private Monster opponentMonster;
    private Drawable monsterImage;
    private ImageView monsterImageDisplay;
    private TextView monsterAttackDisplay;
    private TextView monsterDefenseDisplay;
    private TextView monsterHpDisplay;
    private TextView floorRoomDisplay;

    private int monsterHp;
    private int monsterExp;
    private int monsterAttack;
    private int monsterDefense;
    private String monsterName;
    private int monsterDropItemIndex;
    private double monsterDropChance;
    private int monsterActualGoldDrop;
    //==============MONSTER RELATED VARS================//

    //=======PLAYER RELATED VARS=================//
    private Button saveButton;
    private Button attackButton;
    private Button healButton;
    private Button endTurnButton;
    private Button leaveDungeonButton;
    private ImageView playerImageDisplay;
    private TextView killCountDisplay;
    private TextView combatLogDisplay;
    private TextView playerExperienceDisplay;
    private TextView playerHPDisplay;
    private TextView playerAttackDisplay;
    private TextView playerDefenseDisplay;
    private TextView playerLevelDisplay;
    private TextView playerEnergyDisplay;
    private TextView playerSkillPowerDisplay;
    private TextView playerCoinPurseDisplay;
    private TextView currentInventoryCountDisplay;

    private Player newPlayer;
    private Drawable playerImage;

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

    private int playerExperience;
    private int playerLevel;
    private int killCount;
    private int playerCoinPurse;

    private String playerDescription;
    private String playerSharedPrefrences;
    private ArrayList<Integer> playerInventory;
    private ArrayList<Limb> playerBodyParts;

    Limb playerLimb1;
    Limb playerLimb2;
    Limb playerLimb3;
    Limb playerLimb4;
    Limb playerLimb5;
    Limb playerLimb6;

    //============================PLAYER RELATED VARS==================================//

    //============================BATTLE RELATED VARS==================================//
    private TextView turnCounterDisplay;
    private int damage;
    private int healAmount;
    private int playerDamage;
    private int turnCounter=1;
    private SoundPool soundPool;
    private int sound1,sound2,sound3,sound4,sound5,sound6;
    private boolean loaded;
    Resources res;
    //============================BATTLE RELATED VARS==================================//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_battle);
        initializeViews();


        Intent intent = getIntent();
        //player starts with max HP and max Energy upon entering the dungeon
        newPlayer = intent.getParcelableExtra(EXTRA_PLAYER);
        playerInventory = newPlayer.getPlayerInventory();
        Intent finishPriorActivities = new Intent("finish_activity");
        sendBroadcast(finishPriorActivities);

        playerLimb1 = newPlayer.getPlayerHead();
        playerLimb2 = newPlayer.getPlayerHand1();
        playerLimb3 = newPlayer.getPlayerTorso();
        playerLimb4 = newPlayer.getPlayerHand2();
        playerLimb5 = newPlayer.getPlayerLegs();
        playerLimb6 = newPlayer.getPlayerLegs();

        getPlayerStats();
        updatePlayerViews();

        opponentMonster = floorMonsters.get(roomNumber);
        getMonsterStats();
        updateMonsterViews();

        loadSounds();

        attackButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            attackMonster();
            }
        });

        healButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                healPlayer();
            }
        });

        endTurnButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (monsterHp<=0){
                    //todo:[Medium] get the next room's event* can be good or bad event
                    //good event = free item/gold; bad event = new monster to fight;

                    if(roomNumber+1<floorMonsters.size()){
                        roomNumber = roomNumber +1;
                    }else if(roomNumber+1>=floorMonsters.size()){

                        floorNumber = floorNumber+1;
                        dungeonFloor = dungeonFloorDictionary.getDungeonFloor(floorNumber);
                        floorMonsters = dungeonFloor.getFloorMonsters();
                        roomNumber = 0;
                    }
                    saveData();
                    opponentMonster = floorMonsters.get(roomNumber);
                    getMonsterStats();
                    updateMonsterViews();
                    resetTurnCounter();
                }else{
                    updateTurnCounter();
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        leaveDungeonButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startSelectPlayerActivity();
            }
        });
    }




    public void saveData() {

        SharedPreferences sharedPreferences = getSharedPreferences(playerSharedPrefrences, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonPlayer = gson.toJson(newPlayer);
        editor.putString(PLAYERSAVE,jsonPlayer);

        //fixme:[Bug] monster Image id's from the dictionary are overwriting the Shared Preferences on occasion
        editor.apply();
        Toast.makeText(this,"Data saved",Toast.LENGTH_SHORT).show();

    }

    public void startSelectPlayerActivity(){

        Intent intent = new Intent(this,SelectPlayer.class);
        intent.putExtra(EXTRA_PLAYER,newPlayer);
        startActivity(intent);

    }
    private void loadSounds() {
        //fixme:[Bug] the sound is adjusted using the System sounds bar and not the Media volumes
        //creates a pool of sounds
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(6)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else{
            soundPool = new SoundPool(6, AudioManager.STREAM_MUSIC,0);
        }

        sound1 = soundPool.load(this,R.raw.sound1, 1);
        sound2 = soundPool.load(this,R.raw.sound2, 1);
        sound3 = soundPool.load(this,R.raw.sound3, 1);
        /*

        //======DO NOT REMOVE THIS CODE IS OKAY! UNCOMMENT WHEN YOU NEED BGM!!!=====================

        sound4 = soundPool.load(this,R.raw.sound4, 1);
        sound5 = soundPool.load(this,R.raw.sound5, 1);
        sound6 = soundPool.load(this,R.raw.sound6, 1);

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId,
                                       int status) {
                soundPool.play(sound3, 1, 1, 0, -1, 1);
            }
        });

        //======DO NOT REMOVE THIS CODE IS OKAY! UNCOMMENT WHEN YOU NEED BGM!!!=====================

        */
    }


    private void initializeViews() {
        res = getResources();
        //player views
        playerImageDisplay = findViewById(R.id.leftArmImageView);
        playerHPDisplay = findViewById(R.id.playerHPTextVIew);
        playerAttackDisplay = findViewById(R.id.playerAttackTextView);
        playerDefenseDisplay = findViewById(R.id.playerDefenseTextView);
        playerLevelDisplay = findViewById(R.id.playerLevelTextView);
        playerExperienceDisplay = findViewById(R.id.playerExpTextView);
        playerEnergyDisplay = findViewById(R.id.playerEnergyTextView);
        playerSkillPowerDisplay = findViewById(R.id.playerStartSPTextView);
        killCountDisplay = findViewById(R.id.killCountTextView);
        killCountDisplay.setText(res.getString(R.string.killCount_StringValue,killCount));
        attackButton = findViewById(R.id.atkButtonView);
        healButton = findViewById(R.id.healButtonView);
        playerCoinPurseDisplay = findViewById(R.id.playerCoinPurseTextView);
        //battle views
        saveButton = findViewById(R.id.saveButtonView);
        saveButton.setVisibility(View.VISIBLE);
        leaveDungeonButton = findViewById(R.id.leaveDungeonButtonView);
        endTurnButton = findViewById(R.id.endTurnButtonView);
        endTurnButton.setText(res.getString(R.string.endTurn_StringValue));
        endTurnButton.setVisibility(View.VISIBLE);
        turnCounterDisplay = findViewById(R.id.turnCounterTextView);
        turnCounterDisplay.setText(res.getString(R.string.turnCounter_StringValue,turnCounter));
        combatLogDisplay = findViewById(R.id.combatLogTextView);
        combatLogDisplay.setMovementMethod(new ScrollingMovementMethod());
        //monster Views
        monsterHpDisplay = findViewById(R.id.monsterHpTextView);
        monsterAttackDisplay = findViewById(R.id.monsterAttackTextView);
        monsterDefenseDisplay = findViewById(R.id.monsterDefenseTextView);
        monsterImageDisplay = findViewById(R.id.monsterImageView);
        currentInventoryCountDisplay = findViewById(R.id.playerBattleInventoryCountTextView);
        floorRoomDisplay = findViewById(R.id.floorRoomTextView);
    }

    //BATTLE METHODS


    private void getPlayerStats() {
        playerImage = ContextCompat.getDrawable(this, newPlayer.getPlayerImageId());
        playerHP = newPlayer.getPlayerHealth();
        playerMaxHP = newPlayer.getPlayerMaxHealth();
        playerEnergy = newPlayer.getPlayerEnergy();
        playerMaxEnergy = newPlayer.getPlayerMaxEnergy();
        playerAttack = newPlayer.getPlayerAttack();
        playerDefense = newPlayer.getPlayerDefense();
        playerSkillPower = newPlayer.getPlayerSkillPower();
        playerExperience = newPlayer.getPlayerExperience();
        playerLevel = newPlayer.getPlayerLevel();
        killCount = newPlayer.getPlayerKillCount();
        playerCoinPurse = newPlayer.getPlayerCoinPurse();
        playerDescription = newPlayer.getPlayerDescription();
        playerBodyParts = newPlayer.getPlayerBodyParts();
        playerInventory = newPlayer.getPlayerInventory();

        itemHPEffect = newPlayer.getItemHealthEffect();
        itemMaxHPEffect = newPlayer.getItemMaxHealthEffect();
        itemEnergyEffect = newPlayer.getItemEnergyEffect();
        itemMaxEnergyEffect = newPlayer.getItemMaxEnergyEffect();
        itemAttackEffect = newPlayer.getItemAttackEffect();
        itemDefenseEffect = newPlayer.getItemDefenseEffect();
        itemSkillPowerEffect = newPlayer.getItemSkillPowerEffect();



        //BATTLE CALCULATIONS RELATIVE TO PLAYER
        //todo: [Medium] this cannot be a SOLID coding practice, extract this into its own method
        //todo: [Medium] refactor this so that the damage is a function of playerAttack and itemEffects on player Attack
        if(playerAttack + itemAttackEffect - monsterDefense <=0){
            damage = 1;
        }else{
            damage = playerAttack + itemAttackEffect- monsterDefense;
        }

        healAmount = playerSkillPower + itemSkillPowerEffect + 1;

        if(playerDefense + itemDefenseEffect >= monsterAttack){
            playerDamage = 1;
        }else{
            playerDamage = monsterAttack - playerDefense - itemDefenseEffect;
        }
    }

    private void updatePlayerViews() {

        playerImageDisplay.setImageDrawable(playerImage);
        playerImageDisplay.setVisibility(View.VISIBLE);
        playerHPDisplay.setText(res.getString(R.string.playerHP_StringValue,playerHP,playerMaxHP));
            if(Math.round((playerMaxHP/2))>=playerHP){
            playerHPDisplay.setTextColor(Color.rgb(255,0,0));
        }else{
            playerHPDisplay.setTextColor(Color.rgb(0,0,0));
        }
        playerEnergyDisplay.setText(res.getString(R.string.playerEnergy_StringValue,playerEnergy+itemEnergyEffect,playerMaxEnergy+itemMaxEnergyEffect));
        playerAttackDisplay.setText(res.getString(R.string.playerAttack_StringValue,playerAttack+itemAttackEffect));
        playerDefenseDisplay.setText(res.getString(R.string.playerDefense_StringValue,playerDefense+itemDefenseEffect));
        playerSkillPowerDisplay.setText(res.getString(R.string.playerSP_StringValue,playerSkillPower+itemSkillPowerEffect));

        playerExperienceDisplay.setText(res.getString(R.string.playerExperience_StringValue,playerExperience));
        playerLevelDisplay.setText(res.getString(R.string.playerLevel_StringValue,playerLevel));
        killCountDisplay.setText(res.getString(R.string.killCount_StringValue,killCount));
        playerCoinPurseDisplay.setText(res.getString(R.string.playerCoinPurse_StringValue,playerCoinPurse));
        playerSharedPrefrences = newPlayer.getPlayerSharedPreferences();
        if(playerInventory != null) {
            currentInventoryCountDisplay.setText(res.getString(R.string.inventoryCount_StringValue, playerInventory.size()));
        }else{
            currentInventoryCountDisplay.setText(res.getString(R.string.inventoryCount_StringValue, 0));
        }
        attackButton.setText(res.getString(R.string.attackButton_TextValue,damage));
        healButton.setText(res.getString(R.string.healButton_StringValue,healAmount));
    }
    //Get Player Equipment Method (NEEDS REFACTORING)
    /*
    private void getPlayerEquipment() {
        try {
            playerLimb1EquippedItem = playerLimb1.getEquippedItem();
            Drawable equipment1 = ContextCompat.getDrawable(this, playerLimb1EquippedItem.getItemImageId());
            playerLimb1EquippmentDisplay.setImageDrawable(equipment1);
        }catch(NullPointerException e){
            Log.d("Battle.Limb1.GetEquip","No equippment found for player Limb 1");
        }

        try {
            playerLimb2EquippedItem = playerLimb2.getEquippedItem();
            Drawable equipment2 = ContextCompat.getDrawable(this, playerLimb2EquippedItem.getItemImageId());
            playerLimb2EquippmentDisplay.setImageDrawable(equipment2);
        }catch(NullPointerException e){
            Log.d("Battle.Limb2.GetEquip","No equipment found for player Limb 2");
        }
        getPlayerStats();
    }
    */

    private void setPlayerStats(){
        newPlayer.setPlayerExperience(playerExperience);
        newPlayer.setPlayerAttack(playerAttack);
        newPlayer.setPlayerLevel(playerLevel);
        newPlayer.setPlayerHealth(playerHP);
        newPlayer.setPlayerDefense(playerDefense);
        newPlayer.setPlayerKillCount(killCount);
        newPlayer.setPlayerMaxHealth(playerMaxHP);
        newPlayer.setPlayerEnergy(playerEnergy);
        newPlayer.setPlayerDescription(playerDescription);
        newPlayer.setPlayerSkillPower(playerSkillPower);
        newPlayer.setPlayerCoinPurse(playerCoinPurse);
        newPlayer.setItemHealthEffect(itemHPEffect);
        newPlayer.setItemMaxHealthEffect(itemMaxHPEffect);
        newPlayer.setItemEnergyEffect(itemEnergyEffect);
        newPlayer.setItemMaxEnergyEffect(itemMaxEnergyEffect);
        newPlayer.setItemAttackEffect(itemAttackEffect);
        newPlayer.setItemDefenseEffect(itemDefenseEffect);
        newPlayer.setItemSkillPowerEffect(itemSkillPowerEffect);
    }


    //todo:[Low] refactor to focus on targeting of a specific monster
    private void attackMonster(){

        if(turnCounter % 2 == 1 && playerEnergy > 0 && monsterHp > 0 && playerHP > 0){
            YoYo.with(Techniques.Flash)
                    .duration(300)
                    .repeat(0)
                    .playOn(monsterImageDisplay);
            monsterHp = monsterHp-damage;
            playerEnergy = playerEnergy - 1;
            setPlayerStats();
            getPlayerStats();
            updatePlayerViews();
            soundPool.play(sound1, 1, 1, 0, 0, 1);
            combatLogDisplay.append(res.getString(R.string.playerAttackMonster,monsterName,damage));
            monsterHpDisplay.setText(res.getString(R.string.monsterHP_StringValue,monsterHp));
            if (monsterHp<=0){
                killMonster();
                setPlayerStats();
                getPlayerStats();
                updatePlayerViews();
            }
        }else if(playerEnergy<=0){
            YoYo.with(Techniques.Flash)
                    .duration(1000)
                    .repeat(0)
                    .playOn(endTurnButton);
            YoYo.with(Techniques.Flash)
                    .duration(1000)
                    .repeat(0)
                    .playOn(playerEnergyDisplay);

        }else if(playerHP<=0){
            YoYo.with(Techniques.Flash)
                    .duration(1000)
                    .repeat(0)
                    .playOn(leaveDungeonButton);
        }else if(monsterHp<=0){
            YoYo.with(Techniques.Flash)
                    .duration(1000)
                    .repeat(0)
                    .playOn(endTurnButton);
        }

    }

    private void healPlayer() {

        if (turnCounter%2 == 1 && playerEnergy > 0 && playerHP > 0){
            if (playerHP + healAmount > playerMaxHP){
                playerHP = playerMaxHP;
                playerEnergy = playerEnergy - 1;
                setPlayerStats();
                getPlayerStats();
                updatePlayerViews();
            }else{
                playerHP = playerHP + healAmount;
                playerEnergy = playerEnergy - 1;
                setPlayerStats();
                getPlayerStats();
                updatePlayerViews();
            }
            soundPool.play(sound2, 1, 1, 0, 0, 1);
            combatLogDisplay.append(res.getString(R.string.playerHealSelf,healAmount));
            YoYo.with(Techniques.Tada)
                    .duration(300)
                    .repeat(0)
                    .playOn(playerImageDisplay);
        }else if (playerEnergy <=0) {
            YoYo.with(Techniques.Flash)
                    .duration(1000)
                    .repeat(0)
                    .playOn(endTurnButton);
            YoYo.with(Techniques.Flash)
                    .duration(1000)
                    .repeat(0)
                    .playOn(playerEnergyDisplay);

        }else if (playerHP<=0){
            YoYo.with(Techniques.Flash)
                    .duration(1000)
                    .repeat(0)
                    .playOn(leaveDungeonButton);
        }

    }

    private void getMonsterStats(){
        monsterImage = ContextCompat.getDrawable(this, opponentMonster.getMonsterImageId());
        monsterName = res.getString(opponentMonster.getName());
        monsterHp = opponentMonster.getHealthPoints();
        monsterAttack = opponentMonster.getAttackPower();
        monsterDefense= opponentMonster.getDefensePower();
        monsterExp = opponentMonster.getExperience();
        monsterDropItemIndex = opponentMonster.getItemIndex();
        monsterDropChance = opponentMonster.getItemDropChance();
        monsterActualGoldDrop = opponentMonster.dropGold();
    }

    private void updateMonsterViews(){
        monsterImageDisplay.setImageDrawable(monsterImage);
        monsterHpDisplay.setText(res.getString(R.string.monsterHP_StringValue,monsterHp));
        monsterAttackDisplay.setText(res.getString(R.string.monsterAttack_StringValue,monsterAttack));
        monsterDefenseDisplay.setText(res.getString(R.string.monsterDefense_StringValue,monsterDefense));
        combatLogDisplay.setText(res.getString(R.string.wildMonsterAppears,monsterName));
        monsterImageDisplay.animate().alpha(1.0f).setDuration(100);
        monsterHpDisplay.setVisibility(View.VISIBLE);
        monsterAttackDisplay.setVisibility(View.VISIBLE);
        monsterDefenseDisplay.setVisibility(View.VISIBLE);
        floorRoomDisplay.setText(res.getString(R.string.floorRoom_StringValue,floorNumber+1,roomNumber+1));
    }




    private void attackPlayer(Player newPlayer){
        YoYo.with(Techniques.Tada)
                .duration(100)
                .repeat(0)
                .playOn(monsterImageDisplay);
        res = getResources();
        playerHP = playerHP - playerDamage;
        setPlayerStats();
        getPlayerStats();
        updatePlayerViews();
        turnCounter++;
        turnCounterDisplay.setText(res.getString(R.string.turnCounter_StringValue,turnCounter));
        combatLogDisplay.setText(res.getString(R.string.monsterAttackPlayer,monsterName,playerDamage));

        YoYo.with(Techniques.Flash)
                .duration(300)
                .repeat(0)
                .playOn(playerImageDisplay);
        if (playerHP<=0){
            playerDeath();

        }
    }

    private void playerDeath() {

        resetTurnCounter();
        getPlayerStats();
        updatePlayerViews();
        playerImageDisplay.setVisibility(View.INVISIBLE);
        endTurnButton.setVisibility(View.INVISIBLE);
        saveButton.setVisibility(View.INVISIBLE);
        combatLogDisplay.append(res.getString(R.string.playerDeath));
    }

    private void killMonster() {
        res = getResources();
        killCount = killCount + 1;
        playerExperience = playerExperience + monsterExp;
        playerCoinPurse = monsterActualGoldDrop + playerCoinPurse;
        setPlayerStats();
        getPlayerStats();
        updatePlayerViews();
        combatLogDisplay.setText(res.getString(R.string.monsterDeath,monsterName,monsterExp,monsterActualGoldDrop));
        monsterImageDisplay.animate().alpha(0.0f).setDuration(600);
        monsterHpDisplay.setVisibility(View.INVISIBLE);
        monsterAttackDisplay.setVisibility(View.INVISIBLE);
        monsterDefenseDisplay.setVisibility(View.INVISIBLE);
        if(newPlayer.checkLevel(playerExperience) != -1) {
            if(newPlayer.checkLevel(playerExperience) > newPlayer.getPlayerLevel()){
                newPlayer.levelUp(newPlayer.checkLevel(playerExperience));
                getPlayerStats();
                updatePlayerViews();
            }

        }else{
            Log.e("CheckLevelError","This thing ducked up in the battle activity check level step");
        }
        if(monsterDropCheck(monsterDropChance)){
            newPlayer.addItem2Inventory(monsterDropItemIndex);
            ItemDictionary itemDictionary = new ItemDictionary();
            Item item = itemDictionary.getItem(monsterDropItemIndex);
            String itemName = res.getString(item.getItemName());
            combatLogDisplay.append(res.getString(R.string.dropSuccess_StringValue,monsterName,itemName));
        }
    }

    private boolean monsterDropCheck(double monsterDropChance){
        boolean dropSuccess;
        Random r = new Random();
        float chance = r.nextFloat();
        dropSuccess = chance <= monsterDropChance;
        return dropSuccess;
    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private void updateTurnCounter(){

        turnCounter = turnCounter + 1;
        turnCounterDisplay.setText(res.getString(R.string.turnCounter_StringValue,turnCounter));
        playerEnergy = playerMaxEnergy;
        if (turnCounter%2==0){
            attackPlayer(newPlayer);
        }
    }

    private void resetTurnCounter(){
        turnCounter = 1;
        //todo: [Low] player energy update shouldn't be here...I think
        playerEnergy = playerMaxEnergy;
        turnCounterDisplay.setText(res.getString(R.string.turnCounter_StringValue,turnCounter));
    }


}