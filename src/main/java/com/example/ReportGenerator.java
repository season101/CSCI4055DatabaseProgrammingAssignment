package com.example;

public class ReportGenerator {
    public static void main(String[] args) throws Exception {
        ProjectExecutiveSummary.executeProjectExecutiveSummary();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        ProjectDetails.executeProjectDetails();
    }
}
