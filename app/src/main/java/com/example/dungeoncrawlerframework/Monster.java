package com.example.dungeoncrawlerframework;

import java.util.Random;

public class Monster {
    //todo: [Medium] generate an attack animation for the monster (shift left to right for simple animation and move arm up and down for more complex animations)


    private int name;
    private int healthPoints;
    private int attackPower;
    private int defensePower;
    private int monsterImageId;
    private int experience;
    private int itemIndex;
    private double itemDropChance;
    private int baseGoldDrop;

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

}
