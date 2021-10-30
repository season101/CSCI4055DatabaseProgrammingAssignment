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
            String query = "SELECT Dname, CONCAT(Fname,' ',Minit,'. ',Lname) As MGR, Pname FROM DEPARTMENT,EMPLOYEE , PROJECT WHERE EMPLOYEE.Ssn = DEPARTMENT.Mgr_ssn AND PROJECT.Dnum = DEPARTMENT.Dnumber ORDER BY Dname, Pname;";
            ResultSet rs = stmt.executeQuery(query);

            ArrayList<String> Dept = new ArrayList<>();
            while (rs.next()) {
                String DeptName = rs.getString("Dname");
                if (!Dept.contains(DeptName)) {
                    ResultSet empInfo = stmt.executeQuery("SELECT COUNT(DISTINCT Fname), SUM(Hours), Pname FROM EMPLOYEE, PROJECT, WORKS_ON WHERE Pname = 'ProductZ' AND Ssn = Essn AND Pnumber=Pno;");
                    System.out.println(rs.getString("Dname") + " - " + rs.getString("MGR") + empInfo.getString("Pname"));
                    System.out.println(rs.getString("Pname"));
                    Dept.add(DeptName);

                } else {
                    System.out.println(rs.getString("Pname"));
                }

            }
        }
    }

}
