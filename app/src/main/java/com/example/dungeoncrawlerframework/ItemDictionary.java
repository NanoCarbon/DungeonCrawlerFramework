package com.example.dungeoncrawlerframework;

public class ItemDictionary {


    private Item[] items;


    public ItemDictionary() {

        //itemEffectType
        //1: max health
        //2: health
        //3: attack
        //4: defense
        //5: max energy
        //6: energy
        //7: skill power

        items = new Item[4];

        items[0] = new Item(R.string.aquatear,R.drawable.aquatear,1,5,true);
        items[1] = new Item(R.string.emerald,R.drawable.squareemerald,1,7,true);
        items[2] = new Item(R.string.shovel,R.drawable.shovel, 3 ,3,true);
        items[3] = new Item(R.string.miniglobe,R.drawable.bluecircle,5,100,false);


    }

    public Item getItem(int index) {
        return items[index];
    }


}
