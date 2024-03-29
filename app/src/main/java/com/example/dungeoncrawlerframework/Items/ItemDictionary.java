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

        items = new Item[8];

        items[0] = new Item(R.string.aquatear,R.drawable.aquatear,1,5,true,"Equippable; +1 to Max Energy",3);

        items[1] = new Item(R.string.emerald,R.drawable.squareemerald,1,7,true,"Equippable; +1 to Skill Power",3);

        items[2] = new Item(R.string.shovel,R.drawable.shovel, 3 ,3,true,"Equippable; +3 to Attack",3);

        items[3] = new Item(R.string.miniglobe,R.drawable.bluedot,100,5,false,"Single-use; +100 to Max Energy",0);

        items[4] = new Item(R.string.battery,R.drawable.battery,100,6,false,"Single-use; +100 to Energy",0);

        items[5] = new Item(R.string.redpotion,R.drawable.redpotion,10,2,false,"Single-use; +10 to Health",0);

        items[6] = new Item(R.string.bluepotion,R.drawable.bluepotion,100,2,false,"Single-use; +100 to Health",0);

        items[7] = new Item(R.string.greenpotion,R.drawable.greenpotion,500,2,false,"Single-use; +500 to Health",0);

    }

    public Item getItem(int index) {
        return items[index];
    }


}
