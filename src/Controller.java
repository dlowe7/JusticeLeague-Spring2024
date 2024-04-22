import java.io.File;
import java.util.Scanner;




public class Controller {

	private Map gameMap;
	private Player player;
	private View view;
	private Scanner scanner;
	private GameSaveLoad gameSaveLoad = new GameSaveLoad();
	private boolean gameRunning = true;
	private boolean waitingForPuzzleAnswer = false;



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
	        if (!waitingForPuzzleAnswer) {
	            String input = getUserInput("\nWhat do you want to do next?: ");
	            parseInput(input);
	        } else {
	            String answer = getUserInput("Please enter your answer to solve the puzzle or type 'hint' for a hint:");
	            handlePuzzleAnswer(answer);
	        }
	    }
	    view.displayMessage("Game Over!");
	}

	private void parseInput(String input) {
		String[] parts = input.split(" ");
		String command = parts.length > 0 ? parts[0].toLowerCase() : "";
		String argument = parts.length > 1 ? parts[1] : "";

		if (waitingForPuzzleAnswer) {
	        handlePuzzleAnswer(input);
	        return; // Early exit to continue handling puzzle answers
	    }


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
				Items item = currentRoom.getRoomInventory().get(0);
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
			if (this.currentPuzzle != null) {
				view.displayMessage(this.currentPuzzle.examine());
			} else {
				view.displayMessage("No puzzle to examine in this room.");
			}
			break;
		case "hint":
			if (this.currentPuzzle != null) {
				view.displayMessage(this.currentPuzzle.giveHint());
			} else {
				view.displayMessage("No puzzle to get hints from.");
			}
			break;
		case "solve":
	        if (this.currentPuzzle != null) {
	            waitingForPuzzleAnswer = true; // Expect an answer on next input
	        } else {
	            view.displayMessage("No puzzle to solve.");
	        }
	        break;
		case "ignore":
	        if (this.currentPuzzle != null) {
	            view.displayMessage("Puzzle ignored.");
	            this.currentPuzzle = null; // Optionally clear the puzzle
	        } else {
	            view.displayMessage("No puzzle to ignore.");
	        }
	        break;
		case "engage":
            startCombat();
            break;
        case "flee":
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
	    Room currentRoom = gameMap.getCurrentRoom(player.getCurrentRoomId());
	    if (currentRoom != null && !currentRoom.getMonsters().isEmpty()) {
	        Monster monster = currentRoom.getMonsters().get(0);  // Get the first monster in the list
	        System.out.println("You have engaged the monster: " + monster.getName());
	        // Implement combat logic here, possibly passing 'monster' to a combat handling method

	        // Example: start a combat session
	        handleCombat(monster, currentRoom);
	    } else {
	        System.out.println("No monsters here to engage!");
	    }
	}

	// Example combat handling method
	private void handleCombat(Monster monster, Room room) {
	    // Example logic for a simple combat scenario
	    while (!monster.isDefeated() && !player.isDefeated()) {
	        int playerAttack = player.calculateDamage();
	        monster.takeDamage(playerAttack);
	        if (monster.isDefeated()) {
	            System.out.println(monster.getName() + " has been defeated!");
	            // Handle rewards, experience, etc.
	            player.gainExperience(monster.calculateRewards());
	            room.getMonsters().remove(monster);  // Remove the defeated monster from the room
	            break;
	        }

	        int monsterAttack = monster.getAttack();
	        player.takeDamage(monsterAttack);
	        if (player.isDefeated()) {
	            System.out.println("You have been defeated by " + monster.getName());
	            break;
	        }
	    }
	}

	private void avoidCombat() {
	    // Logic to avoid combat, perhaps a stealth check or returning to the previous room
	    System.out.println("You avoid combat successfully.");
	}

	private void performAttack() {
	    // Detailed logic for performing an attack, checking if a monster is present, etc.
	    System.out.println("You attack the monster.");
	}

	private boolean handleMove(String direction) {
		Room currentRoom = gameMap.getCurrentRoom(player.getCurrentRoomId());
		if (currentRoom == null) {
			view.displayMessage("You are currently in an undefined location.");
			return false;
		}

		String nextRoomId = currentRoom.getNextRoomId(direction);
		Room nextRoom = gameMap.getCurrentRoom(nextRoomId);
		if (nextRoom == null) {
			view.displayMessage("You can't move in that direction.");
			return true;
		}

		setCurrentRoom(nextRoom);
		return true;
	}

	private void setCurrentRoom(Room room) {
		if (room == null) {
			view.displayMessage("Failed to load the room.");
			return;
		}
		player.setCurrentRoomId(room.getRoomId());  // Update the player's current room ID
		view.displayRoom(room);  // Display the room details
		this.currentPuzzle = room.getPuzzle();  // Update the current puzzle based on the room

		if (this.currentPuzzle != null) {
			view.displayMessage("\nThis room contains a puzzle. Use 'solve' or 'hint' to interact with it.");
			view.displayMessage(currentPuzzle.examine());  // Automatically display the puzzle question
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

	private void handlePuzzleAnswer(String input) {
	    if (input.equalsIgnoreCase("hint")) {
	        if (currentPuzzle != null) {
	            view.displayMessage(currentPuzzle.giveHint());
	        } else {
	            view.displayMessage("No puzzle to get hints from.");
	        }
	    } else {
	        if (currentPuzzle != null) {
	            String result = currentPuzzle.solve(input);
	            view.displayMessage(result);
	            if (currentPuzzle.isSolved() || currentPuzzle.getAttempts() == 0) {
	                waitingForPuzzleAnswer = false; // Exit puzzle mode if solved or no attempts left
	            }
	        } else {
	            waitingForPuzzleAnswer = false; // Safety check
	            view.displayMessage("No puzzle to solve.");
	        }
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
