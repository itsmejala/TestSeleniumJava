package com.jalavathi.utils.listeners;

import com.jalavathi.objectRepo.BasePage;
import com.jalavathi.utils.extentReports.ExtentManager;
import com.jalavathi.utils.generics.GenericLibrary;
import com.jalavathi.utils.logs.Logs;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.Status;

import java.io.IOException;

import static com.jalavathi.utils.extentReports.ExtentTestManager.getTest;

public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {

        Logs.debug("Test started");

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        getTest().log(Status.PASS, "Test passed");
        Logs.info("Test ran successfully");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        getTest().log(Status.FAIL, "Test failed");

        String path = null;
        try {
            ITestContext context = iTestResult.getTestContext();
            WebDriver driver = (WebDriver)context.getAttribute("driver");

            path = BasePage.getScreenshotAndLocation(driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getTest().log(Status.FAIL, "Test Failed",
                getTest().addScreenCaptureFromPath(path).getModel().getMedia().get(0));
        Logs.info("Test failed ");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Logs.info("Test started successfully");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Logs.info("Test finished successfully");
        ExtentManager.extentReports.flush();
    }
}
