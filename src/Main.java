import java.io.File;

public class Main 
{
	public static void main(String[] args) 
		{
			// Create an instance of Map
			Map game = new Map();

			// Read data from files and populate game objects
			game.readRooms(new File("Rooms.txt"));
			game.readItems(new File("Items.txt"));
			game.readPuzzle(new File("Puzzles.txt"));
			game.readMonster(new File("Monsters.txt"));
			game.readPlayerStats(new File("Player.txt"));

			// Create an instance of Controller and pass the game instance to its constructor
			Controller controller = new Controller(game);

			// Start the game
			controller.startGame();
		}
}