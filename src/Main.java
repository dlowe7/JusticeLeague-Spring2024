import java.io.File;

public class Main 
{
	public static void main(String[] args) {
		try {
			// Define file paths
			File roomFile = new File("Rooms.txt");
			File itemFile = new File("Items.txt");
			File puzzleFile = new File("Puzzles.txt");
			File monsterFile = new File("Monsters.txt");
			File playerFile = new File("Player.txt");


			// Create instances of Map, Player, and View
			Map gameMap = new Map();  // Assuming Map class can handle these files appropriately
			View view = new View();  // This class should handle all user outputs

			gameMap.readRooms(roomFile);
			gameMap.readItems(itemFile);
			gameMap.readPuzzle(puzzleFile);
			gameMap.readMonster(monsterFile);

			Player player = gameMap.readPlayerStats(playerFile);
			/*if (player == null) {
				System.err.println("Failed to load player data.");
				return;  // Exit if no player data could be loaded
			}*/

			// Initialize the Controller with Map, Player, and View
			Controller controller = new Controller(gameMap, player, view);

			// Start the game
			controller.startGame();
		} catch (Exception e) {
			System.err.println("Error initializing the game: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
