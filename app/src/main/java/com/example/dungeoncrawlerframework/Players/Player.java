package com.example.dungeoncrawlerframework.Players;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.dungeoncrawlerframework.Items.Item;
import com.example.dungeoncrawlerframework.Items.ItemDictionary;
import com.example.dungeoncrawlerframework.Limbs.Limb;

import java.util.ArrayList;

public class Player implements Parcelable {

    //todo: [High] create a skill class and skill dictionary

    //======THE 7 PLAYER STATS THAT ARE AFFECTED BY ITEMS========//
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
    //======THE 7 PLAYER STATS THAT ARE AFFECTED BY ITEMS========//

    //==============OTHER PARAMETERIZED VARS====================//
    private int playerImageId;
    private ArrayList<Integer> playerInventory;
    private String playerDescription;
    //==============OTHER PARAMETERIZED VARS====================//

    //===================PRE INSTANTIATED VARS==================//
    private int playerExperience=0;
    private int playerLevel=0;
    private int playerKillCount=0;
    private int playerCoinPurse = 0;
    private ArrayList<Limb> playerBodyParts = new ArrayList<Limb>();
    private Limb playerHand1 = new Limb(3);
    private Limb playerHand2 = new Limb(3);
    private Limb playerFeet = new Limb(5);
    private Limb playerHead = new Limb(1);
    private Limb playerTorso = new Limb(2);
    private Limb playerLegs = new Limb(4);
    //===================PRE INSTANTIATED VARS==================//

    //===================STARTING VARS==========================//
    final private int playerStartingHealth;
    final private int playerStartingAttack;
    final private int playerStartingDefense;
    private int playerStartingExperience=0;
    private int playerStartingLevel=0;
    private int playerStartingKillCount=0;
    final private int playerStartingMaxHealth;
    final private int playerStartingEnergy;
    final private int playerStartingMaxEnergy;
    final private int playerStartingSkillPower;
    final private String playerSharedPreferences;
    //===================STARTING VARS==========================//

