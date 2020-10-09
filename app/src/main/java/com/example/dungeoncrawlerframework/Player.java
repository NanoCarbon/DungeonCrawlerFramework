package com.example.dungeoncrawlerframework;

import java.util.ArrayList;

public class Player{

    //todo: [High] create a skill class and skill dictionary


    private int playerHealth;
    private int playerAttack;
    private int playerDefense;
    private int playerMaxHealth;
    private int playerEnergy;
    private int playerMaxEnergy;
    private int playerLimb1ImageId;
    private ArrayList<Integer> playerInventory;
    private int playerExperience=0;
    private int playerLevel=0;
    private int playerKillCount=0;
    private int playerSkillPower;
    private String playerDescription;
    private Limb playerLimb1 = new Limb();
    private Limb playerLimb2 = new Limb();
    private int playerCoinPurse = 0;


    //starting values
    final private int playerStartingHealth;
    final private int playerStartingAttack;
    final private int playerStartingDefense;
    final private int playerStartingExperience=0;
    final private int playerStartingLevel=0;
    final private int playerStartingKillCount=0;
    final private int playerStartingMaxHealth;
    final private int playerStartingEnergy;
    final private int playerStartingMaxEnergy;
    final private int playerStartingSkillPower;
    final private String playerSharedPreferences;


    public Player(int playerHealth,
                  int playerAttack,
                  int playerDefense,
                  int playerEnergy,
                  int playerLimb1ImageId,
                  String playerDescription,
                  int playerSkillPower,
                  String playerSharedPreferences) {


        this.playerHealth = playerHealth;
        this.playerAttack = playerAttack;
        this.playerDefense = playerDefense;
        this.playerMaxHealth = playerHealth;
        this.playerEnergy = playerEnergy;
        this.playerMaxEnergy = playerEnergy;
        this.playerLimb1ImageId = playerLimb1ImageId;
        this.playerDescription = playerDescription;
        this.playerSkillPower = playerSkillPower;
        this.playerSharedPreferences = playerSharedPreferences;

        //starting values
        this.playerStartingHealth = playerHealth;
        this.playerStartingAttack = playerAttack;
        this.playerStartingDefense = playerDefense;
        this.playerStartingMaxHealth = playerMaxHealth;
        this.playerStartingEnergy = playerEnergy;
        this.playerStartingMaxEnergy = playerEnergy;
        this.playerStartingSkillPower = playerSkillPower;
    }

    // PARCELABLE IMPLEMENTATION METHODS
    /*
    protected Player(Parcel in) {
        playerHealth = in.readInt();
        playerAttack = in.readInt();
        playerDefense = in.readInt();
        playerMaxHealth = in.readInt();
        playerEnergy = in.readInt();
        playerMaxEnergy = in.readInt();
        playerLimb1ImageId = in.readInt();
        playerExperience = in.readInt();
        playerLevel = in.readInt();
        playerKillCount = in.readInt();
        playerSkillPower = in.readInt();
        playerDescription = in.readString();
        playerStartingHealth = in.readInt();
        playerStartingAttack = in.readInt();
        playerStartingDefense = in.readInt();
        playerStartingExperience = in.readInt();
        playerStartingLevel = in.readInt();
        playerStartingKillCount = in.readInt();
        playerStartingMaxHealth = in.readInt();
        playerStartingEnergy = in.readInt();
        playerStartingMaxEnergy = in.readInt();
        playerStartingSkillPower = in.readInt();
        lowLevelBound = in.createIntArray();
        hiLevelBound = in.createIntArray();
        statsTreeAttack = in.createIntArray();
        statsTreeDefense = in.createIntArray();
        statsTreeHP = in.createIntArray();
        statsTreeEnergy = in.createIntArray();
        statsTreeSkillPower = in.createIntArray();
    }


    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(playerHealth);
        dest.writeInt(playerAttack);
        dest.writeInt(playerDefense);
        dest.writeInt(playerMaxHealth);
        dest.writeInt(playerEnergy);
        dest.writeInt(playerMaxEnergy);
        dest.writeInt(playerLimb1ImageId);
        dest.writeInt(playerExperience);
        dest.writeInt(playerLevel);
        dest.writeInt(playerKillCount);
        dest.writeInt(playerSkillPower);
        dest.writeString(playerDescription);
        dest.writeInt(playerStartingHealth);
        dest.writeInt(playerStartingAttack);
        dest.writeInt(playerStartingDefense);
        dest.writeInt(playerStartingExperience);
        dest.writeInt(playerStartingLevel);
        dest.writeInt(playerStartingKillCount);
        dest.writeInt(playerStartingMaxHealth);
        dest.writeInt(playerStartingEnergy);
        dest.writeInt(playerStartingMaxEnergy);
        dest.writeInt(playerStartingSkillPower);
        dest.writeIntArray(lowLevelBound);
        dest.writeIntArray(hiLevelBound);
        dest.writeIntArray(statsTreeAttack);
        dest.writeIntArray(statsTreeDefense);
        dest.writeIntArray(statsTreeHP);
        dest.writeIntArray(statsTreeEnergy);
        dest.writeIntArray(statsTreeSkillPower);
    }
*/

