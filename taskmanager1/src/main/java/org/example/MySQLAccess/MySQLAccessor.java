package org.example.MySQLAccess;

import java.sql.*;

public class MySQLAccessor {
    private Connection connect = null;
    private PreparedStatement preparedStatement = null;

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

    public void addDatabasePersonalTask(int id, String username, String start, String deadline, double importance, String theme, String description, boolean done, long deadlineComparisonValue) {
        try {
            String insertTaskQuery = "insert into personalTasks (id, username, start, deadline, importance, theme, description, done, deadlineComparisonValue)";
            insertTaskQuery += " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connect.prepareStatement(insertTaskQuery);
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

    public int getDatabaseElementsNumber() {
        try {
            Statement elementsNumberQuery = connect.createStatement();
            ResultSet countElementsNumberResponse = elementsNumberQuery.executeQuery("select count(*) from personalTasks");
            while(countElementsNumberResponse.next()) {
                return countElementsNumberResponse.getInt(1);
            }
        } catch (SQLException throwable) {
            throwable.getSQLState();
        }
        return -1;
    }

    public void close() {
        try {
            connect.close();
        } catch (SQLException throwable) {
            throwable.getSQLState();
        }
    }
}
