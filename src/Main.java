import java.io.File;

//import java.lang;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello world");
        Map game = new Map();
        game.readRooms(new File("Rooms.txt"));
        game.readMonster(new File("Monsters.txt"));
        game.readPlayerStats(new File("Player.txt"));
        game.readItems(new File("Items.txt"))
    }
}