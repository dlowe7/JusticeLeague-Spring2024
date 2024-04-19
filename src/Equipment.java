
public class Equipment extends Items{
	int defenseIncrease, attackIncrease;

	public Equipment(String name, String description, String itemID, int location, boolean isConsumable,
			boolean isEquipable, boolean isUsable, int defenseIncrease, int attackIncrease) {
		super(name, description, itemID, location, isConsumable, isEquipable, isUsable);
		this.defenseIncrease = defenseIncrease;
		this.attackIncrease = attackIncrease;
	}
	
	

}
