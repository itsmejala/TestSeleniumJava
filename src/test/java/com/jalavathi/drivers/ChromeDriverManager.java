package com.jalavathi.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager extends Factory{
    @Override
   public WebDriver getDriver() {
         WebDriverManager.chromedriver().setup();
         return new ChromeDriver();
    }
}