    // GETTERS AND SETTERS
    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }

    public int getPlayerAttack() {
        return playerAttack;
    }

    public void setPlayerAttack(int playerAttack) {
        this.playerAttack = playerAttack;
    }

    public int getPlayerDefense() {
        return playerDefense;
    }

    public void setPlayerDefense(int playerDefense) {
        this.playerDefense = playerDefense;
    }

    public int getPlayerExperience() {
        return playerExperience;
    }

    public void setPlayerExperience(int playerExperience) {
        this.playerExperience = playerExperience;
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }

    public int getPlayerMaxHealth() {
        return playerMaxHealth;
    }

    public void setPlayerMaxHealth(int playerMaxHealth) {
        this.playerMaxHealth = playerMaxHealth;
    }

    public int getPlayerKillCount() {
        return playerKillCount;
    }

    public void setPlayerKillCount(int playerKillCount) {
        this.playerKillCount = playerKillCount;
    }

    public int getPlayerEnergy() {
        return playerEnergy;
    }

    public void setPlayerEnergy(int playerEnergy) {
        this.playerEnergy = playerEnergy;
    }

    public int getPlayerMaxEnergy() {
        return playerMaxEnergy;
    }

    public void setPlayerMaxEnergy(int playerMaxEnergy) {
        this.playerMaxEnergy = playerMaxEnergy;
    }

    public ArrayList<Integer> getPlayerInventory() {
        return playerInventory;
    }

    public void setPlayerInventory(ArrayList<Integer> playerInventory) {
        this.playerInventory = playerInventory;
    }

    public int getPlayerLimb1ImageId() {
        return playerLimb1ImageId;
    }

    public void setPlayerLimb1ImageId(int playerLimb1ImageId) {
        this.playerLimb1ImageId = playerLimb1ImageId;
    }

    public Limb getPlayerLimb1() {
        return playerLimb1;
    }

    public void setPlayerLimb1(Limb playerLimb1) {
        this.playerLimb1 = playerLimb1;
    }

    public Limb getPlayerLimb2() {
        return playerLimb2;
    }

    public void setPlayerLimb2(Limb playerLimb2) {
        this.playerLimb2 = playerLimb2;
    }

    public String getPlayerDescription() {
        return playerDescription;
    }

    public void setPlayerDescription(String playerDescription) {
        this.playerDescription = playerDescription;
    }

    public int getPlayerSkillPower() {
        return playerSkillPower;
    }

    public void setPlayerSkillPower(int playerSkillPower) {
        this.playerSkillPower = playerSkillPower;
    }

    public int getPlayerCoinPurse() {
        return playerCoinPurse;
    }

    public void setPlayerCoinPurse(int playerCoinPurse) {
        this.playerCoinPurse = playerCoinPurse;
    }




    public int getPlayerStartingAttack() {
        return playerStartingAttack;
    }

    public int getPlayerStartingDefense() {
        return playerStartingDefense;
    }

    public int getPlayerStartingExperience() {
        return playerStartingExperience;
    }

    public int getPlayerStartingLevel() {
        return playerStartingLevel;
    }

    public int getPlayerStartingKillCount() {
        return playerStartingKillCount;
    }

    public int getPlayerStartingMaxHealth() {
        return playerStartingMaxHealth;
    }

    public int getPlayerStartingSkillPower() {
        return playerStartingSkillPower;
    }

    public String getPlayerSharedPreferences() {
        return playerSharedPreferences;
    }



