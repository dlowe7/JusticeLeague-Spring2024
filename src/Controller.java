public class Controller {

	private Map game;

	public Controller(Map game) 
		{
			this.game = game;
		}

	public void startGame() 
		{
			System.out.println("Game started!");

			// Initialize the game state
			initializeGame();

			// Start the game loop
			gameLoop();
		}

	private void initializeGame() 
		{
			// Perform any necessary initialization tasks here
			// For example, finding the starting room and initializing the player
			Room startingRoom = findStartingRoom();
			if (startingRoom != null) {
				initializePlayer(startingRoom);
			} else {
				System.out.println("Starting room not found!");
			}
		}

	private void initializePlayer(Room startingRoom) 
		{
			// Add logic to initialize the player in the starting room
			// This is just a placeholder implementation
			game.getAllPlayerStats().setCurrentRoomId(startingRoom);
		}

	private Room findStartingRoom() 
		{
			// Implement logic to find the starting room
			// This is just a placeholder implementation
			for (Room room : game.getAllRooms()) 
				{
					if (room.getRoomId().equals(game.getAllPlayerStats().getStartingRoomId())) 
						{
							return room;
						}
				}
			return null;
		}


	private void gameLoop() 
		{
			// Implement the main game loop here
			// This loop could handle player input, update game state, and display output
			// It would continue until the game is over
			// This is just a placeholder implementation
			System.out.println("Game loop running...");
		}

	//This has to go in the view
	/*  public static final String BOLD = "\u001B[1m";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m"; */

	private void parseInput(String input) {
		String[] inputParts = input.split(" ");
		String command = inputParts[0];
		Player player;

		switch (command) {

			//Player FRs
		case "move":
			player.move(direction);
			break;
		case "inventory":
			player.accessInventory();
			break;
		case "map":
			player.accessMap();
			break;
		case "statistics":
			player.accessStatistics();
		case "save":
		case "load":
		case "exit":

			//Item Frs
		case "pickup":
			player.pickup(itemName);
			break;
		case "drop":
			player.drop(itemName);
			break;
		case "use":
			player.useDamagingItem(itemName);
			break;
		case "consume":
			player.useHealingItem(itemName);
			break;
		case "equip":
			player.equip(itemName);
			break;
		case "unequip":
			player.unequip();
			break;

			//Multiple examine methods, must combine or differentiate commands
		case "examine":
			item.examine();
			break;

			//Puzzle Frs
		case "hint":
			player.hint();
			break;
		case "solve":
			puzzle.solve();
			break;

			//Monster Frs
		case "engage":
			player.engage();
			break;
		case "ignore":
			player.ignore();
			break;
		case "attack":
			player.attack();

		default:
			view.displayMessage("Invalid command. Type 'help' for available commands.");
		}
	}

	private void displayHelp() {
		view.displayMessage("Available Commands:");
		view.displayMessage("Player:");
		view.displayMessage("- Move direction-name: Move to different rooms");
		view.displayMessage("- Map: Access the game map");
		view.displayMessage("- Statistics: View player statistics");
		view.displayMessage("- Inventory: View player inventory");
		//Save, Load, Exit
		view.displayMessage("Items:");
		view.displayMessage("- Pickup item-name: Pick up items");
		view.displayMessage("- Drop item-name: Drop items");
		view.displayMessage("- Use item-name: Use items");
		view.displayMessage("- Consume item-name: Consume items");
		view.displayMessage("- Equip item-name: Equip items");
		view.displayMessage("- Unequip: Unequip items");
	}
}