package com.example.dungeoncrawlerframework.Players;

import com.example.dungeoncrawlerframework.Items.Item;
import com.example.dungeoncrawlerframework.Items.ItemDictionary;
import com.example.dungeoncrawlerframework.Limbs.Feet;
import com.example.dungeoncrawlerframework.Limbs.Hand;
import com.example.dungeoncrawlerframework.Limbs.Head;
import com.example.dungeoncrawlerframework.Limbs.Legs;
import com.example.dungeoncrawlerframework.Limbs.Limb;
import com.example.dungeoncrawlerframework.Limbs.Torso;

import java.util.ArrayList;

public class Player{

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
    //===========================================================//

    private int playerLimb1ImageId;
    private ArrayList<Integer> playerInventory;
    private int playerExperience=0;
    private int playerLevel=0;
    private int playerKillCount=0;

    private String playerDescription;
    private Hand playerHand1 = new Hand();
    private Hand playerHand2 = new Hand();
    private Feet playerFeet = new Feet();
    private Head playerHead = new Head();
    private Torso playerTorso = new Torso();
    private Legs playerLegs = new Legs();

    private int playerCoinPurse = 0;
    private ArrayList<Limb> playerBodyParts = new ArrayList<Limb>();


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
        this.playerBodyParts.add(playerHand1);
        this.playerBodyParts.add(playerHand2);
        this.playerBodyParts.add(playerHead);
        this.playerBodyParts.add(playerTorso);
        this.playerBodyParts.add(playerLegs);
        this.playerBodyParts.add(playerFeet);





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





//MAJOR PLAYER METHODS


    PlayerLevelTree playerLevelTree = new PlayerLevelTree();

    int[] lowLevelBound = playerLevelTree.getLowLevelBound();
    int[] hiLevelBound = playerLevelTree.getHiLevelBound();
    int[] statsTreeAttack = playerLevelTree.getAttackTree();
    int[] statsTreeDefense = playerLevelTree.getDefenseTree();
    int[] statsTreeHP = playerLevelTree.getHpTree();
    int[] statsTreeEnergy = playerLevelTree.getEnergyTree();
    int[] statsTreeSkillPower = playerLevelTree.getSkillPowerTree();

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


}