//MAJOR PLAYER METHODS


    LevelTree levelTree = new LevelTree();

    int[] lowLevelBound = levelTree.getLowLevelBound();
    int[] hiLevelBound = levelTree.getHiLevelBound();
    int[] statsTreeAttack = levelTree.getAttackTree();
    int[] statsTreeDefense = levelTree.getDefenseTree();
    int[] statsTreeHP = levelTree.getHpTree();
    int[] statsTreeEnergy = levelTree.getEnergyTree();
    int[] statsTreeSkillPower = levelTree.getSkillPowerTree();

    public int checkLevel(int playerExperience){
        int level = -1;
        for (int i = 0; i<=lowLevelBound.length; i++){
            if(playerExperience >= lowLevelBound[i] && playerExperience < hiLevelBound[i]){
                level = i;
                break;
            }
        }
        return level;
    }


    public void levelUp(int playerLevel){
        if (playerLevel - 1 == this.playerLevel){
            this.playerAttack = this.playerAttack + statsTreeAttack[playerLevel];
            this.playerDefense = this.playerDefense + statsTreeDefense[playerLevel];
            this.playerMaxHealth = this.playerMaxHealth + statsTreeHP[playerLevel];
            this.playerHealth = this.playerMaxHealth;
            this.playerLevel = playerLevel;
            this.playerMaxEnergy = this.playerMaxEnergy + statsTreeEnergy[playerLevel];
            this.playerEnergy = this.playerMaxEnergy;
            this.playerSkillPower = this.playerSkillPower + statsTreeSkillPower[playerLevel];
        }else{
            for(int i = this.playerLevel; i<=playerLevel; i++){
                this.playerAttack = this.playerAttack + statsTreeAttack[i];
                this.playerDefense = this.playerDefense + statsTreeDefense[i];
                this.playerMaxHealth = this.playerMaxHealth + statsTreeHP[i];
                this.playerHealth = this.playerMaxHealth;
                this.playerLevel = i;
                this.playerMaxEnergy = this.playerMaxEnergy + statsTreeEnergy[i];
                this.playerEnergy = this.playerMaxEnergy;
                this.playerSkillPower = this.playerSkillPower + statsTreeSkillPower[i];
            }
        }

    }

    public void resetPlayer(){
        this.playerHealth = this.playerStartingHealth;
        this.playerAttack = this.playerStartingAttack;
        this.playerDefense = this.playerStartingDefense;
        this.playerExperience = this.playerStartingExperience;
        this.playerLevel = this.playerStartingLevel;
        this.playerKillCount = this.playerStartingKillCount;
        this.playerMaxHealth = this.playerStartingMaxHealth;
        this.playerEnergy = this.playerStartingEnergy;
        this.playerMaxEnergy = this.playerStartingMaxEnergy;
        this.playerSkillPower = this.playerStartingSkillPower;
    }


    public void addItem2Inventory(int itemIndex){
        playerInventory.add(itemIndex);
    }

    public void getItemFromInventory(int inventoryIndex){ playerInventory.get(inventoryIndex);}

    public void removeItemFromInventory(int inventoryIndex){
        playerInventory.remove(inventoryIndex);
    }

    public void playerUseItem(int inventoryIndex){
        //method for single use items
        ItemDictionary itemDictionary = new ItemDictionary();

        int itemIndex;
        Item item2Use;
        int itemType;

        itemIndex = playerInventory.get(inventoryIndex);
        item2Use = itemDictionary.getItem(itemIndex);
        itemType = item2Use.getItemEffectType();

        //todo:[High] incorporate floor logic in case the effectValue goes higher or below logical floors
        //todo:[High] incorporate the logic for the other item types
        //(i.e. death or potions healing above max health)

        switch(itemType){
            case 1:
                //affects playerMaxHealth
                playerMaxHealth = playerMaxHealth + item2Use.getEffectValue();
                playerHealth = playerMaxHealth;
                break;
            case 2:
                //affects playerHealth
                if(playerHealth + item2Use.getEffectValue()>=playerMaxHealth){
                    playerHealth = playerMaxHealth;
                }else{
                    playerHealth = playerHealth + item2Use.getEffectValue();
                }
                break;
            case 3:
                //affects playerAttack
                playerAttack = playerAttack + item2Use.getEffectValue();
                break;
            case 4:
                //affects playerDefense
                playerDefense = playerDefense + item2Use.getEffectValue();
                break;
            case 5:
                //affects playerMaxEnergy;
                playerMaxEnergy = playerMaxEnergy + item2Use.getEffectValue();
                playerEnergy = playerMaxEnergy;
                break;
            case 6:
                //affects playerEnergy;
                if(playerEnergy + item2Use.getEffectValue()>=playerMaxEnergy){
                    playerEnergy = playerMaxEnergy;
                }else{
                    playerEnergy = playerEnergy + item2Use.getEffectValue();
                }
                break;
            case 7:
                //affects playerSkillPower;
                playerSkillPower = playerSkillPower + item2Use.getEffectValue();
                break;

        }

        removeItemFromInventory(inventoryIndex);
    }
    public void playerEquipItem(int inventoryIndex, Limb limb2Equip){
        //method for equipment and permanent items

        ItemDictionary itemDictionary = new ItemDictionary();

        int itemIndex;
        Item item2Equip;
        int itemType;

        itemIndex = playerInventory.get(inventoryIndex);
        item2Equip = itemDictionary.getItem(itemIndex);
        itemType = item2Equip.getItemEffectType();

        if(item2Equip.isEquippable()) {
            switch (itemType) {
                case 1:
                    //affects playerMaxHealth
                    playerMaxHealth = playerMaxHealth + item2Equip.getEffectValue();
                    playerHealth = playerMaxHealth;
                    break;
                case 2:
                    //affects playerHealth
                    if (playerHealth + item2Equip.getEffectValue() >= playerMaxHealth) {
                        playerHealth = playerMaxHealth;
                    } else {
                        playerHealth = playerHealth + item2Equip.getEffectValue();
                    }
                    break;
                case 3:
                    //affects playerAttack
                    playerAttack = playerAttack + item2Equip.getEffectValue();
                    break;
                case 4:
                    //affects playerDefense
                    playerDefense = playerDefense + item2Equip.getEffectValue();
                    break;
                case 5:
                    //affects playerMaxEnergy;
                    playerMaxEnergy = playerMaxEnergy + item2Equip.getEffectValue();
                    playerEnergy = playerMaxEnergy;
                    break;
                case 6:
                    //affects playerEnergy;
                    if (playerEnergy + item2Equip.getEffectValue() >= playerMaxEnergy) {
                        playerEnergy = playerMaxEnergy;
                    } else {
                        playerEnergy = playerEnergy + item2Equip.getEffectValue();
                    }
                    break;
                case 7:
                    //affects playerSkillPower;
                    playerSkillPower = playerSkillPower + item2Equip.getEffectValue();
                    break;

            }
            limb2Equip.setEquippedItem(item2Equip);
        }
    }


}
