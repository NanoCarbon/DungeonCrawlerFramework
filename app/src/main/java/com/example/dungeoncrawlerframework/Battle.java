package com.example.dungeoncrawlerframework;

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
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

import static com.example.dungeoncrawlerframework.SelectPlayer.KILLCOUNT;
import static com.example.dungeoncrawlerframework.SelectPlayer.PLAYERATTACK;
import static com.example.dungeoncrawlerframework.SelectPlayer.PLAYERCOINPURSE;
import static com.example.dungeoncrawlerframework.SelectPlayer.PLAYERDEFENSE;
import static com.example.dungeoncrawlerframework.SelectPlayer.PLAYERENERGY;
import static com.example.dungeoncrawlerframework.SelectPlayer.PLAYEREXPERIENCE;
import static com.example.dungeoncrawlerframework.SelectPlayer.PLAYERHP;
import static com.example.dungeoncrawlerframework.SelectPlayer.PLAYERIMAGEID;
import static com.example.dungeoncrawlerframework.SelectPlayer.PLAYERINVENTORY;
import static com.example.dungeoncrawlerframework.SelectPlayer.PLAYERLEVEL;
import static com.example.dungeoncrawlerframework.SelectPlayer.PLAYERMAXENERGY;
import static com.example.dungeoncrawlerframework.SelectPlayer.PLAYERMAXHP;
import static com.example.dungeoncrawlerframework.SelectPlayer.PLAYERSKILLPOWER;

public class Battle extends AppCompatActivity {

    //monster related fields
    private MonsterDictionary monsterDictionary;
    private Monster opponent;
    private ImageView monsterImageDisplay;
    private TextView monsterAttackDisplay;
    private TextView monsterDefenseDisplay;
    private TextView monsterHpDisplay;
    private int monsterHp;
    private int monsterExp;
    private int monsterAttack;
    private int monsterDefense;
    private String monsterName;
    private int monsterDropItemIndex;
    private double monsterDropChance;
    private int monsterActualGoldDrop;
    //player related fields
    private Button saveButton;
    private Button attackButton;
    private Button healButton;
    private Button endTurnButton;
    private Button leaveDungeonButton;
    private ImageView leftArmDisplay;
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
    private ImageView playerLimb1EquippmentDisplay;
    private ImageView playerLimb2EquippmentDisplay;
    private ImageView playerLimb3EquippmentDisplay;
    private ImageView playerLimb4EquippmentDisplay;
    private ImageView playerLimb5EquippmentDisplay;
    private ImageView playerLimb6EquippmentDisplay;
    private ImageView playerLimb7EquippmentDisplay;
    private ImageView playerLimb8EquippmentDisplay;
    private ImageView playerLimb9EquippmentDisplay;
    private Player newPlayer;
    private String playerDescription;
    private int playerEnergy;
    private int playerAttack;
    private int playerDefense;
    private int playerHP;
    private int killCount;
    private int playerExperience;
    private int playerLevel;
    private int playerMaxHP;
    private int playerMaxEnergy;
    private int playerSkillPower;
    private int playerCoinPurse;
    private String playerSharedPreferences;

    private Limb playerLimb1;
    private Item playerLimb1EquippedItem;

    private Limb playerLimb2;
    private Item playerLimb2EquippedItem;

