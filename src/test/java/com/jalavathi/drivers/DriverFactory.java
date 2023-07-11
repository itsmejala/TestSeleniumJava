package com.jalavathi.drivers;

import com.jalavathi.drivers.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class DriverFactory  {
    WebDriver driver ;
    public   WebDriver getDriverInstance(String name) {
        DriverType driverType = DriverType.valueOf(name.trim().toUpperCase());


        switch(driverType){

            case CHROME:
                    if(driver == null)
                         driver = new ChromeDriverManager().getDriver();

                break;


            case FIREFOX:
                if(driver == null)
                    driver = new FirefoxDriverManager().getDriver();
                break;

            case SAFARI:
                if(driver == null)
                    driver = new SafariDriverManager().getDriver();
               break;

            default:
                if(driver == null)
                 driver = new EdgeDriverManager().getDriver();


        }
        driver.manage().timeouts().implicitlyWait( Duration.ofSeconds(30));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();


        return driver;
    }

    public WebDriver getDriver(){
        return driver;
    }
    public void closeDriver(){
        if(driver != null)
            driver.close();
    }


}