    //=============================CONSTRUCTOR====================================//
    public Player(int playerHealth,
                  int playerAttack,
                  int playerDefense,
                  int playerEnergy,
                  int playerImageId,
                  String playerDescription,
                  int playerSkillPower,
                  String playerSharedPreferences) {


        this.playerHealth = playerHealth;
        this.playerMaxHealth = playerHealth;
        this.playerEnergy = playerEnergy;
        this.playerMaxEnergy = playerEnergy;
        this.playerAttack = playerAttack;
        this.playerDefense = playerDefense;
        this.playerImageId = playerImageId;
        this.playerDescription = playerDescription;
        this.playerSkillPower = playerSkillPower;
        this.playerSharedPreferences = playerSharedPreferences;
        this.playerBodyParts.add(playerHead);
        this.playerBodyParts.add(playerHand1);
        this.playerBodyParts.add(playerTorso);
        this.playerBodyParts.add(playerHand2);
        this.playerBodyParts.add(playerLegs);
        this.playerBodyParts.add(playerFeet);
        this.playerInventory = new ArrayList<Integer>(){};
        //starting values
        this.playerStartingHealth = playerHealth;
        this.playerStartingAttack = playerAttack;
        this.playerStartingDefense = playerDefense;
        this.playerStartingMaxHealth = playerMaxHealth;
        this.playerStartingEnergy = playerEnergy;
        this.playerStartingMaxEnergy = playerEnergy;
        this.playerStartingSkillPower = playerSkillPower;

    }
//=============================CONSTRUCTOR====================================//
//===================PARCELABLE CREATOR==============================================//
    protected Player(Parcel in) {
        playerHealth = in.readInt();
        itemHealthEffect = in.readInt();
        playerAttack = in.readInt();
        itemAttackEffect = in.readInt();
        playerDefense = in.readInt();
        itemDefenseEffect = in.readInt();
        playerMaxHealth = in.readInt();
        itemMaxHealthEffect = in.readInt();
        playerEnergy = in.readInt();
        itemEnergyEffect = in.readInt();
        playerMaxEnergy = in.readInt();
        itemMaxEnergyEffect = in.readInt();
        playerSkillPower = in.readInt();
        itemSkillPowerEffect = in.readInt();
        playerImageId = in.readInt();
        playerDescription = in.readString();
        playerExperience = in.readInt();
        playerLevel = in.readInt();
        playerKillCount = in.readInt();
        playerCoinPurse = in.readInt();
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
        playerSharedPreferences = in.readString();
        playerInventory = in.readArrayList(null);
        playerHead = in.readParcelable(Limb.class.getClassLoader());
        playerHand1 = in.readParcelable(Limb.class.getClassLoader());
        playerTorso = in.readParcelable(Limb.class.getClassLoader());
        playerHand2 = in.readParcelable(Limb.class.getClassLoader());
        playerLegs = in.readParcelable(Limb.class.getClassLoader());
        playerFeet = in.readParcelable(Limb.class.getClassLoader());
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
    //===================PARCELABLE CREATOR==============================================//

    //====================GETTERS AND SETTERS============================================//
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

    public int getPlayerImageId() {
        return playerImageId;
    }

    public void setPlayerImageId(int playerImageId) {
        this.playerImageId = playerImageId;
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

    public ArrayList<Limb> getPlayerBodyParts() {
        return playerBodyParts;
    }

    public void setPlayerBodyParts(ArrayList<Limb> playerBodyParts) {
        this.playerBodyParts = playerBodyParts;
    }

    public String getPlayerSharedPreferences() {
        return playerSharedPreferences;
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

    public int getItemHealthEffect() {
        return itemHealthEffect;
    }

    public void setItemHealthEffect(int itemHealthEffect) {
        this.itemHealthEffect = itemHealthEffect;
    }

    public int getItemAttackEffect() {
        return itemAttackEffect;
    }

    public void setItemAttackEffect(int itemAttackEffect) {
        this.itemAttackEffect = itemAttackEffect;
    }

    public int getItemDefenseEffect() {
        return itemDefenseEffect;
    }

    public void setItemDefenseEffect(int itemDefenseEffect) {
        this.itemDefenseEffect = itemDefenseEffect;
    }

    public int getItemMaxHealthEffect() {
        return itemMaxHealthEffect;
    }

    public void setItemMaxHealthEffect(int itemMaxHealthEffect) {
        this.itemMaxHealthEffect = itemMaxHealthEffect;
    }

    public int getItemEnergyEffect() {
        return itemEnergyEffect;
    }

    public void setItemEnergyEffect(int itemEnergyEffect) {
        this.itemEnergyEffect = itemEnergyEffect;
    }

    public int getItemMaxEnergyEffect() {
        return itemMaxEnergyEffect;
    }

    public void setItemMaxEnergyEffect(int itemMaxEnergyEffect) {
        this.itemMaxEnergyEffect = itemMaxEnergyEffect;
    }

    public int getItemSkillPowerEffect() {
        return itemSkillPowerEffect;
    }

    public void setItemSkillPowerEffect(int itemSkillPowerEffect) {
        this.itemSkillPowerEffect = itemSkillPowerEffect;
    }

    public Limb getPlayerHand1(){
        return playerHand1;
    }

    public Limb getPlayerHand2() {
        return playerHand2;
    }

    public Limb getPlayerFeet() {
        return playerFeet;
    }

    public Limb getPlayerHead() {
        return playerHead;
    }

    public Limb getPlayerTorso() {
        return playerTorso;
    }

    public Limb getPlayerLegs() {
        return playerLegs;
    }
//====================GETTERS AND SETTERS============================================//

//====================LEVELING METHODS===============================================//

    public int checkLevel(int playerExperience){

        PlayerLevelTree playerLevelTree = new PlayerLevelTree();

        int[] lowLevelBound = playerLevelTree.getLowLevelBound();
        int[] hiLevelBound = playerLevelTree.getHiLevelBound();
        int[] statsTreeAttack = playerLevelTree.getAttackTree();
        int[] statsTreeDefense = playerLevelTree.getDefenseTree();
        int[] statsTreeHP = playerLevelTree.getHpTree();
        int[] statsTreeEnergy = playerLevelTree.getEnergyTree();
        int[] statsTreeSkillPower = playerLevelTree.getSkillPowerTree();

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

        PlayerLevelTree playerLevelTree = new PlayerLevelTree();

        int[] lowLevelBound = playerLevelTree.getLowLevelBound();
        int[] hiLevelBound = playerLevelTree.getHiLevelBound();
        int[] statsTreeAttack = playerLevelTree.getAttackTree();
        int[] statsTreeDefense = playerLevelTree.getDefenseTree();
        int[] statsTreeHP = playerLevelTree.getHpTree();
        int[] statsTreeEnergy = playerLevelTree.getEnergyTree();
        int[] statsTreeSkillPower = playerLevelTree.getSkillPowerTree();

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


//====================LEVELING METHODS===============================================//


//============================INVENTORY AND ITEM RELATED METHODS==============================//

    public void addItem2Inventory(int itemIndex){
        playerInventory.add(itemIndex);
    }

    public void removeItemFromInventory(int inventoryIndex){
        playerInventory.remove(inventoryIndex);
    }

    public void getItemFromInventory(int inventoryIndex){ playerInventory.get(inventoryIndex);}


    public void playerActivateEquipment(Limb limb) {

        ItemDictionary itemDictionary = new ItemDictionary();

            Item item2Equip = limb.getEquippedItem();
            if(item2Equip != null) {
                int itemEffectType = item2Equip.getItemEffectType();

                switch (itemEffectType) {
                    case 1:
                        //affects playerMaxHealth
                        itemMaxHealthEffect = itemMaxHealthEffect + item2Equip.getEffectValue();
                        break;
                    case 2:
                        //affects playerHealth
                        itemHealthEffect = itemHealthEffect + item2Equip.getEffectValue();
                        break;
                    case 3:
                        //affects playerAttack
                        itemAttackEffect = itemAttackEffect + item2Equip.getEffectValue();
                        break;
                    case 4:
                        //affects playerDefense
                        itemDefenseEffect = itemDefenseEffect + item2Equip.getEffectValue();
                        break;
                    case 5:
                        //affects playerMaxEnergy;
                        itemMaxEnergyEffect = itemMaxEnergyEffect + item2Equip.getEffectValue();
                        break;
                    case 6:
                        //affects playerEnergy;
                        itemEnergyEffect = itemEnergyEffect + item2Equip.getEffectValue();
                    case 7:
                        //affects playerSkillPower;
                        itemSkillPowerEffect = itemSkillPowerEffect + item2Equip.getEffectValue();
                        break;
                }
            }
    }

    //todo: [Critical] Refactor this so this is limb independent
    //todo: [Blocker] Create item slots for general use items in Battle Activity
    public void playerUseItem(int inventoryIndex, Limb limb2Equip){
        //method for equipment and permanent items

        ItemDictionary itemDictionary = new ItemDictionary();

        int itemIndex;
        Item item2Equip;
        int itemType;

        itemIndex = playerInventory.get(inventoryIndex);
        item2Equip = itemDictionary.getItem(itemIndex);
        itemType = item2Equip.getItemEffectType();

        if(item2Equip.isEquippable() && limb2Equip.getLimbType() == item2Equip.getLimbRestriction()) {
            switch (itemType) {
                case 1:
                    //affects playerMaxHealth
                    itemMaxHealthEffect = itemMaxHealthEffect + item2Equip.getEffectValue();
                    playerMaxHealth = playerMaxHealth + itemMaxHealthEffect;
                    playerHealth = playerMaxHealth;
                    break;
                case 2:
                    //affects playerHealth
                    itemHealthEffect = itemMaxHealthEffect + item2Equip.getEffectValue();
                    if (playerHealth + itemHealthEffect>= playerMaxHealth) {
                        playerHealth = playerMaxHealth;
                    } else {
                        playerHealth = playerHealth + itemHealthEffect;
                    }
                    break;
                case 3:
                    //affects playerAttack
                    itemAttackEffect = itemAttackEffect + item2Equip.getEffectValue();
                    playerAttack = playerAttack + itemAttackEffect;
                    break;
                case 4:
                    //affects playerDefense
                    itemDefenseEffect = itemDefenseEffect + item2Equip.getEffectValue();
                    playerDefense = playerDefense + itemDefenseEffect;
                    break;
                case 5:
                    //affects playerMaxEnergy;
                    itemMaxEnergyEffect = itemMaxEnergyEffect + item2Equip.getEffectValue();
                    playerMaxEnergy = playerMaxEnergy + itemMaxEnergyEffect;
                    playerEnergy = playerMaxEnergy;
                    break;
                case 6:
                    //affects playerEnergy;
                    itemEnergyEffect = itemEnergyEffect + item2Equip.getEffectValue();
                    if (playerEnergy + itemEnergyEffect >= playerMaxEnergy) {
                        playerEnergy = playerMaxEnergy;
                    } else {
                        playerEnergy = playerEnergy + itemEnergyEffect;
                    }
                    break;
                case 7:
                    //affects playerSkillPower;
                    itemSkillPowerEffect = itemSkillPowerEffect + item2Equip.getEffectValue();
                    playerSkillPower = playerSkillPower + itemSkillPowerEffect;
                    break;

            }
            //Checks to see if the item is single use (i.e. where LimbRestriction = 0)//
            if(item2Equip.getLimbRestriction() ==0){
                removeItemFromInventory(inventoryIndex);
            }else{
                limb2Equip.setEquippedItem(item2Equip);
            }

        }
    }

    //============================INVENTORY AND ITEM RELATED METHODS==============================//

    //=============================PARCELABLE METHODS=======================//
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(playerHealth);
        dest.writeInt(itemHealthEffect);
        dest.writeInt(playerAttack);
        dest.writeInt(itemAttackEffect);
        dest.writeInt(playerDefense);
        dest.writeInt(itemDefenseEffect);
        dest.writeInt(playerMaxHealth);
        dest.writeInt(itemMaxHealthEffect);
        dest.writeInt(playerEnergy);
        dest.writeInt(itemEnergyEffect);
        dest.writeInt(playerMaxEnergy);
        dest.writeInt(itemMaxEnergyEffect);
        dest.writeInt(playerSkillPower);
        dest.writeInt(itemSkillPowerEffect);
        dest.writeInt(playerImageId);
        dest.writeString(playerDescription);
        dest.writeInt(playerExperience);
        dest.writeInt(playerLevel);
        dest.writeInt(playerKillCount);
        dest.writeInt(playerCoinPurse);
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
        dest.writeString(playerSharedPreferences);
        dest.writeList(playerInventory);
        dest.writeParcelable(playerHead, flags);
        dest.writeParcelable(playerHand1, flags);
        dest.writeParcelable(playerTorso, flags);
        dest.writeParcelable(playerHand2, flags);
        dest.writeParcelable(playerLegs, flags);
        dest.writeParcelable(playerFeet, flags);
    }
    //=============================PARCELABLE METHODS=======================//

}
