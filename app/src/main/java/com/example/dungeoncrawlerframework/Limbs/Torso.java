package com.example.dungeoncrawlerframework.Limbs;

import com.example.dungeoncrawlerframework.Items.Item;

public class Torso extends Limb {
    private Item equippedItem;
    private int limbType = 2;

    public Torso(){
    }

    public Item getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(Item equippedItem) {
        this.equippedItem = equippedItem;
    }

    public int getLimbType() {
        return limbType;
    }

    public void setLimbType(int limbType) {
        this.limbType = limbType;
    }

}
