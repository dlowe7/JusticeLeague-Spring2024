import java.io.Serializable;



public class Equipment extends Items implements Serializable
{
    private static final long serialVersionUID = 2L;  // Different from Items class
	private int defenseIncrease;
	private int attackIncrease;

	public Equipment(String name, String description, String itemID, String location, boolean isConsumable,
			boolean isEquipable, boolean isUsable, int defenseIncrease, int attackIncrease) 
		{
			super(name, description, itemID, location, isConsumable, isEquipable, isUsable);
			this.defenseIncrease = defenseIncrease;
			this.attackIncrease = attackIncrease;
		}

	public int getDefenseIncrease() 
		{
			return defenseIncrease;
		}

	public int getAttackIncrease() 
		{
			return attackIncrease;
		}


}
