package com.example.dungeoncrawlerframework.Players;

import com.example.dungeoncrawlerframework.R;

public class PlayerClassDictionary {

    private final Player[] players;

    public PlayerClassDictionary(){

        //UPDATE WHEN YOU ADD A NEW PLAYER CLASS
        players = new Player[3];

        //todo: [Medium] reimport leftarm1 into the same shape and size of the other arms - it's duckin up the select player screen

        players[0] = new Player(10,100,1,10, R.drawable.leftarm1,"This dude is basic.",1,"PLAYER 0 SHARED PREF");

        players[1] = new Player(20,200,2,20,R.drawable.musclystickarm,"This dude is intermediate.",2,"PLAYER 1 SHARED PREF");

        players[2] = new Player(30,300,3,30,R.drawable.glovedarmright,"This dude is advanced.",3,"PLAYER 2 SHARED PREF");

    }

    public Player getPlayer(int classIndex) {
        return players[classIndex];
    }

    public int getSize(){return players.length;}

}
