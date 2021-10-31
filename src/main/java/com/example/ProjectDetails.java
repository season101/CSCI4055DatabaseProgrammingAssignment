package com.example;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

//{Page 2} Project Details
public final class ProjectDetails {

    private static StringBuffer result = new StringBuffer();
    
    public static final String executeProjectDetails() throws Exception {

        Connection con = DBConnection.getConnection();

        if (!con.isClosed()) {

            ArrayList<String> departments = new ArrayList<>();
            ArrayList<String> projects = new ArrayList<>();
            String department;
            String project;
            DecimalFormat formatter = new DecimalFormat("$ #,###.00");
            Boolean print = false;
            Double hora=0.0;
            Double dinero=0.0;

            //System.out.println("Project Details");

            ResultSet rs = con.createStatement().executeQuery("SELECT CONCAT(Fname,' ',Minit,'. ',Lname) AS Name, Dname, Plocation, Pname, Hours, (Salary*(Hours/40)) AS FC, Address FROM EMPLOYEE,DEPARTMENT, PROJECT, WORKS_ON WHERE Dnum = Dnumber AND Pno = Pnumber AND Essn = Ssn AND Hours IS NOT NULL ORDER BY Dname,Pname, Lname;");

            
            while (rs.next()) {
                
                department = rs.getString("Dname");
                if (!departments.contains(department)) {
                    
                    departments.add(department);
                    project = rs.getString("Pname");
                    if (!projects.contains(project)) {
                        
                        if(print){
                            result.append("\t\t\t\t\t\t\t"+"-------------------------\n");
                            //System.out.println("\t\t\t\t\t\t\t"+"-------------------------");
                            result.append(String.format("%60s%20s\n",hora,formatter.format(dinero)));
                            //System.out.println("\t\t\t\t\t\t\t"+hora+"\t"+formatter.format(dinero)+"\n");
                            hora=0.0;
                            dinero=0.0;
                        } 

                        result.append(department+"\n");
                        //System.out.println(department);  

                        result.append("\t"+project+" ("+rs.getString("Plocation")+")"+"\n");
                        //System.out.println("\t"+project+" ("+rs.getString("Plocation")+")");
                        projects.add(project);

                        hora+= rs.getDouble("Hours");
                        dinero+= rs.getDouble("FC");

                        result.append(String.format("%20s%-30s%10.1f%20s%5s%-15s\n"," ",rs.getString("Name"),rs.getDouble("Hours"),formatter.format(rs.getDouble("FC"))," ",(rs.getString("Address")).split(",")[1].strip()));
                        //System.out.printf("%20s%-30s%10.1f%20s%5s%-15s\n"," ",rs.getString("Name"),rs.getDouble("Hours"),formatter.format(rs.getDouble("FC"))," ",(rs.getString("Address")).split(",")[1].strip());
                        
                        print = true;
                    }

                    else{
                        hora+= rs.getDouble("Hours");
                        dinero+= rs.getDouble("FC");

                        result.append(String.format("%20s%-30s%10.1f%20s%5s%-15s\n"," ",rs.getString("Name"),rs.getDouble("Hours"),formatter.format(rs.getDouble("FC"))," ",(rs.getString("Address")).split(",")[1].strip()));
                        //System.out.printf("%20s%-30s%10.1f%20s%5s%-15s\n"," ",rs.getString("Name"),rs.getDouble("Hours"),formatter.format(rs.getDouble("FC"))," ",(rs.getString("Address")).split(",")[1].strip());
                    }
                }
                else {
                    project = rs.getString("Pname");
                    
                    if (!projects.contains(project)) {   
                        if (!projects.contains(project)) {
                            if(print){
                                result.append("\t\t\t\t\t\t\t"+"-------------------------\n");
                                //System.out.println("\t\t\t\t\t\t\t"+"-------------------------");
                                //System.out.println("\t\t\t\t\t\t\t"+hora+"\t"+formatter.format(dinero)+"\n");
                                result.append(String.format("%60s%20s\n",hora,formatter.format(dinero)));
                                hora=0.0;
                                dinero=0.0;
                            }          
                        hora+= rs.getDouble("Hours");
                        dinero+= rs.getDouble("FC");            
                        projects.add(project);
                        
                        result.append("\t"+project+" ("+rs.getString("Plocation")+")"+"\n");

                        //System.out.println("\t"+project+" ("+rs.getString("Plocation")+")");
                        result.append(String.format("%20s%-30s%10.1f%20s%5s%-15s\n"," ",rs.getString("Name"),rs.getDouble("Hours"),formatter.format(rs.getDouble("FC"))," ",(rs.getString("Address")).split(",")[1].strip()));
                        //System.out.printf("%20s%-30s%10.1f%20s%5s%-15s\n"," ",rs.getString("Name"),rs.getDouble("Hours"),formatter.format(rs.getDouble("FC"))," ",(rs.getString("Address")).toString().split(",")[1].strip());

                        print = true;
                        }
                    }
                    else{
                        hora+= rs.getDouble("Hours");
                        dinero+= rs.getDouble("FC");
                        result.append(String.format("%20s%-30s%10.1f%20s%5s%-15s\n"," ",rs.getString("Name"),rs.getDouble("Hours"),formatter.format(rs.getDouble("FC"))," ",(rs.getString("Address")).split(",")[1].strip()));

                        //System.out.printf("%20s%-30s%10.1f%20s%5s%-15s\n"," ",rs.getString("Name"),rs.getDouble("Hours"),formatter.format(rs.getDouble("FC"))," ",(rs.getString("Address")).toString().split(",")[1].strip());
                    }
                }
            }
            result.append("\t\t\t\t\t\t\t"+"-------------------------\n");
            result.append(String.format("%60s%20s\n",hora,formatter.format(dinero)));

            //System.out.println("\t\t\t\t\t\t\t"+"-------------------------");
            //System.out.println("\t\t\t\t\t\t\t"+hora+"\t"+formatter.format(dinero)+"\n");
        }

        return result.toString();
    }
}
