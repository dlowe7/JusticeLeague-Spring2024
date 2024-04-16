public class Player
{
    int currentRoomId, startingRoomId, maxHealth, currentHealth, level, baseDamage;
    Items[] inventory;

    //constructor to be uncommented once the items class is done

    Player(int currentRoomID, int startingRoomId, int maxHealth, int currentHealth,
           int level, int baseDamage, Items[] inventory)
    {
        this.currentRoomId = currentRoomId;
        this.startingRoomId = startingRoomId;
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.level = level;
        this.baseDamage = baseDamage;
        this.inventory = inventory;
    }


    public int getCurrentRoomId()
    {
        return  currentRoomId;
    }

    public void setCurrentRoomId(int id)
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