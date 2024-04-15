public class Items
{
    String name, description, ID;
    int location;
    boolean isConsumable, isEquipable;

    //constructer
    Items(String name, String description, String ID, int location, boolean isConsumable, boolean isEquipable){
        this.name = name;
        this.description = description;
        this.ID = ID;
        this.location = location;
        this.isConsumable = isConsumable;
        this.isEquipable = isEquipable;
    }
}