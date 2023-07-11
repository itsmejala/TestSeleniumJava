package com.jalavathi.utils.generics;

import com.jalavathi.drivers.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenericLibrary {

    WebDriver driver;
    public GenericLibrary(WebDriver driver){
        driver = this.driver;
    }
    public  String getScreenshotAndLocation(WebDriver driver) throws IOException {

    File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    String destination = System.getProperty("user.dir")+"\\Screenshots\\"+""+new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+".png";
    File destFile = new File(destination);
    FileHandler.copy(srcFile,destFile);
    return destination;
    }
}
