package com.example.dungeoncrawlerframework;

import java.util.ArrayList;

public class DungeonFloor {

    private MonsterDictionary monsterDictionary;
    private ArrayList<Monster> floorMonsters;
    private int floorNumber;

    DungeonFloor(int floorNumber){

        this.floorNumber = floorNumber;
        monsterDictionary = new MonsterDictionary();

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
