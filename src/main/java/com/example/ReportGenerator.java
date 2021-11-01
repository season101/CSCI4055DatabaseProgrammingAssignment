package com.example;

import java.util.Scanner;
import java.io.*;

public class ReportGenerator {
    public static void main(String[] args) throws Exception {

        File report = new File("ProjectReport.rtf");
        FileWriter reportWriter = new FileWriter(report);

       // System.out.println("Project Executive Summary");
        reportWriter.write("Project Executive Summary\n");
        
        Scanner in = new Scanner(ProjectExecutiveSummary.executeProjectExecutiveSummary());
        
        while(in.hasNextLine()){
            //System.out.println(in.nextLine());
            reportWriter.write(in.nextLine()+"\n");
        }

        //System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        reportWriter.write("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");


        //System.out.println("Project Details");
        reportWriter.write("Project Details\n");


        Scanner ins = new Scanner(ProjectDetails.executeProjectDetails());
        while(ins.hasNextLine()){
            reportWriter.write(ins.nextLine()+"\n");
        }
        in.close();
        ins.close();
        reportWriter.flush();
        reportWriter.close();
    }
}
