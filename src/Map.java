import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Map
{
    //Field variables
    ArrayList<Room> allRooms = new ArrayList<Room>();
    ArrayList<Items> allItems = new ArrayList<Items>();
    ArrayList<Puzzle> allPuzzles = new ArrayList<Puzzle>();
    ArrayList<Monster> allMonsters = new ArrayList<Monster>();
    Player allPlayerStats;

    Scanner fileReader;

    //Methods

    //This method parses data for the rooms.
    //Didn't include the parse exits method as that should
    //be part of the readRooms method
    public void readRooms(File roomFileName)
    {
    	Room r;
    	try {
			fileReader = new Scanner(roomFileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	while(fileReader.hasNext()) {
    	String roomId = fileReader.nextLine();
    	String name = fileReader.nextLine();
    	String description = fileReader.nextLine();
    	String north = fileReader.nextLine();
    	String east = fileReader.nextLine();
    	String west = fileReader.nextLine();
    	String south = fileReader.nextLine();
    	r = new Room(roomId, name, description, north, east, west, south);
    	allRooms.add(r);
    	}
    	
    }

    //this method parses data for the items
    public void readItems(File itemFileName)
    {
    	try 
    	{
			fileReader = new Scanner(itemFileName);
		} catch (FileNotFoundException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	while(fileReader.hasNext()) 
    	{
    		String name = fileReader.nextLine();
    		String description = fileReader.nextLine();
    		String itemID = fileReader.nextLine();
    		String location = fileReader.nextLine(); 
			boolean isConsumable = fileReader.nextBoolean();
    		boolean isEquipable = fileReader.nextBoolean();
    		boolean isUsable = fileReader.nextBoolean();
    		
    		//just for initializing
    		int defenseIncrease, attackIncrease;
    		int healthIncrease, healthDecrease;
    		String otherCondition;
    		
    		if(isEquipable) {
    			defenseIncrease = fileReader.nextInt();
    			attackIncrease = fileReader.nextInt();
    		}
    		else if(isEquipable) {
    			healthIncrease = fileReader.nextInt();
    			healthDecrease = fileReader.nextInt();
    		}
    		else if(isUsable){
    			otherCondition = fileReader.nextLine();
    		}
    		
    	}
    	
    	
    }

    //this method parses data for the puzzle
    public void readPuzzle(String puzzleFileName)
    {

    }

    //this method parses data for the monsters
    public void readMonster(File monsterFileName)
    {
    	try {
			fileReader = new Scanner(monsterFileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	while(fileReader.hasNext()) {
    		String ID, name, description, abilities, location;
    	    int exp, hp, attack;
    	    ID = fileReader.nextLine();
    	    name = fileReader.nextLine();
    	    description = fileReader.nextLine();
    	    abilities = fileReader.nextLine();
    	    location = fileReader.nextLine();
    	    exp = fileReader.nextInt();
    	    hp = fileReader.nextInt();
    	    attack = fileReader.nextInt();
    	    //fileReader.next();//This line is to get rid of the buffer... or so I thought, but it might be needed once there is more data
    	    
    	    allMonsters.add(new Monster(ID, name, description, abilities, location, exp, hp, attack));
    	}
    }
    
    //This is to parse player information
    public void readPlayerStats(File playerStats) {
    	try {
			fileReader = new Scanner(playerStats);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	while(fileReader.hasNext()) {
    		String startingRoomId;
    		int maxHealth, currentHealth, level, baseDamage, exp;
    	    //Items[] inventory; will add later
    	    startingRoomId = fileReader.nextLine();
    	    maxHealth = fileReader.nextInt();
    	    currentHealth = fileReader.nextInt();
    	    level = fileReader.nextInt();
    	    baseDamage = fileReader.nextInt();
    	    exp = fileReader.nextInt();
    	    allPlayerStats = new Player(startingRoomId, maxHealth, currentHealth, level, baseDamage, exp);
    	}
    }

    //All of the below methods will be uncommented out
    //once the rest of the classes are made
    /*
    public List<Room> getAllRooms()
    {

    }

    public List<Items> getAllItems()
    {

    }

    public List<Puzzle> getAllPuzzles()
    {

    }

    public List<Monster> getAllMonsters()
    {

    }

    public List<Player> getAllPlayerStats()
    {

    }

     */

    //Honestly I'm not entirely sure what the purpose
    //for this will be, but we will cross that bridge
    //when we get there.
    public void connectElements()
    {

    }


}