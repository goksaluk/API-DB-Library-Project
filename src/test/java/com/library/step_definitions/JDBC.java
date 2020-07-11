package com.library.step_definitions;

import org.junit.Test;

import java.sql.*;


public class JDBC {

    @Test
    public void testConnection() throws SQLException {

        String url = "jdbc:mysql://ec2-18-233-97-71.compute-1.amazonaws.com:3306/library2";
        String username = "library2_client";
        String password = "6s2LQQTjBcGFfDhY";

        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM book_categories");
        // get the first column
        System.out.println(resultSet.getMetaData().getColumnName(1));

        // get all column names
        for (int i = 1; i < resultSet.getMetaData().getColumnCount(); i++) {
            System.out.print(resultSet.getMetaData().getColumnName(i)+"\t");
        }
        System.out.println();

        // how to print all data from the resultset

        // go through each row
        while (resultSet.next()) {
            // in each row, go through each cell
            for (int i = 1; i < resultSet.getMetaData().getColumnCount(); i++) {
                System.out.print(resultSet.getString(i)+"\t");
            }
            System.out.println();
        }

    }

}
