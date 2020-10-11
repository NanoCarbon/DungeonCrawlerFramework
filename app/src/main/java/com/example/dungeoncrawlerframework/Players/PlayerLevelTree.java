package com.example.dungeoncrawlerframework.Players;


//import java.util.List;

public class PlayerLevelTree {


    //todo: [Low] find a better spot to store these values and create a method to read the stored values - ideally it's an XML file and a method to read the XML files that fills in the array here
    //20200919: tried to store values in SQL Server DBMS but need middleware to access

    //CSV Reader Example code:
    /* CSV reader example code

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null) {
            // process the line
        }
    }

    BufferedReader csvReader = new BufferedReader(new FileReader("C:\Users\Edward\Desktop\)");

while ((row = csvReader.readLine()) != null) {
        String[] data = row.split(",");
        // do something with the data
    }
csvReader.close();
 */

    private final int[] lowLevelBound = {0,5,15,35,65,105,150,205,270,345,430,525,630,755,900,1065,1250,1455,1680,1925,2190,2475,2780,3135,3540,3995,4500,5055,5660,6315,7020,7775,8580,9435,10340,11295,12300,13355,14460,15615,16820,18075,19380,20735,22140,23595,25100,26655,28260,29915,31620,33375,35230,37185,39240,41395,43650,46005,48460,51015,53670,56425,59280,62235,65290,68445,71700,75055,78510,82065,85720,89475,93330,97285,101340,105495,109750,114505,119760,125515,131770,138525,145780,153535,161790,170545,179800,189555,199810,210565,221820,233575,246330,260085,274840,290595,307350,325105,343860,363615,384370};
    private final int[] hiLevelBound = {5,15,35,65,105,150,205,270,345,430,525,630,755,900,1065,1250,1455,1680,1925,2190,2475,2780,3135,3540,3995,4500,5055,5660,6315,7020,7775,8580,9435,10340,11295,12300,13355,14460,15615,16820,18075,19380,20735,22140,23595,25100,26655,28260,29915,31620,33375,35230,37185,39240,41395,43650,46005,48460,51015,53670,56425,59280,62235,65290,68445,71700,75055,78510,82065,85720,89475,93330,97285,101340,105495,109750,114505,119760,125515,131770,138525,145780,153535,161790,170545,179800,189555,199810,210565,221820,233575,246330,260085,274840,290595,307350,325105,343860,363615,384370,406125};
    private final int[] hpTree = {2,2,2,2,2,2,2,2,2,2,2,5,5,5,5,5,5,5,5,5,5,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50};
    private final int[] attackTree = {1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20};
    private final int[] defenseTree = {1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20};
    private final int[] energyTree = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    private final int[] skillPowerTree = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3};

    public int[] getLowLevelBound() {
        return lowLevelBound;
    }
    public int[] getHiLevelBound() {
        return this.hiLevelBound;
    }
    public int[] getAttackTree() {
        return attackTree;
    }
    public int[] getHpTree() {
        return hpTree;
    }
    public int[] getDefenseTree() {
        return defenseTree;
    }
    public int[] getEnergyTree() {
        return energyTree;
    }

    public int[] getSkillPowerTree() {
        return skillPowerTree;
    }


    public PlayerLevelTree() {
    }




    }





