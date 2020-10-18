package org.example;

import java.util.ArrayList;

public class TeamTask extends GlobalTask {

    private String teamName;
    private ArrayList<TeamTask> dependOnTasks;
    private ArrayList<TeamTask> influenceTasks;
    private ArrayList<String> teammates;

    public TeamTask(int id, double importance, String theme, String description, String deadline) throws WrongFormatException {
        super(id, importance, theme, description, deadline);
    }
}
