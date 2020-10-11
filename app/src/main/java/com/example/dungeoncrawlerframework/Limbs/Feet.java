package com.example.dungeoncrawlerframework.Limbs;

import com.example.dungeoncrawlerframework.Items.Item;

public class Feet extends Limb{
    private Item equippedItem;
    public Feet(){

    }

    public Item getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(Item equippedItem) {
        this.equippedItem = equippedItem;
    }

}
