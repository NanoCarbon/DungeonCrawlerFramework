package com.example.dungeoncrawlerframework;

public class Limb {


    //todo: [High] refactor so it's called body parts
    //todo: [High] create subclasses of body parts that will help define what type of equipment can go on each body part but not necessarily limit the classification of equipment methods that require body parts
    //arms, torso, head, ears, legs, feet

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
