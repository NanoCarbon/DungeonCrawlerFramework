package com.example.dungeoncrawlerframework.Monsters;


import com.example.dungeoncrawlerframework.R;

public class MonsterDictionary {
    //todo: [Low] refactor this to use an ArrayList<Integer> rather than just a fixed array
    private final Monster[] monsters;


    public MonsterDictionary(){
        //todo:[Medium] refactor so this is an ArrayList rather than an Array
        //UPDATE WHEN YOU ADD A NEW MONSTER
        monsters = new Monster[7];

        monsters[0] = new Monster(R.string.monster1,10,1,1,R.drawable.monster1,10,0,1.0,100);

        monsters[1] = new Monster(R.string.monster2,20,2,2,R.drawable.monster2,30,1,1.0,200);

        monsters[2] = new Monster(R.string.monster3,30,3,3,R.drawable.monster3,50,2,1.0,300);

        monsters[3] = new Monster(R.string.snelly,15,1,10,R.drawable.snelly,15,3,1.0,1000);

        monsters[4] = new Monster(R.string.commongrunt,50,5,5,R.drawable.commongrunt,100,1,1.0,500);

        monsters[5] = new Monster(R.string.gruntbro,70,7,7,R.drawable.gruntbro,300,2,1.0,1000);

        monsters[6] = new Monster(R.string.gruntcaptain,100,10,10,R.drawable.gruntcaptain,500,3,1.0,1500);


    }

    public Monster getMonster(int index){
        return monsters[index];
    }

    public int getSize(){return monsters.length;}

}
