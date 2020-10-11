package com.example.dungeoncrawlerframework.Items;

import com.example.dungeoncrawlerframework.R;

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

        items[0] = new Item(R.string.aquatear,R.drawable.aquatear,1,5,true,"Equippable; +1 to Max Energy");
        items[1] = new Item(R.string.emerald,R.drawable.squareemerald,1,7,true,"Equippable; +1 to Skill Power");
        items[2] = new Item(R.string.shovel,R.drawable.shovel, 3 ,3,true,"Equippable; +3 to Attack");
        items[3] = new Item(R.string.miniglobe,R.drawable.bluecircle,5,100,false,"Single-use; +100 to Max Energy");


    }

    public Item getItem(int index) {
        return items[index];
    }


}
