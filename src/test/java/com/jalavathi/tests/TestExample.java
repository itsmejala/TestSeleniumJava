package com.jalavathi.tests;
import com.jalavathi.drivers.DriverFactory;
import com.jalavathi.objectRepo.HomePage;
import com.jalavathi.objectRepo.SwagLabsPage;
import com.jalavathi.utils.GlobalConstants;
import com.jalavathi.utils.generics.CustomException;
import com.jalavathi.utils.generics.ReadProperties;
import com.jalavathi.utils.generics.ReadTestData;
import com.jalavathi.utils.logs.Logs;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;

import static com.jalavathi.utils.extentReports.ExtentTestManager.startTest;

public class TestExample {
    WebDriver driver;
    SwagLabsPage swagLabsPage;
    HomePage homePage;
    HashMap<String,String> testData;
    HashMap<String,String> propertyList;
    @BeforeSuite
    void getAllProperties() throws IOException {
        propertyList = new ReadProperties().getProperties();

    }


    @BeforeTest
    public void getTestData() throws IOException {
        testData = ReadTestData.getSheetData(GlobalConstants.TEST_DATA_FILE_NAME,GlobalConstants.Login_SHEET_NAME);
    }

    @BeforeMethod
    void getDriverInstance(ITestContext context){

        driver = new DriverFactory().getDriverInstance(propertyList.get("browser"));
        context.setAttribute("driver",driver);
        this.driver.get(propertyList.get("url"));

    }



    @AfterMethod
    void tearDown(){
       driver.close();
    }

    @Test(priority = 0)
    void testLogin(Method method) throws CustomException {
        Logs.info(method.getName()+" started execution");
        startTest(method.getName(),"Tets Login functionality");
        boolean isHomePage = SwagLabsPage.using(driver).
                loginToApplication(testData.get("UserName"),testData.get("Password")).
                isHomePageDisplayed();
        Assert.assertTrue(isHomePage,"Login is not successful");

    }

    @Test(enabled=false)
    void test1(Method method){
        Logs.info(method.getName()+" execution started");
        startTest(method.getName(),"Test1 to check execution");
        this.driver.get("https://mvnrepository.com/");
        Logs.info(this.driver.getTitle());
        Logs.info(method.getName()+" execution completed");

    }

    @Test(enabled = false)
    void test2(Method method){
        Logs.info(method.getName()+" execution started");
        startTest(method.getName(),"Test 2 to check execution");
        this.driver.get("https://www.google.com/");
        Logs.info(this.driver.getTitle());
        Logs.info(method.getName()+" execution completed");

    }


    @Test(enabled=false)
    void test3(Method method){
        Logs.info(method.getName()+" execution started");
        startTest(method.getName(),"Test 2 to check failure of execution");
        this.driver.get("https://www.facebook.com/");
        Assert.assertEquals(this.driver.getTitle(),"facebook","Wrong page is opened");
    }
}
