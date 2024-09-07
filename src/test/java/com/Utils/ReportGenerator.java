package com.Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class ReportGenerator {

    private static ExtentReports extent;

    static {
        // Initialize ExtentReports
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark/Spark.html");
        extent.attachReporter(spark);
    }

    public static void generateReport() {
        try {
            System.out.println("Generating report...");

            // Create test scenarios and add details
            ExtentTest screenCaptureTest = extent.createTest("ScreenCapture");
            screenCaptureTest.addScreenCaptureFromPath("extent.png");
            screenCaptureTest.pass(MediaEntityBuilder.createScreenCaptureFromPath("extent.png").build());

            ExtentTest logLevelsTest = extent.createTest("LogLevels");
            logLevelsTest.info("info")
                    .pass("pass")
                    .warning("warn")
                    .skip("skip")
                    .fail("fail");

            ExtentTest codeBlockTest = extent.createTest("CodeBlock");
            codeBlockTest.generateLog(Status.PASS, MarkupHelper.createCodeBlock("{\n    \"theme\": \"standard\",\n    \"encoding\": \"utf-8\n}", "{\n    \"protocol\": \"HTTPS\",\n    \"timelineEnabled\": false\n}"));

            ExtentTest parentWithChildTest = extent.createTest("ParentWithChild");
            parentWithChildTest.createNode("Child")
                    .pass("This test is created as a toggle as part of a child test of 'ParentWithChild'");

            ExtentTest tagsTest = extent.createTest("Tags");
            tagsTest.assignCategory("MyTag")
                    .pass("The test 'Tags' was assigned by the tag <span class='badge badge-primary'>MyTag</span>");

            ExtentTest authorsTest = extent.createTest("Authors");
            authorsTest.assignAuthor("TheAuthor")
                    .pass("This test 'Authors' was assigned by a special kind of author tag.");

            ExtentTest devicesTest = extent.createTest("Devices");
            devicesTest.assignDevice("TheDevice")
                    .pass("This test 'Devices' was assigned by a special kind of devices tag.");

            ExtentTest exceptionTest = extent.createTest("Exception! <i class='fa fa-frown-o'></i>");
            exceptionTest.fail(new RuntimeException("A runtime exception occurred!"));

            // Flush the report to write it to file
            extent.flush();
            System.out.println("Report generated.");
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace to identify issues
        }
    }
}
