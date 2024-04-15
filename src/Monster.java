public class Monster
{
    String ID, name, description, abilities;
    int location, exp, hp, attack;

    //Constructer
    Monster(String ID, String name, String description, String abilities,
            int location, int exp, int hp, int attack)
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
}