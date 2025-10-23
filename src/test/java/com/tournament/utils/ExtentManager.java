package com.tournament.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static ExtentReports createInstance() {
        String reportPath = "test-output/ExtentReport.html";

        // Create directory if it doesn't exist
        new File("test-output").mkdirs();

        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

        // Configure report
        sparkReporter.config().setDocumentTitle("Tournament App Test Report");
        sparkReporter.config().setReportName("Selenium Test Results");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // System information
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Application", "Tournament App");

        return extent;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}