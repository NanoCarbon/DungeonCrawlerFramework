package com.example.dungeoncrawlerframework.DungeonStructure;

import com.example.dungeoncrawlerframework.Monsters.Monster;
import com.example.dungeoncrawlerframework.Monsters.MonsterDictionary;

import java.util.ArrayList;

public class DungeonFloor {

    //todo: [Medium] Clean up code formatting (i.e. white space and comments)

    private final MonsterDictionary monsterDictionary = new MonsterDictionary();
    private DungeonFloorDictionary dungeonFloorDictionary;
    private ArrayList<Monster> floorMonsters;
    private int floorNumber;

    DungeonFloor(int floorNumber){

        this.floorNumber = floorNumber;
        //todo:[CRITICAL] randomly generate a list of monsters that you would fight based on the population of the Dungeon Floor with one boss monster at the end
    }

    //todo:[Step 1] take dungeon floorNumber and pass it to a DungeonFloorDict obj to return a list of possible monsters available on this floor
    //todo:[Step 2] take the list of possible monsters on this floor their associated probabilities of showing up and generate a list of monsters for this floor (return a hash map after invoking .getFloor(int floorNumber)
    //todo:[Step 3] reference the list in the Battle Activity where each Monster that shows up is simply another record in that list
    //todo:[Note]each floor on the floor dict should take a hast map of monsters as well as a single monster as the floor boss for that dungeon
    //todo:[Step 4]create variables for the dungeon floor and stage of progress in battle activity
    //todo:[Step 5] create variables for the dungeon floor and stage within the player for progress to be saved and associated with the player
    //todo:[Step 6] create a "Map" activity so that players can select the dungeon floor they want to tackle at any point in time.




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
