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
	public void readRooms(File roomFileName) {
	    Room room;
	    try (Scanner fileReader = new Scanner(roomFileName)) {
	        while (fileReader.hasNext()) {
	            String roomId = fileReader.nextLine();
	            String name = fileReader.nextLine();
	            String description = fileReader.nextLine();
	            String north = fileReader.nextLine();
	            String east = fileReader.nextLine();
	            String west = fileReader.nextLine();
	            String south = fileReader.nextLine();

	            // Find monsters for this room
	            ArrayList<Monster> roomMonsters = findMonstersForRoom(roomId);

	            room = new Room(roomId, name, description, north, east, west, south, roomMonsters);
	            allRooms.add(room);
	        }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}


	public Room getCurrentRoom(String roomId) {
		for (Room room : allRooms) {
			if (room.getRoomId().equals(roomId)) {
				return room;
			}
		}
		return null;
	}

	private ArrayList<Monster> findMonstersForRoom(String roomId) {
	    ArrayList<Monster> foundMonsters = new ArrayList<>();
	    for (Monster monster : allMonsters) {
	        if (monster.getLocation().equals(roomId)) {
	            foundMonsters.add(monster);
	        }
	    }
	    return foundMonsters; // Returns an empty list if no monsters found
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
					boolean isConsumable = Boolean.parseBoolean(fileReader.nextLine());

					boolean isEquipable = Boolean.parseBoolean(fileReader.nextLine());
					boolean isUsable = Boolean.parseBoolean(fileReader.nextLine());

					//just for initializing
					int defenseIncrease, attackIncrease;
					int healthIncrease, healthDecrease;
					String otherCondition;


					if(isEquipable) 
						{
							defenseIncrease = fileReader.nextInt();
							attackIncrease = fileReader.nextInt();
							if(fileReader.hasNext()) {
								fileReader.next();
							}

							allItems.add(new Equipment(name, description, itemID, location, isConsumable, isEquipable, isUsable, defenseIncrease, attackIncrease));
						}
					else if(isConsumable) 
						{
							healthIncrease = fileReader.nextInt();
							healthDecrease = fileReader.nextInt();
							if(fileReader.hasNext()) {
								fileReader.next();
							}

							allItems.add(new Consume(name, description, itemID, location, isConsumable, isEquipable, isUsable, healthIncrease, healthDecrease));
						}
					else if(isUsable)
						{
							otherCondition = fileReader.nextLine();
							allItems.add(new UniqueConditionItems(name, description, itemID, location, isConsumable, isEquipable, isUsable, otherCondition));
						}
				}

		}


	public Items fetchItemByName(String itemName) {
	    // Here you might check a list of all items predefined in the game
	    for (Items item : allItems) {
	        if (item.getName().equalsIgnoreCase(itemName)) {
	            return item;
	        }
	    }
	    // Optionally create a new Items object if not found
	    return new Items(itemName, "Generated Description", itemName, "No Location", false, false, false);
	}


	//this method parses data for the puzzle
	public void readPuzzle(File puzzleFileName) {
        try (Scanner fileReader = new Scanner(puzzleFileName)) {
            while(fileReader.hasNext()) {
                String puzzleID = fileReader.nextLine();
                String type = fileReader.nextLine();
                String question = fileReader.nextLine();
                String answer = fileReader.nextLine();
                String location = fileReader.nextLine();
                boolean rewardsPuzzle = Boolean.parseBoolean(fileReader.nextLine());
                String itemReward = fileReader.nextLine();
                String hintsLine = fileReader.nextLine();
                String[] hints = hintsLine.split(";");

                Puzzle puzzle = new Puzzle(puzzleID, type, question, answer, location, rewardsPuzzle, itemReward, hints, this);
                allPuzzles.add(puzzle); // Add to the list of all puzzles
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // After loading puzzles, assign them to their respective rooms
        assignPuzzlesToRooms();
    }

    private void assignPuzzlesToRooms() {
        for (Puzzle puzzle : allPuzzles) {
            Room room = getRoomById(puzzle.getLocation());
            if (room != null) {
                room.setPuzzle(puzzle);
            }
        }
    }
    
    public Room getRoomById(String roomId) {
        for (Room room : allRooms) {
            if (room.getRoomId().equals(roomId)) {
                return room;
            }
        }
        return null;
    }

	//this method parses data for the monsters
    public void readMonster(File monsterFileName) {
        try (Scanner fileReader = new Scanner(monsterFileName)) {
            while (fileReader.hasNextLine()) {
                String ID = fileReader.nextLine();
                String name = fileReader.nextLine();
                String description = fileReader.nextLine();
                String abilities = fileReader.nextLine();
                String location = fileReader.nextLine();
                int exp = Integer.parseInt(fileReader.nextLine());
                int hp = Integer.parseInt(fileReader.nextLine());
                int attack = Integer.parseInt(fileReader.nextLine());

                Monster monster = new Monster(ID, name, description, abilities, location, exp, hp, attack);
                Room room = findRoomById(location);
                if (room != null) {
                    room.addMonster(monster);
                }
                allMonsters.add(monster);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Room findRoomById(String roomId) {
        for (Room room : allRooms) {
            if (room.getRoomId().equals(roomId)) {
                return room;
            }
        }
        return null;
    }



   

	//This is to parse player information
	public Player readPlayerStats(File playerFile) throws FileNotFoundException {

	    Scanner fileReader = new Scanner(playerFile);
	    try {
	        if (fileReader.hasNextLine()) {
	            String startingRoomId = fileReader.nextLine();
	            int maxHealth = fileReader.nextInt();
	            int currentHealth = fileReader.nextInt();
	            int level = fileReader.nextInt();
	            int baseDamage = fileReader.nextInt();
	            int exp = fileReader.nextInt();
	            
	            return new Player(startingRoomId, maxHealth, currentHealth, level, baseDamage, exp);
	        }
	    } finally {
	        fileReader.close();
	    }
	    return null;  // Return null if no data was found
	}


	//All of the below methods will be uncommented out
	//once the rest of the classes are made

	public ArrayList<Room> getAllRooms()
		{
			return allRooms;
		}

	public ArrayList<Items> getAllItems()
		{
			return allItems;
		}

	public ArrayList<Puzzle> getAllPuzzles()
		{
			return allPuzzles;
		}

	public ArrayList<Monster> getAllMonsters()
		{
			return allMonsters;
		}

	public Player getAllPlayerStats()
		{
			return allPlayerStats;
		}


}

