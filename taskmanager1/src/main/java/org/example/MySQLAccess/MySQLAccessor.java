/* This class is used to access database tasks
   We have following list of tables:
   -> personalTasks, for personal tasks
*/

package org.example.MySQLAccess;

import org.example.Tasks.PersonalTask;
import org.example.app.TaskManager;

import java.sql.*;

public class MySQLAccessor {

    // common fields and methods section ________________________start

    private Connection connect = null;

    private void EstablishConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/tasks?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","tsimafei","123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MySQLAccessor() {
        EstablishConnection();
    }

    public void close() {
        try {
            connect.close();
        } catch (SQLException throwable) {
            throwable.getSQLState();
        }
    }

    public int getTableElementsNumber(String tableName) {
        try {
            Statement elementsNumberQuery = connect.createStatement();
            ResultSet countElementsNumberResponse = elementsNumberQuery.executeQuery("select count(*) from " + tableName);
            while(countElementsNumberResponse.next()) {
                return countElementsNumberResponse.getInt(1);
            }
        } catch (SQLException throwable) {
            throwable.getSQLState();
        }
        return -1;
    }

    // __________________________________end of common section

    // personal tasks methods section ________________________start

    public void addDatabasePersonalTask(int id, String username, String start, String deadline, double importance, String theme, String description, boolean done, long deadlineComparisonValue) {
        try {
            String insertTaskQuery = "insert into personalTasks (id, username, start, deadline, importance, theme, description, done, deadlineComparisonValue)";
            insertTaskQuery += " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connect.prepareStatement(insertTaskQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, start);
            preparedStatement.setString(4, deadline);
            preparedStatement.setDouble(5, importance);
            preparedStatement.setString(6, theme);
            preparedStatement.setString(7, description);
            preparedStatement.setBoolean(8, done);
            preparedStatement.setLong(9, deadlineComparisonValue);

            preparedStatement.execute();

        }   catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadAllPersonalsTasks() {
        try {
            Statement allElementsQuery = connect.createStatement();
            ResultSet allElements = allElementsQuery.executeQuery("select * from personalTasks");
            while(allElements.next()) {
                int id = allElements.getInt(1);
                String username = allElements.getString(2);
                String start = allElements.getString(3);
                String deadline = allElements.getString(4);
                double importance = allElements.getDouble(5);
                String theme = allElements.getString(6);
                String description = allElements.getString(7);
                PersonalTask personalTask = new PersonalTask(id,importance,theme,description,deadline,username);
                personalTask.setStart(start);
                TaskManager.pushPersonalTaskIntoArray(personalTask);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // _________________________________ end of section


}
