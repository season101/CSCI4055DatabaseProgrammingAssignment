package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.text.DecimalFormat;

// {Page 1} Project Executive Summary
public final class ProjectExecutiveSummary {

    private static StringBuffer result = new StringBuffer();

    public static final String executeProjectExecutiveSummary() throws Exception {

        Connection con = DBConnection.getConnection();

        if (!con.isClosed()) {
            ArrayList<String> Dept = new ArrayList<>();
            DecimalFormat formatter = new DecimalFormat("$ #,###.00");

            ResultSet rs = con.createStatement().executeQuery("SELECT Dname, CONCAT(Fname,' ',Minit,'. ',Lname) As MGR, Pname FROM DEPARTMENT,EMPLOYEE , PROJECT WHERE EMPLOYEE.Ssn = DEPARTMENT.Mgr_ssn AND PROJECT.Dnum = DEPARTMENT.Dnumber ORDER BY Dname, Pname;");

            ResultSet empInfo = con.createStatement().executeQuery("SELECT COUNT(DISTINCT Fname) AS num, SUM(Hours) AS hrs,SUM(Salary*(Hours/40)) AS FC, Pname FROM EMPLOYEE, PROJECT, WORKS_ON, DEPARTMENT WHERE Ssn = Essn AND Pnumber=Pno AND Dnumber = Dnum AND Hours IS NOT NULL GROUP BY Pname ORDER BY Dname,Pname;");

            while (rs.next() && empInfo.next()) {

                String DeptName = rs.getString("Dname");

                if (!Dept.contains(DeptName)) {

                    result.append(rs.getString("Dname") + " - " + rs.getString("MGR") + "\n");
                    result.append(String.format("%-4s%-24s"," " ,rs.getString("Pname") + " "));
                    result.append(String.format("%6d %10.1f%15s\n",empInfo.getInt("num"), empInfo.getDouble("hrs"),formatter.format(empInfo.getDouble("FC"))));

                    Dept.add(DeptName);

                }

                else {
                    result.append(String.format("%-4s%-24s"," ", rs.getString("Pname") + " "));
                    result.append(String.format("%6d %10.1f%15s\n",empInfo.getInt("num"), empInfo.getDouble("hrs"),formatter.format(empInfo.getDouble("FC"))));
                }
            }
        }
        return result.toString();
    }
}
