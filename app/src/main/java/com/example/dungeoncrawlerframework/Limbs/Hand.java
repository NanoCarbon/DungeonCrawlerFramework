package com.example.dungeoncrawlerframework.Limbs;

import com.example.dungeoncrawlerframework.Items.Item;

public class Hand extends Limb {

    private Item equippedItem;
    private int limbType = 3;

    public Hand(){
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
