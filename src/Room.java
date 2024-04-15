import java.util.ArrayList;

public class Room{
    //Field variables
    int roomId;
    String roomName, roomDesc, roomExits;
    ArrayList<Items> roomInventory;

    //unfinished constructor
    Room(int roomId, String roomName, String roomDesc, String roomExits, ArrayList<Items> roomInventory){
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomDesc = roomDesc;
        this.roomExits = roomExits;
        this.roomInventory = roomInventory;
    }

    public void setRoomId(int id){

    }

}