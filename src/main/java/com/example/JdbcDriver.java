package com.example;

import java.io.FileReader;
import java.io.FileWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.Properties;

public class JdbcDriver {
    public static void main(String args[]) {
        Connection con = null;
        Statement stmt;
        ResultSet result;
        String query;
        String name;
        try {
            FileReader reader = new FileReader("src\\main\\java\\com\\example\\example.properties");
            Properties p = new Properties();
            p.load(reader);

            String dbdriver = p.getProperty("db.driver");
            String dbuser = p.getProperty("db.user");
            String dbpassword = p.getProperty("db.password");
            String dburl = p.getProperty("db.url");

            Class.forName(dbdriver);
            con = DriverManager.getConnection(dburl, dbuser, dbpassword);

            if (!con.isClosed()) {
                System.out.println("Successfully connected to " + "MySQL server using TCP/IP...");
                stmt = con.createStatement();
                query = "SELECT * FROM EMPLOYEE";
                result = stmt.executeQuery(query);
                // result.first()
                FileWriter writer = new FileWriter("ProjectReport.rtf");
                while (result.next()) {
                    name = result.getString("Fname") + " " + result.getString("Lname");
                    System.out.println(name);
                    writer.write(name + "\n");
                }
                writer.close();
            }
        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
            }
        }
    }
}
