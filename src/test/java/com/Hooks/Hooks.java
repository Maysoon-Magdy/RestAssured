//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.Hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.observer.ExtentObserver;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class hooks implements ITestListener {
    private static ExtentReports extent;
    private static ExtentTest test;

    public hooks() {
    }

    public void onStart(ITestContext context) {
        System.out.println("Test Suite Started.");
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark/Spark.html");
        extent.attachReporter(new ExtentObserver[]{spark});
    }

    public void onFinish(ITestContext context) {
        System.out.println("Test Suite Finished.");
        if (extent != null) {
            extent.flush();
        }

    }

    public void onTestStart(ITestResult result) {
        System.out.println("Test Started: " + result.getMethod().getMethodName());
        test = extent.createTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("Test Passed: " + result.getMethod().getMethodName());
        test.pass("Test passed");
    }

    public void onTestFailure(ITestResult result) {
        System.out.println("Test Failed: " + result.getMethod().getMethodName());
        test.fail(result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Skipped: " + result.getMethod().getMethodName());
        test.skip("Test skipped");
    }
}
