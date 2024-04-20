public class Monster
{
    String ID, name, description, abilities, location;
    int exp, hp, attack;

    //Constructer
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
    
    
}