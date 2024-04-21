public class Player 
	{
		private String currentRoomId;
		private int maxHealth;
		private int currentHealth;
		private int level;
		private int baseDamage;
		private int exp;
		private static final int EXP_REQUIRED = 300;
		private Equipment equippedWeapon;  // Declare equippedWeapon as an instance of Equipment.
		private InventoryManager inventoryManager;

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
				if (equippedWeapon != null) {
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

		// Debugging and Information
		public String getStatus() 
			{
				return "Player Status: [Health: " + currentHealth + "/" + maxHealth +
						", Level: " + level + ", Exp: " + exp + ", Base Damage: " + baseDamage + "]";
			}
	}