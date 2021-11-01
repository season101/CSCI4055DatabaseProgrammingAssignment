package com.example;

import java.util.Scanner;

public class ReportGenerator {
    public static void main(String[] args) throws Exception {

        System.out.println("Project Executive Summary");
        Scanner in = new Scanner(ProjectExecutiveSummary.executeProjectExecutiveSummary());
        
        while(in.hasNextLine()){
            System.out.println(in.nextLine());
        }

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");

        System.out.println("Project Details");

        Scanner ins = new Scanner(ProjectDetails.executeProjectDetails());
        while(ins.hasNextLine()){
            System.out.println(in.nextLine());
        }
        in.close();
        ins.close();
    }
}
