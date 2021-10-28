package com.example;

import java.util.Properties;
import java.io.FileReader;
import java.sql.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class JdbcExample {
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

         Class.forName(dbdriver).newInstance();
         con = DriverManager.getConnection(dburl, dbuser, dbpassword);

         if (!con.isClosed()) {
            System.out.println("Successfully connected to " + "MySQL server using TCP/IP...");
            stmt = con.createStatement();
            query = "SELECT * FROM EMPLOYEE";
            result = stmt.executeQuery(query);
            // result.first()
            FileWriter writer = new FileWriter("output.rtf");
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
