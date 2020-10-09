package sample;

public class PersonalTask extends GlobalTask{

    public PersonalTask(int id, float importance, String theme, String description, String deadline) throws WrongFormatException {
        super(id, importance, theme, description, deadline);
    }
}
