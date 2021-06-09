package com.example.dungeoncrawlerframework.DungeonStructure;

import java.util.ArrayList;
import java.util.Arrays;

public class DungeonFloorDictionary {

    private ArrayList<DungeonFloor> floorList = new ArrayList<DungeonFloor>(){};

    public DungeonFloorDictionary() {
        DungeonFloor dungeonFloor0 = new DungeonFloor(new ArrayList<Integer>(Arrays.asList(0,1)));
        floorList.add(0,dungeonFloor0);/*

        DungeonFloor dungeonFloor1 = new DungeonFloor(new ArrayList<Integer>(Arrays.asList(2,3)));
        floorList.add(1,dungeonFloor1);

        DungeonFloor dungeonFloor2 = new DungeonFloor(new ArrayList<Integer>(Arrays.asList(4,5)));
        floorList.add(2,dungeonFloor2);

        DungeonFloor dungeonFloor3 = new DungeonFloor(new ArrayList<Integer>(Arrays.asList(6)));
        floorList.add(3,dungeonFloor3);*/
    }

    public DungeonFloor getDungeonFloor(int floorIndex){
        DungeonFloor dungeonFloor = floorList.get(floorIndex);
        return dungeonFloor;
    }

    public void addDungeonFloors(int numFloors){
        for (int i=0; i<numFloors;i++){
            DungeonFloor dungeonFloorN = new DungeonFloor(new ArrayList<Integer>(Arrays.asList(6)));
            floorList.add(floorList.size(),dungeonFloorN);
        }
    }


}
