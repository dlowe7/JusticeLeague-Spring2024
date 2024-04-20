import java.util.ArrayList;
import java.util.List;

public class Player
{
	String currentRoomId, startingRoomId;
    int maxHealth, currentHealth, level, baseDamage, exp;
    ArrayList<Items> inventory = new ArrayList<Items>();
    
    //variables for level ups:
    int healthIncrease = 10, damageIncrease = 3, expRequired = 300;
    
    //for methods or added in later
    boolean inCombat = false;
    boolean gameOver = false;
    boolean hasEquipped;
    Equipment equipped;

    //constructor to be uncommented once the items class is done

    Player(String startingRoomId, int maxHealth, int currentHealth,
           int level, int baseDamage, int exp)
    {
        this.currentRoomId = startingRoomId;
        this.startingRoomId = startingRoomId;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.level = level;
        this.baseDamage = baseDamage;
        this.exp = exp;
        //this.inventory = inventory; may add later
    }


    public String getCurrentRoomId()
    {
        return  currentRoomId;
    }

    public void setCurrentRoomId(String id)
    {
        currentRoomId = id;
    }

    public int getMonsterRewards()
    {
        return 1;
    }

    public void setMaxHealth(int maxHealth)
    {
        this.maxHealth = maxHealth;
    }

    public int getMaxHealth()
    {
        return maxHealth;
    }

    public void setCurrentHealth(int currentHealth)
    {
        this.currentHealth =currentHealth;
    }

    public int getCurrentHealth()
    {
        return currentHealth;
    }

    public void setBaseDamage(int baseDamage)
    {
        this.baseDamage = baseDamage;
    }

    public int getBaseDamage()
    {
        return baseDamage;
    }

    //hard coded for time's sake
    public void levelUp()
    {
    	if(exp >= expRequired) {
    		maxHealth += healthIncrease;
    		currentHealth += healthIncrease;
    		baseDamage += damageIncrease;
    		expRequired += 300;
    		level += 1;
    	}
    }

    public void move(String direction)
    {
        //Player input: "move north" and "move n"
    }

    public void accessInventory()
    {
    	for(int i = 0; i <= inventory.size(); i++) {
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
    }


    public void pickup(Items i)
    {
        List<Items> roomItems = currentRoom.getRoomInventory();
        List<Items> itemsToRemove = new ArrayList<>();
        boolean itemFound = false;
        for (Items currentItem : roomItems) {
            if (currentItem.getName().equals(i.getName())) {
                itemsToRemove.add(currentItem);
                inventory.add(currentItem);
                itemFound = true;
                System.out.println("Picked up " + i.getName());
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

    public void useHealingItem(Items i) //Consume healing potions
    {
        for (Items currentItem : inventory) {
            if (currentItem.getName().equals(i) && currentItem.isConsumable()) {
                int healthIncrease = ((Consume) currentItem).getHealthIncrease();
                currentHealth += healthIncrease;
                inventory.remove(currentItem);
                System.out.println("Consumed " + i);
            }
        }
        if (!currentItem.isComsumable) {
            System.out.println("Item cannot be consumed");
        }
    }

    public void useDamagingItem(Items i) //Use damage potion? yes, and other damaging items that are consumable
    {
        for (Items currentItem : inventory) {
            if (currentItem.getName().equalsIgnoreCase(i) && currentItem.isUsable()) {
                int damageIncrease = ((Equipment) currentItem).getAttackIncrease();
                // Deal damage to monster
                inventory.remove(currentItem);
                System.out.println("Used " + i);
            }
        }
        if (!currentItem.isUsable) {
            System.out.println("Item cannot be used");
        }
    }

    public void equip(Items i)
    {

    }

    public void unequip()
    {

    }



    public void hint()
    {

    }

    public void type()
    {

    }

    //This should probably go in the controller class
    public void exit(){

    }

    public StringBuilder examineRoom(int roomId)
    {
        return new StringBuilder();
    }

    public StringBuilder examinePuzzle(int puzzleId)
    {
        return new StringBuilder();
    }

    public StringBuilder examineRoom(String itemName)
    {
        return new StringBuilder();
    }

    public void engage(ArrayList<Monster> m)
    {
    	for(int i = 0; i < m.size(); i++) {
    		if(m.get(i).equals(currentRoomId)) 
    		{
    			inCombat = true;
    		}
    	}
    }

    public void ignore() //not needed
    {

    }

    //This method should not need to take in an int
    //but may need to take in a monster.
    //May change void to boolean to show if the player won or
    //lost.
    public void attack(ArrayList<Monster> m)
    {
    	int monsterLocation = -1;
    	for(int i = 0; i < m.size(); i++) {
    		if(m.get(i).location.equals(currentRoomId)) 
    		{
    			monsterLocation = i;
    		}
    	}
    	
    	if(monsterLocation == -1) {
    		return;
    	}
    	
    	System.out.println("player hp: " + currentHealth + "/" + maxHealth);
    	System.out.println(m.get(monsterLocation).name + " hp: " + m.get(monsterLocation).hp);
    	if(hasEquipped) {
    		m.get(monsterLocation).setHealth(m.get(monsterLocation).hp - (equipped.attackIncrease + baseDamage));
    		System.out.println("You attacked the monster and dealt " + (equipped.attackIncrease + baseDamage) + " damage!");
    	}
    	else {
    		m.get(monsterLocation).setHealth(m.get(monsterLocation).hp - (baseDamage));
    		System.out.println("You attacked the monster and dealt " + baseDamage + " damage!");
    	}
    	if(m.get(monsterLocation).hp <= 0) {
    		System.out.println("You defeated the monster!");
    		inCombat = false;
    	}
    	System.out.println("player hp: " + currentHealth + "/" + maxHealth);
    	System.out.println(m.get(monsterLocation).name + " hp: " + m.get(monsterLocation).hp);
    	System.out.println("The monster is attacking back!");
    	currentHealth -= m.get(monsterLocation).attack;
    	if(currentHealth <= 0) {
    		System.out.println("You've been defeated. would you like to start a new game or load a save file?");
    		inCombat = false;
    		gameOver = true;
    	}
    	
    }
}