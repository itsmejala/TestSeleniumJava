package com.jalavathi.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SafariDriverManager extends Factory {
    @Override
    public WebDriver getDriver() {
        WebDriverManager.safaridriver().setup();
        return new SafariDriver();
    }
}