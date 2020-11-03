package com.example.dungeoncrawlerframework.Monsters;

import com.example.dungeoncrawlerframework.Items.Item;
import com.example.dungeoncrawlerframework.Items.ItemDictionary;

import java.util.Random;

public class Monster {

    //===============================MONSTER VARS========================================//
    private int name;
    private int healthPoints;
    private int attackPower;
    private int defensePower;
    private int monsterImageId;
    private int experience;
    private int itemIndex;
    private double itemDropChance;
    private int baseGoldDrop;
    //===============================MONSTER VARS========================================//

    //===============================CONSTRUCTOR=========================================//
    public Monster(int monsterName, int healthPoints, int attackPower, int defensePower, int monsterSprite, int experience, int itemIndex, double itemDropChance, int baseGoldDrop) {
        this.name = monsterName;
        this.healthPoints = healthPoints;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.monsterImageId = monsterSprite;
        this.experience = experience;
        this.itemIndex = itemIndex;
        this.itemDropChance = itemDropChance;
        this.baseGoldDrop = baseGoldDrop;
    }
    //===============================CONSTRUCTOR=========================================//

    //===============================GETTERS AND SETTERS=========================================//
    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getDefensePower() {
        return defensePower;
    }

    public void setDefensePower(int defensePower) {
        this.defensePower = defensePower;
    }

    public int getMonsterImageId() {
        return monsterImageId;
    }

    public void setMonsterImageId(int monsterImageId) {
        this.monsterImageId = monsterImageId;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public void setItemIndex(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    public double getItemDropChance() {
        return itemDropChance;
    }

    public void setItemDropChance(double itemDropChance) {
        this.itemDropChance = itemDropChance;
    }

    public int getBaseGoldDrop() {
        return baseGoldDrop;
    }

    public void setBaseGoldDrop(int baseGoldDrop) {
        this.baseGoldDrop = baseGoldDrop;
    }
    //===============================GETTERS AND SETTERS=========================================//


    //===============================MAIN MONSTER METHODS=========================================//
    public Item dropItem(int itemIndex){
            ItemDictionary itemDictionary = new ItemDictionary();
            Item item2Return = itemDictionary.getItem(itemIndex);
            return item2Return;
    }

    public int dropGold(){
        double minMultiplier = 0.7;
        double maxMultiplier = 1.3;
        int actualGoldDrop;
        Random random = new Random();
        double num =(random.nextDouble()*(maxMultiplier-minMultiplier)+minMultiplier)*baseGoldDrop;
        actualGoldDrop = (int) Math.round(num);
        return actualGoldDrop;
    }
    //===============================MAIN MONSTER METHODS=========================================//
}
