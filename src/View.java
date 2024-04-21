public class View {

    public void displayWelcomeMessage() {
        System.out.println("A young warrior named Kael consumed by rage and grief over his father's death at the \n"
                + "hands of Slash, an elusive master swordsman. Kael pledged an oath to avenge his father by defeating \n"
                + "Slash. Help Kael on his quest to find his father's killer as he encounters enemies and acquires \n"
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