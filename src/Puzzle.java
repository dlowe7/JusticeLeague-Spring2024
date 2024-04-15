public class Puzzle
{
    int location;
    String ID, type, text, answer;

    //Constructor
    Puzzle(int location, String ID, String type, String text, String answer)
    {
        this.location = location;
        this.ID = ID;
        this.type = type;
        this.text = text;
        this.answer = answer;
    }
}