package com.jalavathi.objectRepo;

import org.checkerframework.checker.index.qual.EnsuresLTLengthOfIf;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class HomePage extends BasePage {

    @FindBys(
        @FindBy(xpath="//d[text()='Dashboard']")
    )
    private List<WebElement> textDashboard;
    HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isHomePageDisplayed(){
        return textDashboard.size()>0 ? true : false;
    }

}
