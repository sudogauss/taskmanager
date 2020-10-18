package org.example;


import java.util.ArrayList;

public class PersonalTask extends GlobalTask {

    private String username; // user for which task was created
    private ArrayList<PersonalTask> dependOnTasks; // this task depend on some tasks execution
    private ArrayList<PersonalTask> influenceTasks; // this task influence some tasks execution

    public PersonalTask(int id, double importance, String theme, String description, String deadline, String username) throws WrongFormatException {
        super(id, importance, theme, description, deadline);
        this.username = username;
    }

    public void addDependency(PersonalTask ancestor) {
        this.dependOnTasks.add(ancestor);
    }
    public void addInfluence(PersonalTask child) {
        this.influenceTasks.add(child);
    }
}