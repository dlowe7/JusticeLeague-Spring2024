import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Room implements Serializable
{
	private static final long serialVersionUID = 1L;
	//Field variables
	private String roomId;
	private String roomName;
	private String roomDesc;
	private HashMap<String, String> exits;  // HashMap to manage exits based on direction
	ArrayList<Items> roomInventory;
	private String currentRoomId;
    private Puzzle puzzle;  // Puzzle for the room
    private ArrayList<Monster> monsters; // List to hold monsters



	//unfinished constructor
	Room(String roomId, String roomName, String roomDesc, String northExit, String eastExit, String westExit, String southExit, ArrayList<Monster> monsters)
	{
		this.roomId = roomId;
		this.roomName = roomName;
		this.roomDesc = roomDesc;
		this.roomInventory = new ArrayList<>();
        this.monsters = monsters;  // Initialize the list of monsters


		// Initialize exits HashMap and store exits
		exits = new HashMap<>();
		exits.put("north", northExit);
		exits.put("east", eastExit);
		exits.put("west", westExit);
		exits.put("south", southExit);
	}

	public ArrayList<Items> getRoomInventory() 
		{ 
			return roomInventory; 
		}

	public List<Items> getAvailableItems() 
		{
			return new ArrayList<>(roomInventory); // Returns a copy of the list of items
		}
	public void addItem(Items i) 
		{ 
			roomInventory.add(i); 
		}
	public void removeItem(Items i) 
		{ 
			roomInventory.remove(i); 
		}

	public String getRoomId() 
		{
			return roomId;
		}

	public void setRoomId(String roomId) 
		{
			this.roomId = roomId;
		}

	public String getCurrentRoomId() 
		{
			return currentRoomId;
		}

	public void setCurrentRoomId(String currentRoomId) 
		{
			this.currentRoomId = currentRoomId;
		}

	public String getRoomName() {
		return roomName;
	}

	public String getRoomDesc() {
		return roomDesc;
	}
	
	public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }
	
	public Puzzle getPuzzle() {
        return this.puzzle;
    }

	public Items getItem() 
		{
			if (!roomInventory.isEmpty()) 
				{
					return roomInventory.remove(0); // Optionally remove the item from inventory when picked up
				}
			return null;
		}

	public String getNextRoomId(String direction) 
		{
			return exits.get(direction); // Retrieve next room based on direction
		}
	
	public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }
}
