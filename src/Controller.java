import java.util.Scanner;
public class Controller {

    public static final String BOLD = "\u001B[1m";
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    public void startGame() {
        Player player = player.getPlayer();
        view.displayWelcomeMessage();
        while (true) {
            Room currentRoom = player.getCurrentRoom();
            view.displayRoom(currentRoom);
            String input = getUserInput("What do you want to do? (Type 'help' to view commands): ");
            parseInput(input);
        }
    }

    private void parseInput(String input) {
        String[] inputParts = input.split(" ");
        String command = inputParts[0];
        String itemName = inputParts.length > 1 ? inputParts[1] : "";
        String puzzleName = inputParts.length > 1 ? inputParts[1] : "";
        String monsterName = inputParts.length > 1 ? inputParts[1] : "";
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
                player.exit();
                break;

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
                break;

            case "help":
                displayHelp();
                break;
            default:
                view.displayMessage("Invalid command. Type 'help' for available commands.");
        }
    }

    private String getUserInput(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase();
    }

    private void displayHelp() {
        view.displayMessage("Available Commands:");
        view.displayMessage("Player:");
        view.displayMessage("- Move direction-name: Move to different rooms");
        view.displayMessage("- Map: Access the game map");
        view.displayMessage("- Statistics: View player statistics");
        view.displayMessage("- Inventory: View player inventory");
        view.displayMessage("")
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