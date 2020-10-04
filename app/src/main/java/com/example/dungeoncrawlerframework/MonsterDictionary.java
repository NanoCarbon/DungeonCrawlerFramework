package com.example.dungeoncrawlerframework;


public class MonsterDictionary {
    //todo: [Low] refactor this to use an ArrayList<Integer> rather than just a fixed array
    private Monster[] monsters;


    public MonsterDictionary(){

        //UPDATE WHEN YOU ADD A NEW MONSTER
        monsters = new Monster[4];

        monsters[0] = new Monster(R.string.monster1,10,1,1,R.drawable.monster1,10,0,1.0,100);

        monsters[1] = new Monster(R.string.monster2,20,2,2,R.drawable.monster2,30,0,1.0,200);

        monsters[2] = new Monster(R.string.monster3,30,3,3,R.drawable.monster3,50,0,1.0,300);

        monsters[3] = new Monster(R.string.snelly,15,1,5,R.drawable.snelly,15,0,1.0,400);

    }

    public Monster getMonster(int index){
        return monsters[index];
    }

    public int getSize(){return monsters.length;}

}
