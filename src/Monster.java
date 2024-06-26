public class Monster
	{
		String ID, name, description, abilities, location;
		int exp, hp, attack;

		//Constructor
		Monster(String ID, String name, String description, String abilities,
				String location, int exp, int hp, int attack)
		{
			this.ID = ID;
			this.name = name;
			this.description = description;
			this.abilities = abilities;
			this.location = location;
			this.exp = exp;
			this.hp = hp;
			this.attack = attack;
		}

		public int getAttack(){
			return this.attack;
		}

		public void setHealth(int h) {
			hp = h;
		}

		public String getDescription() {
			return description;
		}

		public String getAbility() {
			return abilities;
		}

		public String getID() 
			{
				return ID;
			}

		public String getName() 
			{
				return name;
			}

		public String getLocation() 
			{
				return location;
			}

		public int getExp() 
			{
				return exp;
			}

		public int getHp() 
			{
				return hp;
			}

		public void setHp(int hp) {
			this.hp = hp;
		}

		
		public void takeDamage(int damage) {
		    this.hp -= damage; // Decrease HP by the damage amount
		    if (this.hp < 0) {
		        this.hp = 0; // Ensure HP doesn't go below zero
		    }
		}

		public boolean isDefeated() 
			{
		    return hp <= 0;
		}

		

		public int calculateRewards() 
			{
				int rewards = 0;

				// Example: Calculate rewards based on monster level
				int monsterLevel = this.exp / 100; // Assuming 100 EXP per level
				rewards += monsterLevel * 5; // Example: Each level of the monster rewards 5 points

				// Example: Calculate rewards based on monster type
				if (this.name.equals("Dragon")) 
					{
						rewards += 50; // Example: Defeating a dragon grants additional rewards
					} 
				else if (this.name.equals("Orc")) 
					{
						rewards += 20; // Example: Defeating an orc grants moderate rewards
					} 
				else 
					{
						rewards += 10; // Default rewards for other types of monsters
					}

				// Assuming difficulty is based on HP and attack
				int monsterDifficulty = (this.hp + this.attack) / 10; // Example: Higher difficulty monsters grant more rewards
				rewards += monsterDifficulty * 10;

				return rewards;
			}

	}
