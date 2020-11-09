package com.example.dungeoncrawlerframework.DungeonStructure;

import com.example.dungeoncrawlerframework.Monsters.Monster;
import com.example.dungeoncrawlerframework.Monsters.MonsterDictionary;

import java.util.ArrayList;
import java.util.Random;

public class DungeonFloor {

    //todo: [Low] Clean up code formatting (i.e. white space and comments)

    private final MonsterDictionary monsterDictionary = new MonsterDictionary();
    private ArrayList<Integer> floorMonsterIds;
    private final ArrayList<Monster> floorMonsters = new ArrayList<Monster>(){};

    DungeonFloor(ArrayList<Integer> floorMonsterIds){
        this.floorMonsterIds = floorMonsterIds;
    }

    //todo:[High] dungeon floor should have a floor boss at the end of the generated Array List
    //todo:[High] dungeon floor should have a difficulty multiplier that increases monster stats after the pre defined levels have been completed

    public ArrayList<Monster> getFloorMonsters() {
        int floorLength = 10;
        for (int i = 0; i < floorLength; i++){
            Monster newMonster = monsterDictionary.getMonster(getRandomMonsterId(floorMonsterIds));
            floorMonsters.add(newMonster);
        }
        return floorMonsters;
    }


    public ArrayList<Integer> getFloorMonstersIds(ArrayList<Integer> floorMonstersIds) {
        this.floorMonsterIds = floorMonsterIds;
        return floorMonsterIds;
    }

    public int getRandomMonsterId(ArrayList<Integer> floorMonsterIds){
        Random rand = new Random();
        return floorMonsterIds.get(rand.nextInt(floorMonsterIds.size()));
    }

}
