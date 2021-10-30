package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.text.DecimalFormat;

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

            ResultSet empInfo = stmts.executeQuery("SELECT COUNT(DISTINCT Fname) AS num, SUM(Hours) AS hrs,SUM(Salary*(Hours/40)) AS FC, Pname FROM EMPLOYEE, PROJECT, WORKS_ON, DEPARTMENT WHERE Ssn = Essn AND Pnumber=Pno AND Dnumber = Dnum AND Hours IS NOT NULL GROUP BY Pname ORDER BY Dname,Pname;");

            DecimalFormat formatter = new DecimalFormat("#,###.00");
            while (rs.next() && empInfo.next()) {
                String DeptName = rs.getString("Dname");
              
                
                if (!Dept.contains(DeptName)) {                
                    System.out.println(rs.getString("Dname") + " - " + rs.getString("MGR"));
                    System.out.printf("%-24s",rs.getString("Pname") +" ");
                    System.out.printf("%10d %15.1f    $ %s\n",empInfo.getInt("num"),empInfo.getDouble("hrs"),formatter.format(empInfo.getDouble("FC")));
                    Dept.add(DeptName);

                } else {
                    System.out.printf("%-24s",rs.getString("Pname")+" ");
                    System.out.printf("%10d %15.1f    $ %s\n",empInfo.getInt("num"),empInfo.getDouble("hrs"),formatter.format(empInfo.getDouble("FC")));
                }

            }
        }
    }

}
