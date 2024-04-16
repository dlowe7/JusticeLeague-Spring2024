import java.util.Scanner;

public class Map
{
    //Field variables
    Room[] allRooms;
    Items[] allItems;
    Puzzle[] allPuzzles;
    Monster[] allMonsters;
    Player allPlayerStats;

    Scanner fileReader;

    //Methods

    //This method parses data for the rooms.
    //Didn't include the parse exits method as that should
    //be part of the readRooms method
    public void readRooms(String roomFileName)
    {
    	fileReader = new Scanner(roomFileName);
    	String roomId = fileReader.next();
    }

    //this method parses data for the items
    public void readItems(String itemFileName)
    {

    }

    //this method parses data for the puzzle
    public void readPuzzle(String puzzleFileName)
    {

    }

    //this method parses data for the monsters
    public void readMonster(String mosterFileName)
    {

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