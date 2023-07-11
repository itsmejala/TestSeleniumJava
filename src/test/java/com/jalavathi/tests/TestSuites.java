package com.jalavathi.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class TestSuites {

    public static void main(String[] args) {

      /*  ChromeDriver ch = new ChromeDriver();
        ch.manage().window().maximize();
        ch.close();*/

       // FirefoxDriver fr = new FirefoxDriver();
        /*EdgeDriver fr = new EdgeDriver();
        fr.manage().window().maximize();
        fr.get("http://www.google.com/");
        fr.close();*/

        WebDriver driver;
        String browserName="chrome";
        if(browserName.equalsIgnoreCase("chrome"))
            driver = new ChromeDriver();
        else if(browserName.equalsIgnoreCase("firefox"))
            driver = new FirefoxDriver();
        else if(browserName.equalsIgnoreCase("edge"))
            driver = new EdgeDriver();
        else driver = new SafariDriver();

        driver.manage().window().maximize();
        driver.get("http://www.google.com");
        driver.close();
    }
}
