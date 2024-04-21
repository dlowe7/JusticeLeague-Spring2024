import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private List<Items> inventory;

    public InventoryManager() {
        this.inventory = new ArrayList<>();
    }

    /**
     * Adds an item to the inventory.
     * @param item The item to add.
     */
    public void addItem(Items item) {
        inventory.add(item);
        System.out.println(item.getName() + " added to inventory.");
    }

    /**
     * Removes an item from the inventory.
     * @param item The item to remove.
     */
    public void removeItem(Items item) {
        if (inventory.remove(item)) {
            System.out.println(item.getName() + " removed from inventory.");
        } else {
            System.out.println("Item not found in inventory.");
        }
    }

    /**
     * Uses an item from the inventory based on its properties and affects the player.
     * @param item The item to use.
     * @param player The player on whom the item's effects will be applied.
     */
    public void useItem(Items item, Player player) {
        if (!inventory.contains(item)) {
            System.out.println("Item not in inventory.");
            return;
        }

        if (item.isConsumable()) {
            applyConsumableEffects(item, player);
            inventory.remove(item); // Consumable items are removed after use
        } else if (item.isEquipable()) {
            applyEquipableEffects(item, player);
            // Equipment might not be removed from inventory after use depending on game design
        } else if (item.isUsable()) {
            applyUsableEffects(item, player);
        }
    }

    private void applyConsumableEffects(Items item, Player player) {
        // Example: Assume all consumables increase health
        System.out.println("Using " + item.getName() + ", health increased by 20.");
        player.heal(20); // This method needs to exist in the Player class
    }

    private void applyEquipableEffects(Items item, Player player) {
        // Example: Equip item and modify player's attributes
        System.out.println(item.getName() + " equipped.");
        // This could involve setting a flag or updating player stats
    }

    private void applyUsableEffects(Items item, Player player) {
        // Example: Use item to open a locked door or reveal a hidden passage
        System.out.println(item.getName() + " used.");
    }

    /**
     * Returns the current inventory.
     * @return List of items in the inventory.
     */
    public List<Items> getInventory() {
        return new ArrayList<>(inventory); // Return a copy of the inventory list to prevent external modifications
    }
}