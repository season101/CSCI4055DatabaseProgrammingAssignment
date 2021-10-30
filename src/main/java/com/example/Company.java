package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Company {
    public static void main(String[] args) throws Exception {
        Connection con = DBConnection.getConnection();

        if (!con.isClosed()) {
            System.out.println("Project Executive Summary");
            Statement stmt = con.createStatement();
            Statement stmts = con.createStatement();
            String query = "SELECT Dname, CONCAT(Fname,' ',Minit,'. ',Lname) As MGR, Pname FROM DEPARTMENT,EMPLOYEE , PROJECT WHERE EMPLOYEE.Ssn = DEPARTMENT.Mgr_ssn AND PROJECT.Dnum = DEPARTMENT.Dnumber ORDER BY Dname, Pname;";
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> Dept = new ArrayList<>();

            ResultSet empInfo = stmts.executeQuery("SELECT COUNT(DISTINCT Fname) AS num, SUM(Hours) AS hrs, Pname FROM EMPLOYEE, PROJECT, WORKS_ON, DEPARTMENT WHERE Ssn = Essn AND Pnumber=Pno AND Dnumber = Dnum GROUP BY Pname ORDER BY Dname,Pname;");

            while (rs.next() && empInfo.next()) {
                String DeptName = rs.getString("Dname");
              
                
                if (!Dept.contains(DeptName)) {                
                    System.out.println(rs.getString("Dname") + " - " + rs.getString("MGR"));
                    System.out.printf("%-24s",rs.getString("Pname") +" ");
                    System.out.printf("%10d %15.1f\n",empInfo.getInt("num"),empInfo.getDouble("hrs"));
                    Dept.add(DeptName);

                } else {
                    System.out.printf("%-24s",rs.getString("Pname")+" ");
                    System.out.printf("%10d %15.1f\n",empInfo.getInt("num"),empInfo.getDouble("hrs"));
                }

            }
        }
    }

}
