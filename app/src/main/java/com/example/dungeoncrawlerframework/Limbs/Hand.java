package com.example.dungeoncrawlerframework.Limbs;

import com.example.dungeoncrawlerframework.Item;

public class Hand extends Limb {

    private Item equippedItem;
    public Hand(){

    }

    public Item getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(Item equippedItem) {
        this.equippedItem = equippedItem;
    }

}
