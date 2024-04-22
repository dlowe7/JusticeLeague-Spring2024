import java.io.File;
import java.util.Scanner;




public class Controller {

	private Map gameMap;
	private Player player;
	private View view;
	private Scanner scanner;
	private GameSaveLoad gameSaveLoad = new GameSaveLoad();
	private boolean gameRunning = true;


	private Puzzle currentPuzzle; // This should be updated according to game state


	public Controller(Map gameMap, Player player, View view) {
		this.gameMap = gameMap;
		this.player = player;
		this.view = view;
		this.scanner = new Scanner(System.in);
	}

	public void startGame() 
		{
			try 
				{
					loadGameComponents();  // Load all necessary components
					view.displayWelcomeMessage();
					enterInitialRoom();  // Set the initial room based on player's starting room ID
					gameLoop();  // Start the main game interaction loop
				} 
			catch (Exception e) 
				{
					view.displayMessage("Failed to start the game due to: " + e.getMessage());
					e.printStackTrace();  // To help with debugging during development
				}
		}

	private void loadGameComponents() throws Exception 
		{
			// Load all components like rooms, items, etc.
			gameMap.readRooms(new File("Rooms.txt"));
			gameMap.readItems(new File("Items.txt"));
			gameMap.readMonster(new File("Monsters.txt"));
		    gameMap.readPuzzle(new File("Puzzles.txt")); 
		    player = gameMap.readPlayerStats(new File("Player.txt")); // Load player directly
		}



	private void enterInitialRoom() {
        Room startingRoom = gameMap.getCurrentRoom(player.getCurrentRoomId());
        if (startingRoom == null) {
            view.displayMessage("Failed to load the initial room.");
        } else {
            view.displayRoom(startingRoom);
        }
    }

	private void gameLoop() {
	    while (gameRunning && !player.isDefeated()) {
	        String input = getUserInput("\nWhat do you want to do next?: ");
	        parseInput(input);
	    }
	    view.displayMessage("Game Over!");
	}

	private void parseInput(String input) {
		String[] parts = input.split(" ");
		String command = parts.length > 0 ? parts[0].toLowerCase() : "";
		String argument = parts.length > 1 ? parts[1] : "";

		switch (command) {
		case "move":
			handleMove(argument);
			break;
		case "inventory":
			player.accessInventory();
			break;
		case "map":
			view.displayMap(player.getCurrentRoomId());
			break;
		case "stats":
			player.getStatus();
			break;
		case "save":
			gameSaveLoad.saveGame(player);
			break;
		case "load":
			Player loadedPlayer = gameSaveLoad.loadGame();
			if (loadedPlayer != null) {
				player.updateState(loadedPlayer);
				view.displayMessage("Game loaded successfully.");
			} else {
				view.displayMessage("Failed to load game.");
			}
			break;
		case "exit":
			exitGame();
			break;
		case "pickup":
			Room currentRoom = gameMap.getCurrentRoom(player.getCurrentRoomId());
			if (currentRoom != null && !currentRoom.getRoomInventory().isEmpty()) {
				Items item = currentRoom.getRoomInventory().get(0);  // Assume we always pick the first available item.
				player.addItem(item);
				currentRoom.removeItem(item);
				view.displayMessage("Picked up: " + item.getName());
			} else {
				view.displayMessage("No items available to pick up in this room.");
			}
			break;
		case "drop":
			player.dropItem(argument);
			break;
		case "use":
			player.useItem(argument);
			break;
		case "equip":
			Equipment weapon = (Equipment) player.getInventoryManager().getItemByName(argument);
			if (weapon != null && weapon.isEquipable()) {
				player.getCombatManager().equipWeapon(weapon);
				view.displayMessage(weapon.getName() + " has been equipped.");
			} else {
				view.displayMessage("No equipable weapon found by that name.");
			}
			break;
		case "unequip":
			player.unequipWeapon();
			break;
		case "examine":
			if (parts.length > 1) { // Ensure there is an argument after "examine"
				String itemName = parts[1];
				handleDisplayItemDetails(itemName);
			} else {
				view.displayMessage("Please specify an item to examine.");
			}
			break;
		case "hint":
			currentPuzzle.giveHint();
			break;
		case "solve":
			String result = currentPuzzle.solve(argument);
			if ("Correct! The puzzle is solved.".equals(result)) {
				view.displayMessage("Puzzle solved!");
			} else {
				view.displayMessage(result);  // This will show the correct status, e.g., incorrect, no attempts left, etc.
			}
			break;
		case "engage":
			startCombat();
			break;
		case "ignore":
			avoidCombat();
			break;
		case "attack":
			performAttack();
			break;
		case "help":
			displayHelp();
			break;
		default:
			view.displayMessage("Invalid command. Type 'help' for available commands.");
			break;
		}
	}

	private void startCombat() {
		// Placeholder for starting combat logic
		System.out.println("Combat started...");
	}

	private void avoidCombat() {
		// Placeholder for avoiding combat logic
		System.out.println("Combat avoided...");
	}

	private void performAttack() {
		// Placeholder for attack logic
		System.out.println("Attack performed...");
	}

	private void handleMove(String direction) {
	    Room currentRoom = gameMap.getCurrentRoom(player.getCurrentRoomId());
	    if (currentRoom != null) {
	        String nextRoomId = currentRoom.getNextRoomId(direction);
	        if (nextRoomId != null && !nextRoomId.isEmpty()) {
	            Room nextRoom = gameMap.getCurrentRoom(nextRoomId);
	            if (nextRoom != null) {
	                player.setCurrentRoomId(nextRoomId);
	                view.displayRoom(nextRoom);
	            } else {
	                view.displayMessage("There is no room in that direction.");
	            }
	        } else {
	            view.displayMessage("You can't move in that direction.");
	        }
	    } else {
	        view.displayMessage("You are not in a valid room.");
	    }
	}


	public void exitGame() 
		{
			view.displayMessage("Exiting game...");
			System.exit(0);
		}

	private String getUserInput(String prompt) 
		{
			System.out.println(prompt);
			return scanner.nextLine().toLowerCase();
		}

	public void handleDisplayItemDetails(String itemName) {
		Items item = player.getInventoryManager().getItemByName(itemName);
		if (item != null) {
			view.displayItemDetails(item); // Call display method in the View
		} else {
			view.displayMessage("Item not found.");
		}
	}

	private void displayHelp() {
		view.displayMessage("Available Commands:");
		view.displayMessage("Player:");
		view.displayMessage("- Move direction-name: Move to different rooms");
		view.displayMessage("- Map: Access the game map");
		view.displayMessage("- Statistics: View player statistics");
		view.displayMessage("- Inventory: View player inventory");
		view.displayMessage("");
		//Save, Load, Exit
		view.displayMessage("Items:");
		view.displayMessage("- Pickup item-name: Pick up items");
		view.displayMessage("- Drop item-name: Drop items");
		view.displayMessage("- Use item-name: Use items");
		view.displayMessage("- Consume item-name: Consume items");
		view.displayMessage("- Equip item-name: Equip items");
		view.displayMessage("- Unequip: Unequip items");
		view.displayMessage("");
		// Examine
		view.displayMessage("Puzzles:");
		view.displayMessage("- Hint: Get a hint");
		view.displayMessage("- Solve: Solve the puzzle");
		view.displayMessage("");

		view.displayMessage("Monsters:");
		view.displayMessage("- Engage: Enter combat with a monster");
		view.displayMessage("- Ignore: Ignore the monster");
		view.displayMessage("- Attack: Attack monsters");
	}
}
