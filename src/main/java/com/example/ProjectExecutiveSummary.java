package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.text.DecimalFormat;

// {Page 1} Project Executive Summary
public final class ProjectExecutiveSummary {

    public static final String executeProjectExecutiveSummary() throws Exception {

        Connection con = DBConnection.getConnection();

        if (!con.isClosed()) {
            ArrayList<String> Dept = new ArrayList<>();
            DecimalFormat formatter = new DecimalFormat("#,###.00");

            System.out.println("Project Executive Summary");

            ResultSet rs = con.createStatement().executeQuery(
                    "SELECT Dname, CONCAT(Fname,' ',Minit,'. ',Lname) As MGR, Pname FROM DEPARTMENT,EMPLOYEE , PROJECT WHERE EMPLOYEE.Ssn = DEPARTMENT.Mgr_ssn AND PROJECT.Dnum = DEPARTMENT.Dnumber ORDER BY Dname, Pname;");

            ResultSet empInfo = con.createStatement().executeQuery(
                    "SELECT COUNT(DISTINCT Fname) AS num, SUM(Hours) AS hrs,SUM(Salary*(Hours/40)) AS FC, Pname FROM EMPLOYEE, PROJECT, WORKS_ON, DEPARTMENT WHERE Ssn = Essn AND Pnumber=Pno AND Dnumber = Dnum AND Hours IS NOT NULL GROUP BY Pname ORDER BY Dname,Pname;");

            while (rs.next() && empInfo.next()) {

                String DeptName = rs.getString("Dname");

                if (!Dept.contains(DeptName)) {

                    System.out.println(rs.getString("Dname") + " - " + rs.getString("MGR"));
                    System.out.printf("%-24s", rs.getString("Pname") + " ");
                    System.out.printf("%10d %15.1f    $ %s\n", empInfo.getInt("num"), empInfo.getDouble("hrs"),
                            formatter.format(empInfo.getDouble("FC")));

                    Dept.add(DeptName);

                }

                else {
                    System.out.printf("%-24s", rs.getString("Pname") + " ");
                    System.out.printf("%10d %15.1f    $ %s\n", empInfo.getInt("num"), empInfo.getDouble("hrs"),
                            formatter.format(empInfo.getDouble("FC")));
                }
            }
        }
        return "";
    }
}
