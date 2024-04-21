public class View {

    public void displayWelcomeMessage() {
        System.out.println("A young warrior named Kael consumed by rage and grief over his father's death at the \n"
                + "hands of Slash, an elusive master swordsman. Kael pledged an oath to avenge his father by defeating \n"
                + "Slash. Help Kael on his quest to find his father's killer as he encounters enemies and acquires \n"
                + "items that may aid him on his journey.");
        System.out.println();
    }

    public void displayRoom(Room room) {
        if (room != null) {
            System.out.println(room.getRoomName() + "\n" + room.getRoomDesc());
        } else {
            System.out.println("No room to display.");
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
        System.out.println();
    }
    
    
    public void displayMap(String location) 
    	{
        System.out.println("Displaying map around your current location: " + location);
        // Optionally, fetch and display details about the surrounding rooms or area
        // This can be as simple or as complex as your game map allows
        //
    }
    
    public void displayRoomDetails(Room room) {
        if (room != null) {
            System.out.println("Room Name: " + room.getRoomName());
            System.out.println("Room Description: " + room.getRoomDesc());
            // Optionally display exits or other room-related info here
        } else {
            System.out.println("No room information available.");
        }
    }
    
    public void displayItemDetails(Items item) {
        System.out.println("Item Details:");
        System.out.println("Name: " + item.getName());
        System.out.println("Description: " + item.getDescription());
        System.out.println("Usable: " + (item.isUsable() ? "Yes" : "No"));
        System.out.println("Equipable: " + (item.isEquipable() ? "Yes" : "No"));
        System.out.println("Consumable: " + (item.isConsumable() ? "Yes" : "No"));
    }
}