    //calculated fields
    private TextView turnCounterDisplay;
    private int damage;
    private int healAmount;
    private int playerDamage;
    private int turnCounter=1;
    private SoundPool soundPool;
    private int sound1,sound2,sound3,sound4,sound5,sound6;
    private boolean loaded;
    private ArrayList<Integer> playerInventory;
    Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_battle);

        initializeViews();

        opponent = createMonster(0);

        Intent intent = getIntent();
        //player starts with max HP and max Energy upon entering the dungeon
        newPlayer = new Player(
                intent.getIntExtra(SelectPlayer.EXTRA_MAXHP,0),
                intent.getIntExtra(SelectPlayer.EXTRA_ATTACK,0),
                intent.getIntExtra(SelectPlayer.EXTRA_DEFENSE,0),
                intent.getIntExtra(SelectPlayer.EXTRA_MAXENERGY,0),
                intent.getIntExtra(SelectPlayer.EXTRA_LIMB1,0),
                intent.getStringExtra(SelectPlayer.EXTRA_PLAYERDESCRIPTION),
                intent.getIntExtra(SelectPlayer.EXTRA_SKILLPOWER,0),
                intent.getStringExtra(SelectPlayer.EXTRA_SHAREDPREF));
        newPlayer.setPlayerLevel(intent.getIntExtra(SelectPlayer.EXTRA_LEVEL,0));
        newPlayer.setPlayerExperience(intent.getIntExtra(SelectPlayer.EXTRA_EXPERIENCE,0));
        newPlayer.setPlayerKillCount(intent.getIntExtra(SelectPlayer.EXTRA_KILLCOUNT,0));
        newPlayer.setPlayerCoinPurse(intent.getIntExtra(SelectPlayer.EXTRA_COINPURSE,0));
        newPlayer.setPlayerMaxHealth(intent.getIntExtra(SelectPlayer.EXTRA_MAXHP,newPlayer.getPlayerMaxHealth()));
        newPlayer.setPlayerMaxEnergy(intent.getIntExtra(SelectPlayer.EXTRA_MAXENERGY,newPlayer.getPlayerMaxEnergy()));
        playerInventory = intent.getIntegerArrayListExtra(SelectPlayer.EXTRA_PLAYERINVENTORY);
        newPlayer.setPlayerInventory(playerInventory);
        /*
        newPlayer.addItem2Inventory(0);
        newPlayer.addItem2Inventory(2);
        */
        getPlayerStats();

        /*
        newPlayer.playerEquipItem(0,playerLimb1);
        newPlayer.playerEquipItem(1,playerLimb2);
        getPlayerEquipment();
        */
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
                    //todo:[High] get the next room's event* can be good or bad event
                    //good event = free item/gold; bad event = new monster to fight;
                    saveData();
                    opponent = createMonster(getRandomNumberInRange(0,monsterDictionary.getSize()-1));
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

        //[todo]: [Bug]player stats being stored are including weapon buffs
        SharedPreferences sharedPreferences = getSharedPreferences(playerSharedPreferences, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(playerInventory);
        editor.putString(PLAYERINVENTORY,json);
        editor.putInt(PLAYEREXPERIENCE,playerExperience);
        editor.putInt(PLAYERLEVEL,playerLevel);
        editor.putInt(PLAYERHP,playerHP);
        editor.putInt(PLAYERMAXHP,playerMaxHP);
        editor.putInt(PLAYERENERGY,playerEnergy);
        editor.putInt(PLAYERMAXENERGY,playerMaxEnergy);
        editor.putInt(PLAYERATTACK,playerAttack);
        editor.putInt(PLAYERDEFENSE,playerDefense);
        editor.putInt(PLAYERCOINPURSE,playerCoinPurse);
        editor.putInt(PLAYERSKILLPOWER,playerSkillPower);
        editor.putInt(KILLCOUNT,killCount);
        editor.putInt(PLAYERIMAGEID,newPlayer.getPlayerLimb1ImageId());
        editor.apply();
        Toast.makeText(this,"Data saved",Toast.LENGTH_SHORT).show();

    }

    public void startSelectPlayerActivity(){
        //todo:[Medium] should display the hero's latest save data upon start of this activity
        Intent intent = new Intent(this,SelectPlayer.class);
        startActivity(intent);
    }
    private void loadSounds() {
        //todo:[BUG] the sound is adjusted using the System sounds bar and not the Media volumes
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
        leftArmDisplay = findViewById(R.id.leftArmImageView);
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
        playerLimb1EquippmentDisplay = findViewById(R.id.limb1EquippedItem);
        playerLimb2EquippmentDisplay = findViewById(R.id.limb2EquippedItem);
        playerLimb3EquippmentDisplay = findViewById(R.id.limb3EquippedItem);
        playerLimb4EquippmentDisplay = findViewById(R.id.limb4EquippedItem);
        playerLimb5EquippmentDisplay = findViewById(R.id.limb5EquippedItem);
        playerLimb6EquippmentDisplay = findViewById(R.id.limb6EquippedItem);
        playerLimb7EquippmentDisplay = findViewById(R.id.limb7EquippedItem);
        playerLimb8EquippmentDisplay = findViewById(R.id.limb8EquippedItem);
        playerLimb9EquippmentDisplay = findViewById(R.id.limb9EquippedItem);
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
    }

    //BATTLE METHODS

    //todo:[High] refactor so that the get data is separate from the update of the displays like the Select Player activity
    private void getPlayerStats() {
        playerEnergy = newPlayer.getPlayerEnergy();
        playerMaxEnergy = newPlayer.getPlayerMaxEnergy();
        playerEnergyDisplay.setText(res.getString(R.string.playerEnergy_StringValue,playerEnergy,playerMaxEnergy));

        Drawable image = ContextCompat.getDrawable(this, newPlayer.getPlayerLimb1ImageId());
        leftArmDisplay.setImageDrawable(image);
        leftArmDisplay.setVisibility(View.VISIBLE);

        playerExperience = newPlayer.getPlayerExperience();
        playerExperienceDisplay.setText(res.getString(R.string.playerExperience_StringValue,playerExperience));

        playerAttack = newPlayer.getPlayerAttack();
        playerAttackDisplay.setText(res.getString(R.string.playerAttack_StringValue,playerAttack));

        playerDefense = newPlayer.getPlayerDefense();
        playerDefenseDisplay.setText(res.getString(R.string.playerDefense_StringValue,playerDefense));

        playerHP = newPlayer.getPlayerHealth();
        playerMaxHP = newPlayer.getPlayerMaxHealth();
        playerHPDisplay.setText(res.getString(R.string.playerHP_StringValue,playerHP,playerMaxHP));
        if(Math.round((playerMaxHP/2))>=playerHP){
            playerHPDisplay.setTextColor(Color.rgb(255,0,0));
        }else{
            playerHPDisplay.setTextColor(Color.rgb(0,0,0));
        }

        playerLevel = newPlayer.getPlayerLevel();
        playerLevelDisplay.setText(res.getString(R.string.playerLevel_StringValue,playerLevel));

        killCount = newPlayer.getPlayerKillCount();
        killCountDisplay.setText(res.getString(R.string.killCount_StringValue,killCount));

        playerDescription = newPlayer.getPlayerDescription();

        playerSkillPower = newPlayer.getPlayerSkillPower();
        playerSkillPowerDisplay.setText(res.getString(R.string.playerSP_StringValue,playerSkillPower));


        playerLimb1 = newPlayer.getPlayerLimb1();
        playerLimb2 = newPlayer.getPlayerLimb2();

        playerInventory = newPlayer.getPlayerInventory();
        currentInventoryCountDisplay.setText(res.getString(R.string.inventoryCount_StringValue,playerInventory.size()));

        playerCoinPurse = newPlayer.getPlayerCoinPurse();
        playerCoinPurseDisplay.setText(res.getString(R.string.playerCoinPurse_StringValue,playerCoinPurse));

        playerSharedPreferences = newPlayer.getPlayerSharedPreferences();

        //BATTLE CALCULATIONS RELATIVE TO PLAYER
        //todo: [Medium] this cannot be a SOLID coding practice, extract this into its own method
        if(playerAttack - monsterDefense <=0){
            damage = 1;
        }else{
            damage = playerAttack - monsterDefense;
        }
        attackButton.setText(res.getString(R.string.attackButton_TextValue,damage));
        healAmount = playerSkillPower+1;
        healButton.setText(res.getString(R.string.healButton_StringValue,healAmount));
        if(playerDefense >= monsterAttack){
            playerDamage = 1;
        }else{
            playerDamage = monsterAttack - playerDefense;
        }
    }

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
    }


    //todo:[Medium] refactor to focus on targeting of a specific monster
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
            soundPool.play(sound1, 1, 1, 0, 0, 1);
            combatLogDisplay.append(res.getString(R.string.playerAttackMonster,monsterName,damage));
            monsterHpDisplay.setText(res.getString(R.string.monsterHP_StringValue,monsterHp));
            if (monsterHp<=0){
                killMonster();
                setPlayerStats();
                getPlayerStats();
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
            }else{
                playerHP = playerHP + healAmount;
                playerEnergy = playerEnergy - 1;
                setPlayerStats();
                getPlayerStats();
            }
            soundPool.play(sound2, 1, 1, 0, 0, 1);
            combatLogDisplay.append(res.getString(R.string.playerHealSelf,healAmount));
            YoYo.with(Techniques.Tada)
                    .duration(300)
                    .repeat(0)
                    .playOn(leftArmDisplay);
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

    private Monster createMonster(int monsterNumber) {
        res = getResources();
        monsterDictionary = new MonsterDictionary();
        Monster freshMonster;
        freshMonster = monsterDictionary.getMonster(monsterNumber);

        Drawable image = ContextCompat.getDrawable(this,freshMonster.getMonsterImageId());

        //todo: [Low] create an updateMonsterStats method same as updatePlayerStats
        monsterImageDisplay.setImageDrawable(image);

        monsterName = res.getString(freshMonster.getName());
        monsterDefense=freshMonster.getDefensePower();
        monsterDefenseDisplay.setText(res.getString(R.string.monsterDefense_StringValue,monsterDefense));
        monsterHp = freshMonster.getHealthPoints();
        monsterHpDisplay.setText(res.getString(R.string.monsterHP_StringValue,monsterHp));
        monsterAttack = freshMonster.getAttackPower();
        monsterAttackDisplay.setText(res.getString(R.string.monsterAttack_StringValue,monsterAttack));
        monsterExp = freshMonster.getExperience();
        combatLogDisplay.setText(res.getString(R.string.wildMonsterAppears,monsterName));
        monsterDropItemIndex = freshMonster.getItemIndex();
        monsterDropChance = freshMonster.getItemDropChance();
        monsterActualGoldDrop = freshMonster.dropGold();

        monsterImageDisplay.animate().alpha(1.0f).setDuration(100);
        monsterHpDisplay.setVisibility(View.VISIBLE);
        monsterAttackDisplay.setVisibility(View.VISIBLE);
        monsterDefenseDisplay.setVisibility(View.VISIBLE);

        return freshMonster;
    }

    //todo: [High] return the player back to 1-1 equivalent... need to return them back to the main menu
    //todo: [Bug] two YoYo's can't seem to play at once,
    private void attackPlayer(Player newPlayer){
        YoYo.with(Techniques.Tada)
                .duration(100)
                .repeat(0)
                .playOn(monsterImageDisplay);
        res = getResources();
        playerHP = playerHP - playerDamage;
        setPlayerStats();
        getPlayerStats();
        turnCounter++;
        turnCounterDisplay.setText(res.getString(R.string.turnCounter_StringValue,turnCounter));
        combatLogDisplay.setText(res.getString(R.string.monsterAttackPlayer,monsterName,playerDamage));

        YoYo.with(Techniques.Flash)
                .duration(300)
                .repeat(0)
                .playOn(leftArmDisplay);
        if (playerHP<=0){
            playerDeath();

        }
    }

    private void playerDeath() {
        resetTurnCounter();
        getPlayerStats();
        leftArmDisplay.setVisibility(View.INVISIBLE);
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
        combatLogDisplay.setText(res.getString(R.string.monsterDeath,monsterName,monsterExp,monsterActualGoldDrop));
        monsterImageDisplay.animate().alpha(0.0f).setDuration(600);
        monsterHpDisplay.setVisibility(View.INVISIBLE);
        monsterAttackDisplay.setVisibility(View.INVISIBLE);
        monsterDefenseDisplay.setVisibility(View.INVISIBLE);
        if(newPlayer.checkLevel(playerExperience) != -1) {
            if(newPlayer.checkLevel(playerExperience) > newPlayer.getPlayerLevel()){
                newPlayer.levelUp(newPlayer.checkLevel(playerExperience));
                getPlayerStats();
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
        if (chance<=monsterDropChance){
            dropSuccess = true;
        }else{
            dropSuccess = false;
        }
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