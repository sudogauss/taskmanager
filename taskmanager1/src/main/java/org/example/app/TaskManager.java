package org.example.app;

import org.example.MySQLAccess.MySQLAccessor;
import org.example.Tasks.GlobalTask;
import org.example.Tasks.PersonalTask;
import org.example.Tasks.TeamTask;

import java.util.ArrayList;

public class TaskManager {

    private static ArrayList<PersonalTask> personalTaskArrayList = new ArrayList<PersonalTask>();
    private static MySQLAccessor mySQLAccessor = null;
    private ArrayList<TeamTask> teamTaskArrayList;



    public static void addPersonalTask(double importance, String theme, String description, String deadline, String username) {
        PersonalTask newPersonalTask = null;
        try {
            newPersonalTask = new PersonalTask(personalTaskArrayList.size(), importance, theme, description, deadline, username);
            personalTaskArrayList.add(newPersonalTask);
            //System.out.println(personalTaskArrayList.get(personalTaskArrayList.size() - 1).getDeadline());
        } catch (GlobalTask.WrongFormatException e) {
            e.printStackTrace();
        }
        try {
            mySQLAccessor = new MySQLAccessor();
            mySQLAccessor.addDatabasePersonalTask(200, username, newPersonalTask.getStart(), deadline, importance, theme, description, false, newPersonalTask.getDeadlineComparisonValue());
            //mySQLAccessor.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
