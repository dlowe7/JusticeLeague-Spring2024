

public class Main 
{
	public static void main(String[] args) {
		try {
            // Create an instance of Map, which includes loading of game components within its methods
            Map gameMap = new Map();

            // View class should handle all user outputs
            View view = new View();

            // Player should be initialized within the gameMap or controller based on saved game files
            // Controller is initialized with Map, Player from Map, and View
            Controller controller = new Controller(gameMap, gameMap.getAllPlayerStats(), view);

            // Start the game through the controller
            controller.startGame();
        } catch (Exception e) {
            System.err.println("Error initializing the game: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
