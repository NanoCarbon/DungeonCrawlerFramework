package com.example.dungeoncrawlerframework.DungeonStructure;

import com.example.dungeoncrawlerframework.Monsters.Monster;
import com.example.dungeoncrawlerframework.Monsters.MonsterDictionary;

import java.util.ArrayList;

public class DungeonFloor {

    private MonsterDictionary monsterDictionary = new MonsterDictionary();
    private ArrayList<Monster> floorMonsters;
    private int floorNumber;

    DungeonFloor(int floorNumber){

        this.floorNumber = floorNumber;
        //todo:[High] randomly generate a list of monsters that you would fight based on the
    }

    public MonsterDictionary getMonsterDictionary() {
        return monsterDictionary;
    }

    public void setMonsterDictionary(MonsterDictionary monsterDictionary) {
        this.monsterDictionary = monsterDictionary;
    }

    public ArrayList<Monster> getFloorMonsters() {
        return floorMonsters;
    }

    public void setFloorMonsters(ArrayList<Monster> floorMonsters) {
        this.floorMonsters = floorMonsters;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

}
