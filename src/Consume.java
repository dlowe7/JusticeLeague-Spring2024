
public class Consume extends Items
{

	private int healthIncrease;
	private int healthDecrease;

	public Consume(String name, String description, String itemID, String location, boolean isConsumable,
			boolean isEquipable, boolean isUsable, int healthIncrease, int healthDecrease) 
		{
			super(name, description, itemID, location, isConsumable, isEquipable, isUsable);
			this.healthIncrease = healthIncrease;
			this.healthDecrease = healthDecrease;
		}

	public int getHealthIncrease() 
		{
			return healthIncrease;
		}

	public int getHealthDecrease() 
		{
			return healthDecrease;
		}


}
