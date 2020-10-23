/*  Class that manage the tasks and their dependencies */

package org.example.app;

import org.example.MySQLAccess.MySQLAccessor;
import org.example.Tasks.GlobalTask;
import org.example.Tasks.PersonalTask;
import org.example.Tasks.TeamTask;

import java.util.ArrayList;

public class TaskManager {

    private static ArrayList<PersonalTask> personalTaskArrayList = new ArrayList<PersonalTask>(); // list of personal tasks
    private static MySQLAccessor mySQLAccessor = null;  // accessor for database
    private static ArrayList<TeamTask> teamTaskArrayList; //list of team tasks



    public static void addPersonalTask(double importance, String theme, String description, String deadline, String username) {
        PersonalTask newPersonalTask = null;
        try {
            // TODO: 23/10/2020 get database access and COUNT elements, in order to give a good id
            mySQLAccessor = new MySQLAccessor();
            newPersonalTask = new PersonalTask(mySQLAccessor.getDatabaseElementsNumber(), importance, theme, description, deadline, username); // we create a new personal task, with incremented id
            mySQLAccessor = new MySQLAccessor();
            mySQLAccessor.addDatabasePersonalTask(newPersonalTask.getId(), username, newPersonalTask.getStart(), deadline, importance, theme, description, false, newPersonalTask.getDeadlineComparisonValue()); // we add a task to database
            personalTaskArrayList.add(newPersonalTask);
        } catch (GlobalTask.WrongFormatException e) {
            e.printStackTrace();
        }

            //mySQLAccessor.close(); ??????

    }


}
