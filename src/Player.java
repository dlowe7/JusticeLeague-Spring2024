import java.util.ArrayList;
import java.util.List;

public class Player
{
	private String currentRoomId;
	private String startingRoomId;
	private int maxHealth;
	private int currentHealth;
	private int level;
	private int baseDamage;
	private int exp;
	private int expRequired;
	private ArrayList<Items> inventory;
	private ArrayList<Monster> monsters;


	//Constants for level ups:
	private static final int HEALTH_INCREASE = 10;
	private static final int DAMAGE_INCREASE = 3;
	private static final int EXP_REQUIRED = 300;

	//for methods or added in later
	private boolean inCombat = false;
	private boolean gameOver = false; 



	private boolean hasEquipped;
	private Equipment equipped;


	Player(String startingRoomId, int maxHealth, int currentHealth, int level, int baseDamage, int exp)
	{
		this.startingRoomId = startingRoomId;
		this.currentRoomId = startingRoomId;
		this.maxHealth = maxHealth;
		this.currentHealth = currentHealth;
		this.level = level;
		this.baseDamage = baseDamage;
		this.exp = exp;
		this.expRequired = EXP_REQUIRED; // Initialize expRequired
		this.inventory = new ArrayList<>();
	}


	public String getCurrentRoomId()
		{
			return  currentRoomId;
		}

