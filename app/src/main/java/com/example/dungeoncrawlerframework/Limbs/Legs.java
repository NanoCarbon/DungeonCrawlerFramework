package com.example.dungeoncrawlerframework.Limbs;

import com.example.dungeoncrawlerframework.Item;

public class Legs extends Limb {
    private Item equippedItem;

    public Legs(){
    }

    public Item getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(Item equippedItem) {
        this.equippedItem = equippedItem;
    }
}
