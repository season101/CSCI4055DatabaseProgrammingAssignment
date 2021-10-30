package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

// Project Details page 2
public class ProjectDetails {
    public static void main(String[] args) throws Exception {

        Connection con = DBConnection.getConnection();

        if (!con.isClosed()) {
            System.out.println("Project Details");
            Statement stmt = con.createStatement();
            String query = "SELECT CONCAT(Fname,' ',Minit,'. ',Lname) AS Name, Dname, Plocation, Pname, Hours, (Salary*(Hours/40)) AS FC, Address FROM EMPLOYEE,DEPARTMENT, PROJECT, WORKS_ON WHERE Dnum = Dnumber AND Pno = Pnumber AND Essn = Ssn AND Hours IS NOT NULL ORDER BY Dname,Pname, Name;";
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<String> departments = new ArrayList<>();
            ArrayList<String> projects = new ArrayList<>();
            String department;
            String project;
            DecimalFormat formatter = new DecimalFormat("$ #,###.00");
            while (rs.next()) {
                department = rs.getString("Dname");
                if (!departments.contains(department)) {
                    System.out.println(department);
                    departments.add(department);
                    project = rs.getString("Pname");
                    if (!projects.contains(project)) {                        
                        System.out.println("\t"+project+" ("+rs.getString("Plocation")+")");
                        projects.add(project);
                        System.out.printf("%20s%-30s%10.1f%20s%5s%-15s\n"," ",rs.getString("Name"),rs.getDouble("Hours"),formatter.format(rs.getDouble("FC"))," ",(rs.getString("Address")).toString().split(",")[1].strip());
                    }
                    else{
                        System.out.printf("%20s%-30s%10.1f%20s%5s%-15s\n"," ",rs.getString("Name"),rs.getDouble("Hours"),formatter.format(rs.getDouble("FC"))," ",(rs.getString("Address")).toString().split(",")[1].strip());
                    }
                }
                else {
                    project = rs.getString("Pname");
                    if (!projects.contains(project)) {                        
                        System.out.println("\t"+project+" ("+rs.getString("Plocation")+")");
                        projects.add(project);
                        System.out.printf("%20s%-30s%10.1f%20s%5s%-15s\n"," ",rs.getString("Name"),rs.getDouble("Hours"),formatter.format(rs.getDouble("FC"))," ",(rs.getString("Address")).toString().split(",")[1].strip());
                    }
                    else{
                        System.out.printf("%20s%-30s%10.1f%20s%5s%-15s\n"," ",rs.getString("Name"),rs.getDouble("Hours"),formatter.format(rs.getDouble("FC"))," ",(rs.getString("Address")).toString().split(",")[1].strip());
                    }
                }
            }

        }
    }
}
