import java.util.ArrayList;

public class Player
{
	String currentRoomId, startingRoomId;
    int maxHealth, currentHealth, level, baseDamage, exp;
    ArrayList<Items> inventory = new ArrayList<Items>();
    
    //variables for level ups:
    int healthIncrease = 10, damageIncrease = 3, expRequired = 300;

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

    public void levelUp()
    {

    }

    public void move(String command)
    {

    }

    public void accessInventory()
    {

    }

    public void accessMap()
    {

    }

    public void accessStatistics()
    {

    }


    public void pickup(Items i)
    {

    }

    public void drop(Items i)
    {

    }

    public void useHealingItem(Items i)
    {

    }

    public void useDamagingItem(Items i)
    {

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

    public void engage()
    {

    }

    public void ignore()
    {

    }

    //This method should not need to take in an int
    //but may need to take in a monster.
    //May change void to boolean to show if the player won or
    //lost.
    public void attack()
    {

    }
}