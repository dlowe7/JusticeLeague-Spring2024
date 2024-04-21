public class View {

    //Storyline
    //This story follows a young warrior named Kael. Kael’s father, Finn, was once a famous knight,
    // until he died at the hands of Slash, an elusive master swordsman. Consumed by rage and grief,
    // Kael pledged an oath to avenge his father by defeating Slash. During his quest to find his
    // father’s killer, Kael encounters many different enemies across the world, and acquires items
    // that may aid him on his journey. The young warrior has found the dungeon where Slash resides,
    // and he must now hunt him down.

    public void displayWelcomeMessage() {
        System.out.println("A young warrior named Kael consumed by rage and grief over his father's death at the "
                + "hands of Slash, an elusive master swordsman. Kael pledged an oath to avenge his father by defeating "
                + "Slash. Help Kael on his quest to find his father's killer as he encounters enemies and acquires "
                + "items that may aid him on his journey.");
    }

    public void displayRoom(Room room) {
        System.out.println(room.getRoomName);
        System.out.println(room.getRoomDesc);
    }

    public void displayMessage(String message) {
        System.out.println(message);
        System.out.println();
    }
}