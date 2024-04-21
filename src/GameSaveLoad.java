import java.io.*;

public class GameSaveLoad {
    // File path for saving and loading game data
	private static final String SAVE_FILE_PATH = "game_save.dat";

    public void saveGame(Player player) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILE_PATH))) {
            out.writeObject(player);
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    public Player loadGame() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SAVE_FILE_PATH))) {
            return (Player) in.readObject(); // Cast and return the loaded Player
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game: " + e.getMessage());
            return null;
        }
    }
}

