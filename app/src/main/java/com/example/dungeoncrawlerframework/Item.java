package com.example.dungeoncrawlerframework;

public class Item {


    private int itemName;
    private int itemImageId;
    private int effectValue;
    private int itemEffectType;
    private boolean equippable;


    //todo: [High] add parameter that dictates if the item is to be used on a player or a monster
    public Item(int itemName, int itemImageId, int effectValue, int itemEffectType,boolean equippable){
        this.itemName = itemName;
        this.itemImageId = itemImageId;
        this.effectValue = effectValue;
        this.itemEffectType = itemEffectType;
        this.equippable = equippable;
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


    //todo: [High] create a method tha

    public void equipItem(Limb playerLimb){
        if (this.equippable = true){
            playerLimb.setEquippedItem(this);
        }
    }
}
