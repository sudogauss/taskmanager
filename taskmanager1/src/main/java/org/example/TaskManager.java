package org.example;

import java.util.ArrayList;

public class TaskManager {

    private static ArrayList<PersonalTask> personalTaskArrayList = new ArrayList<PersonalTask>();
    private ArrayList<TeamTask> teamTaskArrayList;


    public static void addPersonalTask(double importance, String theme, String description, String deadline, String username) {
        try {
            personalTaskArrayList.add(new PersonalTask(personalTaskArrayList.size(), importance, theme, description, deadline, username));
            System.out.println(personalTaskArrayList.get(personalTaskArrayList.size() - 1).getDeadline());
        } catch (GlobalTask.WrongFormatException e) {
            e.printStackTrace();
        }
    }
}
