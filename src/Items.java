public class Items 
	{
		private String name;
		private String description;
		private String itemID;
		private int location;
		private boolean isConsumable;
		private boolean isEquipable;
		private boolean isUsable;

		// Constructor
		public Items(String name, String description, String itemID, int location, 
				boolean isConsumable, boolean isEquipable, boolean isUsable) 
			{
				this.name = name;
				this.description = description;
				this.itemID = itemID;
				this.location = location;
				this.isConsumable = isConsumable;
				this.isEquipable = isEquipable;
				this.isUsable = isUsable;
			}

		// Method to determine if the item is usable
		public boolean isUsable() 
			{
				return isUsable;
			}

		// Method to determine if the item is consumable
		public boolean isConsumable() 
			{
				return isConsumable;
			}

		// Method to determine if the item is equipable
		public boolean isEquipable() 
			{
				return isEquipable;
			}

		// Method to examine item details
		public String examine() 
			{
				return "Item: " + this.name + 
						"\nDescription: " + this.description +
						"\nUsable: " + this.isUsable +
						"\nConsumable: " + this.isConsumable +
						"\nEquipable: " + this.isEquipable;
			}

		// Getter for item name
		public String getName() 
			{
				return this.name;
			}

		// Getter for item description
		public String getDescription() 
			{
				return this.description;
			}

		// Getter for item ID
		public String getItemID() 
			{
				return this.itemID;
			}

		// Getter for item location
		public int getLocation() 
			{
				return this.location;
			}
	}