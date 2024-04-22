

public class View {
	
	public static final String RESET = "\u001B[0m";
	public static final String BOLD = "\u001B[1m";
	public static final String BLUE_BG = "\u001B[44m";
	public static final String BLK_BG = "\u001B[40m";
	public static final String RED_BG = "\u001B[41m";
	public static final String GREEN_BG = "\u001B[42m";
	public static final String YELLOW_BG = "\u001B[43m";
	public static final String ORANGE_BG = "\u001B[48;5;214m";
	public static final String BLUE = "\u001B[36;1m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String ORANGE = "\u001B[38;5;214m";
	public static final String YELLOW = "\u001B[93m";
	public static final String PINK = "\u001B[95m";
	public static final String PURP = "\u001B[35m";
	public static final String GRAY_BG = "\u001B[47m";
	public static final String BLK = "\u001B[30m";
	public static final String UNDERLINE = "\u001B[4m";

	
	public void displayTitle() {
        // ANSI escape codes
        String ANSI_RED_BACKGROUND = "\u001B[41m";
        String ANSI_RESET = "\u001B[0m";

        // ASCII art for "Kael's Revenge" with red background
        System.out.println("                " +ANSI_RED_BACKGROUND + " ___  ____          __         _         _______                                                 " + ANSI_RESET);
        System.out.println("                " +ANSI_RED_BACKGROUND + "|_  ||_  _|        [  |       | |        |_   __\\                                                " + ANSI_RESET);
        System.out.println("                " +ANSI_RED_BACKGROUND + "  | |_/ /   ,--.   |  | .---.\\_|.--.     | |__) |  .---.  __    __  .--. __.--.  .--./)  .---.   " + ANSI_RESET);
        System.out.println("                " +ANSI_RED_BACKGROUND + "  |  __'.   `'_\\ : |  |/ /__\\\\ ( (`\\]    |  __ /  / /__\\[\\ /  // / /__\\[ `.-   | |  / /\\;/ /__\\  " + ANSI_RESET);
        System.out.println("                " +ANSI_RED_BACKGROUND + " _| |  \\_   // ||, |  || \\\\_.,  `'.'.    | |  \\ \\ | \\\\_.,\\/ \\// / | \\\\_|  | |  |  \\\\./| \\\\__.,   " + ANSI_RESET);
        System.out.println("                " +ANSI_RED_BACKGROUND + "|____||__| \'-;__/  [__]'.__.' [\\__) )   |___| |__|'.__ .'  \\__/   '.__.[__| |__] .',__`/ '.__.'  " + ANSI_RESET);
        System.out.println("                " +ANSI_RED_BACKGROUND + "                                                                                 ( ( __))        " + ANSI_RESET);
    }
	
	
	
	public void displayWelcomeMessage() {
	    String message = "\n\n                    A young warrior named Kael consumed by rage and grief over his father's death at the \n"
	        + "               hands of Slash, an elusive master swordsman. Kael pledged an oath to avenge his father by defeating \n"
	        + "                 Slash. Help Kael on his quest to find his father's killer as he encounters enemies and acquires \n"
	        + "                                          items that may aid him on his journey.\n\n\n";

	    // Iterate through each character in the message
	    for (char ch : message.toCharArray()) {
	        System.out.print(ch); // Print the character without a newline

	        try {
	            // Sleep for a short time between characters
	            Thread.sleep(50); // 50 ms delay
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt(); // Handle interrupted exception
	            System.out.println("Thread was interrupted, Failed to complete the typing effect.");
	        }
	    }
	}

    public void displayRoom(Room room) 
{
        if (room != null) {
            System.out.println(YELLOW + "\nYou are at the:\n" + room.getRoomName() + "\n" + room.getRoomDesc() + RESET);
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
    
    
    public void displayMonsterAlert()
		{
			pause(2000);
			String monsterArt = 
					"\n\n              .-\"\"\"\"-.\n" +
							"             /        \\\n" +
							"            /_        _\\\n" +
							"           // \\      / \\\\\n" +
							"           |\\__\\    /__/|\n" +
							"            \\    ||    /\n" +
							"             \\        /\n" +
							"              \\  __  /   \n" +
							"               '.__.'\n" +
							"                |  |\n" +
							"                |  |\n" +
							"                |__|\n";
			System.out.println(monsterArt);
			System.out.println("     "+RED_BG+ "      MONSTER ALERT!!!      " + RESET);

		}
    
    
    public static void pause(int duration) 
		{
			try 
				{
					Thread.sleep(duration);
				} 
			catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
		}
    
}
