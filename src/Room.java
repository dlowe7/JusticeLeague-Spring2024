import java.util.ArrayList;

public class Room{
    //Field variables
    String roomId;
    String roomName, roomDesc;
    //This creates a map
    String northExit, eastExit, westExit, southExit;
    ArrayList<Items> roomInventory;

    //unfinished constructor
    Room(String roomId, String roomName, String roomDesc, String northExit, String eastExit, 
    		String westExit, String southExit){
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomDesc = roomDesc;
        this.northExit = northExit;
        this.eastExit = eastExit;
        this.westExit = westExit;
        this.southExit = southExit;
        this.roomInventory = new ArrayList<>();
    }

    public ArrayList<Items> getRoomInventory() { return roomInventory; }
    
    public void addItem(Items i) { roomInventory.add(i); }
    public void removeItem(Items i) { roomInventory.remove(i); }
    
    //This is used since the parse items method in map hasn't been made at the time
    public void addItemsFromMap(ArrayList<Items> roomInventory) {
    	this.roomInventory = roomInventory;
    }

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomName() {
		return roomName;
	}

	public String getRoomDesc() {
		return roomDesc;
	}

	public String getNorthExit() {
		return northExit;
	}

	public String getEastExit() {
		return eastExit;
	}

	public String getWestExit() {
		return westExit;
	}

	public String getSouthExit() {
		return southExit;
	}
    
}