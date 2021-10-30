package com.example;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    private static Connection con;

    public static Connection getConnection() {
        try {
            FileReader reader = new FileReader("src\\main\\java\\com\\example\\example.properties");
            Properties p = new Properties();
            p.load(reader);
            String driver = p.getProperty("db.driver");
            String user = p.getProperty("db.user");
            String password = p.getProperty("db.password");
            String url = p.getProperty("db.url");
            reader.close();

            if (con == null) {
                Class.forName(driver);
                con = DriverManager.getConnection(url, user, password);
            }

        } catch (Exception e) {
            e.getStackTrace();
        }

        return con;
    }
}
