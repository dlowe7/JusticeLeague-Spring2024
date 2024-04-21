

public class Main 
{
	    public static void main(String[] args) {
	        try {
	            // Create instances of Map, Player, and View
	            Map gameMap = new Map();  // Assuming Map class has an appropriate constructor
	            Player player = new Player("StartRoom", 100, 100, 1, 10, 0);  // Sample initial player setup
	            View view = new View();  // This class should handle all user outputs

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
