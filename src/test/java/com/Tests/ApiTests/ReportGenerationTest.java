package com.Tests.ApiTests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportGenerationTest {

    public static void main(String[] args) {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark/Spark.html");
        extent.attachReporter(spark);

        extent.createTest("Sample Test")
                .pass("This is a sample report entry.");

        extent.flush();
    }
}
