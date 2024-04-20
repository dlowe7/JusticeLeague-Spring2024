
public class UniqueConditionItems extends Items{
	//field variables
	String otherCondition;
	
	
	public UniqueConditionItems(String name, String description, String itemID, String location, boolean isConsumable,
			boolean isEquipable, boolean isUsable, String otherCondition) 
	{
		super(name, description, itemID, location, isConsumable, isEquipable, isUsable);
		this.otherCondition = otherCondition;
	}

}
