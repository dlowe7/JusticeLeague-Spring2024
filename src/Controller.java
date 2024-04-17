public class Controller {

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
}