	public void setCurrentRoomId(String id)
		{
			currentRoomId = id;
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

	public void setCurrentHealth(int currentHealth)
		{
			this.currentHealth =currentHealth;
		}

	public int getBaseDamage()
		{
			return baseDamage;
		}

	public void setBaseDamage(int baseDamage)
		{
			this.baseDamage = baseDamage;
		}


	public int getExp() 
		{
			return exp;
		}

	public void setExp(int exp) 
		{
			this.exp = exp;
		}

	public int getExpRequired() 
		{
			return expRequired;
		}

	public void setExpRequired(int expRequired) 
		{
			this.expRequired = expRequired;
		}

	public String getStartingRoomId()
		{
			return startingRoomId;
		}


	public void setStartingRoomId(String startingRoomId)
		{
			this.startingRoomId = startingRoomId;
		}
	public ArrayList<Items> getInventory() 
		{
			return inventory;
		}

	public void setInventory(ArrayList<Items> inventory) 
		{
			this.inventory = inventory;
		}
	public int getLevel()
		{
			return level;
		}

	public boolean isGameOver()
		{
			return gameOver;
		}


	public void setGameOver(boolean gameOver)
		{
			this.gameOver = gameOver;
		}
	//hard coded for time's sake
	public void levelUp() 
		{
			if (exp >= EXP_REQUIRED) 
				{
					maxHealth += HEALTH_INCREASE;
					currentHealth += HEALTH_INCREASE;
					baseDamage += DAMAGE_INCREASE;
					expRequired += 300;
					level++;
				}
		}

	public void move(String direction)
		{
			System.out.println("Moving player " + direction);		
		}

	public void accessInventory()
		{
			for(int i = 0; i <= inventory.size(); i++) 
				{
					System.out.println(inventory.get(i).getName()); //Will change for the view method later
				}
		}


	//Might have to scrap
	public void accessMap()
		{

		}

	//might have to scrap
	public void accessStatistics()
		{
			//View directions moved in, rooms visited, items acquired, puzzles solved, and monsters fought
			System.out.println("Accessed game statistics");

		}


	public void pickup(Items item, Room currentRoom)
		{
			List<Items> roomItems = currentRoom.getRoomInventory();
			List<Items> itemsToRemove = new ArrayList<>();
			boolean itemFound = false;
			for (Items currentItem : roomItems) {
				if (currentItem.getName().equals(item.getName())) {
					itemsToRemove.add(currentItem);
					inventory.add(currentItem);
					itemFound = true;
					System.out.println("Picked up " + item.getName());
					break;
				}
			}

			if (!itemFound) {
				System.out.println("Item not found");
			}
			roomItems.removeAll(itemsToRemove);
		}

	public void drop(Items i, Room currentRoom)
		{
			List<Items> itemsToRemove = new ArrayList<>();
			boolean itemFound = false;
			for (Items currentItem : inventory) {
				if (currentItem.getName().equals(i.getName())) {
					itemsToRemove.add(currentItem);
					currentRoom.addItem(i);
					itemFound = true;
					System.out.println("Dropped " + i.getName());
					break;
				}
			}
			if (!itemFound) {
				System.out.println("Item not found");
			}
			inventory.removeAll(itemsToRemove);
		}

	public void useHealingItem(Items item) {
		boolean itemFound = false;
		for (Items currentItem : inventory) {
			if (currentItem.getName().equals(item.getName()) && currentItem.isConsumable()) {
				int healthIncrease = ((Consume) currentItem).getHealthIncrease();
				currentHealth += healthIncrease;
				inventory.remove(currentItem);
				System.out.println("Consumed " + currentItem.getName());
				itemFound = true;
				break; // Exit the loop if a consumable item is found
			}
		}
		if (!itemFound) {
			System.out.println("Item cannot be consumed");
		}
	}



	public void dealDamage(Monster monster) 
		{
			int damage;
			if (hasEquipped) 
				{
					damage = equipped.getAttackIncrease() + baseDamage;
				} 
			else 
				{
					damage = baseDamage;
				}
			monster.takeDamage(damage);
		}

	public void useDamagingItem(Items item) {
		boolean itemFound = false;
		for (Items currentItem : inventory) {
			if (currentItem.getName().equalsIgnoreCase(item.getName()) && currentItem.isUsable()) {
				if (currentItem instanceof Equipment) {
					Equipment equipmentItem = (Equipment) currentItem;
					int damageIncrease = equipmentItem.getAttackIncrease();
					// Combine player's base damage and item's damage increase
					int totalDamage = baseDamage + damageIncrease;
					// Call the attack method with the total damage
					attack(totalDamage);
					inventory.remove(currentItem);
					System.out.println("Used " + currentItem.getName()); // Print the name of the item
					itemFound = true;
					break; // No need to continue searching if item is found and used
				} else {
					System.out.println("Item is not usable for damaging");
					itemFound = true;
					break;
				}
			}
		}

		if (!itemFound) {
			System.out.println("Item cannot be used or not found in inventory");
		}
	}


	public void equip(Items item) 
		{
			if (inventory.contains(item)) 
				{
					equipped = (Equipment) item;
					hasEquipped = true;
				}
		}

	public void unequip() 
		{
			if (hasEquipped) 
				{
					equipped = null;
					hasEquipped = false;
				}
		}

	public int calculateMonsterRewards(Monster monster) 
		{
			return monster.calculateRewards();
		}

	public void hint()
		{

		}

	public void type()
		{

		}

	//This should probably go in the controller class
	public void exit()
		{
			System.out.println("Exited the game");
		}

	public StringBuilder examineRoom(int roomId)
		{
			System.out.println("Examined room " + roomId);
			return new StringBuilder();
		}

	public StringBuilder examinePuzzle(int puzzleId)
		{
			System.out.println("Examined puzzle " + puzzleId);
			return new StringBuilder();
		}

	//Is this supposed to be named examineItem? - Destiny 
	public StringBuilder examineRoom(String itemName)
		{
			return new StringBuilder();
		}

	public void engage(ArrayList<Monster> monsters) {
		for (Monster monster : monsters) {
			if (monster.getLocation().equals(currentRoomId)) {
				setInCombat(true);
				break;
			}
		}
	}


	public boolean isDefeated() 
		{
			return currentHealth <= 0;
		}

	//This method should not need to take in an int
	//but may need to take in a monster.
	//May change void to boolean to show if the player won or
	//lost.

	public int calculateDamage() 
		{
			// Calculate damage based on equipped item and base damage
			int damageDealt;
			if (hasEquipped) {
				damageDealt = equipped.getAttackIncrease() + baseDamage;
			} else {
				damageDealt = baseDamage;
			}
			return damageDealt;
		}

	public void attack(int damage) {
		// Find the monster in the current room
		Monster currentMonster = null;
		for (Monster monster : monsters) {
			if (monster.getLocation().equals(currentRoomId)) {
				currentMonster = monster;
				break;
			}
		}

		// If no monster is found in the current room, return
		if (currentMonster == null) {
			System.out.println("There are no monsters in this room.");
			return;
		}

		// Display player and monster health
		System.out.println("Player HP: " + currentHealth + "/" + maxHealth);
		System.out.println(currentMonster.getName() + " HP: " + currentMonster.getHp());

		// Deal damage to the monster
		currentMonster.takeDamage(damage);

		// Display damage dealt to the monster
		System.out.println("You attacked the monster and dealt " + damage + " damage.");

		// Check if the monster is defeated
		if (currentMonster.isDefeated()) {
			System.out.println("You defeated the monster!");
			setInCombat(false);
		} else {
			// Monster attacks back
			int monsterAttack = currentMonster.getAttack();
			System.out.println("The monster attacks back and deals " + monsterAttack + " damage.");
			currentHealth -= monsterAttack;

			// Check if player is defeated
			if (currentHealth <= 0) {
				System.out.println("You've been defeated. Would you like to start a new game or load a save file?");
				setInCombat(false);
				gameOver = true;
			}
		}
	}


	public boolean getInCombat()
		{
			return inCombat;
		}


	public void setInCombat(boolean inCombat)
		{
			this.inCombat = inCombat;
		}
}
