import java.io.Serializable;

public class Items implements Serializable

	{
	    private static final long serialVersionUID = 1L;
		private String name;
		private String description;
		private String itemID;
		private String location; //Changed this to a string as the room Ids are strings like R1, R2, R3 and so on.
		private boolean isConsumable;
		private boolean isEquipable;
		private boolean isUsable;

		// Constructor
		public Items(String name, String description, String itemID, String location, boolean isConsumable, boolean isEquipable, boolean isUsable) 
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
		//OR public abstract void examine();
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
		public String getLocation() 
			{
				return this.location;
			}
		
		public void displayItemDetails(Items item) {
		    if (item != null) {
		        System.out.println("Item: " + item.getName());
		        System.out.println("Description: " + item.getDescription());
		        // Additional details can be shown here
		    } else {
		        System.out.println("Item not found.");
		    }
		}
	}
