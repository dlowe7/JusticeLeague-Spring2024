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
        //this.roomInventory = roomInventory;
    }
    
    //This is used since the parse items method in map hasn't been made at the time
    public void addItemsFromMap(ArrayList<Items> roomInventory) {
    	this.roomInventory = roomInventory;
    }

    public void setRoomId(int id){

    }

}