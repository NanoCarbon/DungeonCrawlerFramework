package com.example.dungeoncrawlerframework.Items;

import com.example.dungeoncrawlerframework.Limbs.Limb;

public class Item {


    private int itemName;
    private int itemImageId;
    private int effectValue;
    private int itemEffectType;
    private boolean equippable;
    private String itemDescription;
    private int limbRestriction;

    public Item(int itemName, int itemImageId, int effectValue, int itemEffectType,boolean equippable, String itemDescription, int limbRestriction){
        this.itemName = itemName;
        this.itemImageId = itemImageId;
        this.effectValue = effectValue;
        this.itemEffectType = itemEffectType;
        this.equippable = equippable;
        this.itemDescription = itemDescription;
        this.limbRestriction = limbRestriction;
    }

    public int getItemName() {
        return itemName;
    }

    public void setItemName(int itemName) {
        this.itemName = itemName;
    }

    public int getItemImageId() {
        return itemImageId;
    }

    public void setItemImageId(int itemImageId) {
        this.itemImageId = itemImageId;
    }

    public int getEffectValue() {
        return effectValue;
    }

    public void setEffectValue(int effectValue) {
        this.effectValue = effectValue;
    }

    public int getItemEffectType() {
            return itemEffectType;
    }

    public void setItemEffectType(int itemEffectType) {
        this.itemEffectType = itemEffectType;
    }

    public boolean isEquippable() {
        return equippable;
    }

    public void setEquippable(boolean equippable) {
        this.equippable = equippable;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getLimbRestriction() {
        return limbRestriction;
    }

    public void setLimbRestriction(int limbRestriction) {
        this.limbRestriction = limbRestriction;
    }

    //todo: [High] create a method that unequips item as well

    public void equipItem(Limb playerLimb){
        if (this.equippable = true && limbRestriction == playerLimb.getLimbType()){
            playerLimb.setEquippedItem(this);
        }
    }
}
