package com.example.dungeoncrawlerframework;

public class Limb {


    //todo: [High] refactor so that the limb image id is here not in the player

    private Item equippedItem;

    public Limb(){
    }

    public Item getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(Item equippedItem) {
        this.equippedItem = equippedItem;
    }

}
