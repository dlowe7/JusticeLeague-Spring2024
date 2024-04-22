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

	public void startGame() {
	    try {
	        loadGameComponents();
	        view.displayTitle();
	        view.displayWelcomeMessage();
	        enterInitialRoom();
	        gameLoop();
	    } catch (Exception e) {
	        view.displayMessage(View.RED + "Failed to start the game due to: " + e.getMessage() + View.RESET);
	        e.printStackTrace();
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
            if (waitingForPuzzleAnswer) {
                String answer = getUserInput("Please enter your answer: ");
                handlePuzzleAnswer(answer);
            } else {
                String input = getUserInput("\nWhat do you want to do next?: ");
                parseInput(input);
            }
        }
        view.displayMessage("Game Over!");
    }

	private void parseInput(String input) {
	    if (waitingForPuzzleAnswer) {
	        handlePuzzleAnswer(input);
	        return; // Continue to handle puzzle responses until resolved
	    }

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
	        case "search":
	            handleExamineRoom();
	            break;
	        case "stats":
	        	String status = player.getStatus();
	            view.displayMessage(status);  // Ensure this line properly displays the status
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
	            handleItemPickup();
	            break;
	        case "drop":
	            if (parts.length > 1) { // Ensure there is an argument after 'drop'
	                player.dropItem(parts[1]); // parts[1] should be the name of the item to drop
	            } else {
	                view.displayMessage("You must specify an item to drop.");
	            }
	            break;
	        case "use":
	            player.useItem(argument);
	            break;
	        case "equip":
	            handleEquip(argument);
	            break;
	        case "unequip":
	            player.unequipWeapon();
	            break;
	        case "examine":
	            handleExamine();
	            break;
	        case "hint": // Allow hints if a puzzle is active even outside puzzle mode
	        case "solve":
	        case "ignore":
	            handlePuzzleCommands(command);
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
	        Monster monster = currentRoom.getMonsters().get(0); // Engage with the first monster
	        enterCombat(monster); // Start combat interaction
	    } else {
	        System.out.println("\nNo monsters here to engage!");
	    }
	}
	
	
	private void enterCombat(Monster monster) {
	    boolean inCombat = true;
	    System.out.println("\n\nYou have engaged the monster: " +View.PINK + monster.getName()+View.RESET);
	    while (inCombat && !monster.isDefeated() && !player.isDefeated()) {
	        System.out.println(View.GREEN + "\nYour HP: " + player.getCurrentHealth() +View.RESET+ " | " +View.RED + monster.getName() + " HP: " + monster.getHp() +View.RESET);
	        String action = getUserInput("Choose action: 'attack', 'flee', or 'use item':").toLowerCase();

	        switch (action) {
	            case "attack":
	                performAttack(monster);
	                break;
	            case "flee":
	                if (tryFleeing()) {
	                    System.out.println(View.GREEN + "You successfully fled the battle!" + View.RESET);
	                    inCombat = false;
	                    continue;
	                } else {
	                    System.out.println("Failed to flee!");
	                }
	                break;
	            case "use item":
	                useCombatItem(); // Placeholder for using items
	                break;
	            default:
	                System.out.println("Invalid action.");
	                continue;
	        }

	        if (monster.isDefeated()) {
	            System.out.println(View.GREEN + monster.getName() + " has been defeated!" + View.RESET);
	            player.gainExperience(monster.calculateRewards());
	            Room currentRoom = gameMap.getCurrentRoom(player.getCurrentRoomId());
	            currentRoom.getMonsters().remove(monster); // Remove the defeated monster from the room
	            break;
	        } else if (!player.isDefeated()) {
	            monsterAttack(monster);
	        }
	    }
	    if (player.isDefeated()) {
	        System.out.println(View.RED + "\n\nYou have been defeated by " + monster.getName() + View.RESET);
	    }
	}
	
	private void performAttack(Monster monster) {
	    int playerAttack = player.calculateDamage();
	    monster.takeDamage(playerAttack);
	    System.out.println("\nYou attack the " + monster.getName() + " and deal " + playerAttack + " damage.");
	}

	private void monsterAttack(Monster monster) {
	    int monsterAttack = monster.getAttack();
	    player.takeDamage(monsterAttack);
	    System.out.println(monster.getName() + " attacks back and deals " + monsterAttack + " damage to you.");
	}

	private boolean tryFleeing() {
	    // Implement logic to determine if fleeing is successful, perhaps random or conditional
	    return Math.random() > 0.5;
	}

	private void useCombatItem() {
	    // Ask the player which item they want to use
	    System.out.println("\nEnter the name of the item you want to use:");
	    String itemName = scanner.nextLine();

	    // Check if the item exists and is usable
	    Items item = player.getInventoryManager().getItemByName(itemName);
	    if (item != null && item.isUsable()) {
	        // Apply the effects of the item
	        applyItemEffects(item);
	        System.out.println("You used " + itemName + ".");
	    } else {
	        System.out.println("No usable item named '" + itemName + "' found in your inventory.");
	    }
	}

	private void applyItemEffects(Items item) {
	    // Example: Implement logic based on item specifics
	    if (item.getName().equals("Health Potion")) {
	        int healAmount = 50; // Example healing amount
	        player.heal(healAmount);
	        System.out.println("Health increased by " + healAmount + ".");
	    } else if (item.getName().equals("Strength Potion")) {
	        int strengthBoost = 10; // Example attack boost
	        player.adjustAttackPower(strengthBoost);
	        System.out.println("Attack power increased by " + strengthBoost + " for the next attack.");
	    }
	    // Remove the item from inventory after use
	    player.getInventoryManager().removeItem(item);
	}
	

	// Example combat handling method
	
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
	
	public void handleExamineRoom() {
	    Room currentRoom = gameMap.getCurrentRoom(player.getCurrentRoomId());
	    if (currentRoom != null) {
	        if (!currentRoom.getRoomInventory().isEmpty()) {
	            view.displayMessage("You find the following items:");
	            for (Items item : currentRoom.getRoomInventory()) {
	                view.displayItemDetails(item); // Detailed view of each item
	            }
	        } else {
	            view.displayMessage("You find nothing of interest.");
	        }
	        if (currentRoom.getPuzzle() != null) {
	            view.displayMessage("There is a puzzle here:");
	            view.displayMessage(currentRoom.getPuzzle().examine()); // Display puzzle details
	        }
	    } else {
	        view.displayMessage("You are in an undefined space. No details are available.");
	    }
	}

	private void setCurrentRoom(Room room) {
	    if (room == null) {
	        view.displayMessage("Failed to load the room.");
	        return;
	    }
	    player.setCurrentRoomId(room.getRoomId());  // Update the player's current room ID
	    view.displayRoom(room);  // Display the room details

	    // Check for monsters in the room and alert the player
	    if (!room.getMonsters().isEmpty()) {
	    	view.displayMonsterAlert();
	        for (Monster monster : room.getMonsters()) {
	            view.displayMessage("A " + monster.getName() + " is looking right at you!"); // Display each monster's name
	        }
	        // Optionally, provide more details or options for engagement
	        view.displayMessage("Type 'engage' to fight or 'flee' to try to escape.");
	    }

	    this.currentPuzzle = room.getPuzzle();  // Update the current puzzle based on the room
	    if (this.currentPuzzle != null) {
	        view.displayMessage(View.BLUE + "\nThis room contains a puzzle.\nUse 'solve' or 'hint' to interact with it." + View.RESET);
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

	private void handlePuzzleAnswer(String answer) {
	    if (currentPuzzle == null) {
	        waitingForPuzzleAnswer = false;
	        view.displayMessage("No puzzle to solve.");
	        return;
	    }

	    String result = currentPuzzle.solve(answer, player);
	    view.displayMessage(result);

	    if (currentPuzzle.isSolved()) {
	        waitingForPuzzleAnswer = false;
	        view.displayMessage("You can continue exploring.");
	    } else if (!currentPuzzle.hasMoreAttempts()) {
	        waitingForPuzzleAnswer = false;
	        view.displayMessage("No more attempts left. The puzzle remains unsolved.");
	    } else {
	        view.displayMessage("Try again or use a hint.");
	    }
	}
	
	private void handleItemPickup() {
	    Room currentRoom = gameMap.getCurrentRoom(player.getCurrentRoomId());
	    if (currentRoom != null && !currentRoom.getRoomInventory().isEmpty()) {
	        Items item = currentRoom.getRoomInventory().get(0);  // Assumes taking the first item
	        player.addItemToInventory(item);  // Pass the Items object directly
	        currentRoom.removeItem(item);  // Remove the item from the room inventory
	        view.displayMessage(View.GREEN + "Picked up: " + item.getName() + View.RESET);  // Notify about the pickup
	    } else {
	        view.displayMessage("No items available to pick up in this room.");
	    }
	}

	private void handleEquip(String argument) {
	    Equipment weapon = (Equipment) player.getInventoryManager().getItemByName(argument);
	    if (weapon != null && weapon.isEquipable()) {
	        player.getCombatManager().equipWeapon(weapon);
	        view.displayMessage(View.GREEN + weapon.getName() + " has been equipped." + View.RESET);
	    } else {
	        view.displayMessage("No equipable weapon found by that name.");
	    }
	}

	private void handleExamine() {
	    if (currentPuzzle != null) {
	        view.displayMessage(currentPuzzle.examine());
	    } else {
	        view.displayMessage("No puzzle to examine in this room.");
	    }
	}
	
	private void handlePuzzleCommands(String command) {
	    if (currentPuzzle != null) {
	        if (command.equals("solve")) {
	            waitingForPuzzleAnswer = true; // Expect an answer on the next input
	        } else if (command.equals("hint")) {
	            view.displayMessage(currentPuzzle.giveHint());
	        } else if (command.equals("ignore")) {
	            waitingForPuzzleAnswer = false;
	            currentPuzzle = null; // Clear the puzzle
	            view.displayMessage("Puzzle ignored.");
	        }
	    } else {
	        view.displayMessage("No puzzle to " + command + ".");
	    }
	}
	
	private void displayHelp() {
		view.displayMessage("Available Commands:");
		view.displayMessage("Player:");
		view.displayMessage("- Move direction-name: Move to different rooms");
		view.displayMessage("- Map: Access the game map");
		view.displayMessage("- Stats: View player statistics");
		view.displayMessage("- Inventory: View player inventory");
		view.displayMessage("- Search: View Room Inventory");
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
