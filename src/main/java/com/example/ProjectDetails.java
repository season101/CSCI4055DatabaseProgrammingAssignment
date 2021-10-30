package com.example;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

// Project Details page 2
public class ProjectDetails {
    public static void main(String[] args) throws Exception{
        
    
    Connection con = DBConnection.getConnection();

        if (!con.isClosed()) {
            System.out.println("Project Details");
            Statement stmt = con.createStatement();
            String query = "SELECT CONCAT(Fname,' ',Minit,'. ',Lname) AS Name, Dname, Plocation, Pname, Hours, (Salary*(Hours/40)) AS FC, Address FROM EMPLOYEE,DEPARTMENT, PROJECT, WORKS_ON WHERE Dnum = Dnumber AND Pno = Pnumber AND Essn = Ssn AND Hours IS NOT NULL ORDER BY Dname,Pname, Name;";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                System.out.println(rs.getString("Name"));
            }
            

    
        }
    }
}
