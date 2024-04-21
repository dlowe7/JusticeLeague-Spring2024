import java.io.Serializable;
import java.util.List;



public class Player implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Map gameMap;


	private String currentRoomId;
	private int maxHealth;
	private int currentHealth;
	private int level;
	private int baseDamage;
	private int exp;
	private int attackPower;
	private static final int EXP_REQUIRED = 300;
	private Equipment equippedWeapon;  // Declare equippedWeapon as an instance of Equipment.
	private InventoryManager inventoryManager;
	private CombatManager combatManager;


	// Constructor to initialize the player with starting values
	public Player(String startingRoomId, int maxHealth, int currentHealth, int level, int baseDamage, int exp) 
		{
			this.currentRoomId = startingRoomId;
			this.maxHealth = maxHealth;
			this.currentHealth = currentHealth;
			this.level = level;
			this.baseDamage = baseDamage;
			this.exp = exp;
			this.inventoryManager = new InventoryManager();
			this.equippedWeapon = null; // Initially, no equipment is equipped.
			this.combatManager = new CombatManager(this);

		}

	// Getters and Setters
	public String getCurrentRoomId() 
		{
			return currentRoomId;
		}

	public void setCurrentRoomId(String roomId) 
		{
			this.currentRoomId = roomId;
		}

	public int getMaxHealth() 
		{
			return maxHealth;
		}

	public void setMaxHealth(int maxHealth) 
		{
			this.maxHealth = maxHealth;
		}

	public int getCurrentHealth() 
		{
			return currentHealth;
		}

	public void setCurrentHealth(int health) 
		{
			this.currentHealth = health;
		}

	public int getLevel() 
		{
			return level;
		}

	public int getBaseDamage() 
		{
			return baseDamage;
		}

	public void setBaseDamage(int baseDamage) 
		{
			this.baseDamage = baseDamage;
		}

	public InventoryManager getInventoryManager() 
		{
			return inventoryManager;
		}

	public CombatManager getCombatManager() 
		{
			return combatManager;
		}


	public void accessInventory() {
		System.out.println("Accessing inventory:");
		List<Items> items = inventoryManager.getInventory();
		if (items.isEmpty()) {
			System.out.println("Your inventory is empty.");
		} else {
			for (Items item : items) {
				System.out.println("- " + item.getName());
			}
		}
	}

	public void addItem(Items item) 
		{
			inventoryManager.addItem(item);
			System.out.println(item.getName() + " has been added to your inventory.");
		}

	public void dropItem(String itemName) {
		Items item = inventoryManager.getItemByName(itemName);
		if (item != null) {
			inventoryManager.removeItem(item);
			System.out.println(itemName + " has been dropped from your inventory.");
		} else {
			System.out.println("No item named " + itemName + " found in inventory.");
		}
	}


	public void useItem(String itemName) {
		Items item = inventoryManager.getItemByName(itemName);
		if (item != null) {
			inventoryManager.useItem(item, this);  // Assumes useItem method handles effect application
			System.out.println("Using " + itemName);
		} else {
			System.out.println("No item named " + itemName + " found in inventory.");
		}
	}


	public void equipWeapon(Equipment weapon) {
		if (weapon != null && weapon.isEquipable()) {
			if (equippedWeapon != null) {
				unequipWeapon();  // If already equipped, unequip the current weapon first
			}
			equippedWeapon = weapon;
			System.out.println(weapon.getName() + " equipped.");
			adjustAttributesForEquipment(weapon, true);
		}
	}

	// Method to unequip the current weapon
	public void unequipWeapon() {
		if (equippedWeapon != null) {
			System.out.println(equippedWeapon.getName() + " unequipped.");
			adjustAttributesForEquipment(equippedWeapon, false);
			equippedWeapon = null;
		}
	}

	private void adjustAttributesForEquipment(Equipment equipment, boolean equip) {
		if (equip) {
			// Assuming the Equipment might affect different attributes like attack, defense
			this.baseDamage += equipment.getAttackIncrease();  // Increase attack
		} else {
			this.baseDamage -= equipment.getAttackIncrease();  // Decrease attack
		}
	}

	public Equipment getEquippedWeapon() {
        return equippedWeapon;
    }

    public void setEquippedWeapon(Equipment weapon) {
        this.equippedWeapon = weapon;
    }


    public void adjustAttackPower(int adjustment) {
        this.attackPower += adjustment;
    }

	public int getAttackPower() {
		return attackPower;
	}


	// Experience and Leveling System
	public void gainExperience(int amount) 
		{
			exp += amount;
			if (exp >= EXP_REQUIRED) {
				levelUp();
			}
		}

	private void levelUp() 
		{
			if (exp >= EXP_REQUIRED) 
				{
					level++;
					maxHealth += 10;
					currentHealth = maxHealth; // Fully heal the player on level up
					baseDamage += 3;
					exp -= EXP_REQUIRED; // Reduce exp by the threshold required to level up
				}
		}

	// Player Actions

	public void move(String direction) {
		Room currentRoom = gameMap.getCurrentRoom(currentRoomId);
		if (currentRoom != null) {
			String nextRoomId = currentRoom.getNextRoomId(direction);
			if (nextRoomId != null) {
				setCurrentRoomId(nextRoomId);
				System.out.println("Moved to room: " + nextRoomId);
			} else {
				System.out.println("No room in that direction!");
			}
		} else {
			System.out.println("Current room is undefined!");
		}
	}

	public void heal(int amount) 
		{
			currentHealth += amount;
			if (currentHealth > maxHealth) 
				{
					currentHealth = maxHealth;
				}
		}

	public void takeDamage(int damage) 
		{
			int effectiveDamage = damage;
			if (equippedWeapon != null) 
				{
					effectiveDamage -= equippedWeapon.getDefenseIncrease();
					effectiveDamage = Math.max(effectiveDamage, 0); // Ensure that damage does not go negative.
				}
			currentHealth -= effectiveDamage;
			if (currentHealth < 0) {
				currentHealth = 0;
			}
		}

	public boolean isDefeated() 
		{
			return currentHealth <= 0;
		}

	public int calculateDamage() 
		{
			int totalDamage = baseDamage;
			if (equippedWeapon != null) 
				{
					totalDamage += equippedWeapon.getAttackIncrease(); // Adds the attack increase from the equipped item, if any.
				}
			return totalDamage;
		}

	public void setGameMap(Map map) 
		{
			this.gameMap = map;
		}

	// Debugging and Information
	public String getStatus() 
		{
			return "Player Status: [Health: " + currentHealth + "/" + maxHealth +
					", Level: " + level + ", Exp: " + exp + ", Base Damage: " + baseDamage + "]";
		}



	public void updateState(Player loadedPlayer) 
		{
			if (loadedPlayer != null) 
				{
					this.currentRoomId = loadedPlayer.currentRoomId;
					this.maxHealth = loadedPlayer.maxHealth;
					this.currentHealth = loadedPlayer.currentHealth;
					this.level = loadedPlayer.level;
					this.baseDamage = loadedPlayer.baseDamage;
					this.exp = loadedPlayer.exp;
					// Assuming equippedWeapon and inventoryManager are properly handled
					this.equippedWeapon = loadedPlayer.equippedWeapon;
					// Note: You might need to deep copy inventory or handle it differently
					this.inventoryManager = loadedPlayer.inventoryManager;
				}
		}
}
