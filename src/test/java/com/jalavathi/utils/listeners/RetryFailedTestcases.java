package com.jalavathi.utils.listeners;

import com.aventstack.extentreports.Status;
import com.jalavathi.drivers.DriverFactory;
import com.jalavathi.tests.TestExample;
import com.jalavathi.utils.generics.GenericLibrary;
import com.jalavathi.utils.logs.Logs;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.IOException;

import static com.jalavathi.utils.extentReports.ExtentTestManager.getTest;

public class RetryFailedTestcases implements IRetryAnalyzer {

    private int count=0;
    private static int maxRetry = 2;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {

            if (count < maxRetry) {
                count++;
                iTestResult.setStatus(ITestResult.FAILURE);
                try {
                    extendReportsFailOperations(iTestResult);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return true;
            } else {
                iTestResult.setStatus(ITestResult.SUCCESS);//If test passes, TestNG marks it as passed
            }
        }
        return false;


    }
    public void extendReportsFailOperations(ITestResult iTestResult) throws IOException {
        ITestContext context = iTestResult.getTestContext();
        //Object testClass = iTestResult.getInstance();
        WebDriver driver= (WebDriver) context.getAttribute("driver");
        Logs.info("driver value: "+driver);

      /*  String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.FAIL, "Test Failed",
                getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
     */
        GenericLibrary g = new GenericLibrary(driver);
        String path =  g.getScreenshotAndLocation(driver);
        getTest().log(Status.FAIL, "Test Failed",
                getTest().addScreenCaptureFromPath(path).getModel().getMedia().get(0));
    }
}
