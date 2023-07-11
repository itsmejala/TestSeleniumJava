package com.jalavathi.objectRepo;

import com.jalavathi.utils.GlobalConstants;
import com.jalavathi.utils.generics.CustomException;
import com.jalavathi.utils.logs.Logs;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class BasePage {

    WebDriver driver ;

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    void clickElement(WebElement element){
        element.click();
    }

    public  static String getScreenshotAndLocation(WebDriver driver) throws IOException {

        File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir")+"\\Screenshots\\"+"scr"+new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+".png";
        File destFile = new File(destination);
        FileHandler.copy(srcFile,destFile);
        return destination;
    }

    /**
     * author: jalavathi
     * description : To get the javscript executor instance
     * @param driver
     * @return
     */
    public JavascriptExecutor getJs(WebDriver driver){
        return (JavascriptExecutor)driver;
    }

    /**
     * author: to scroll to the top of the page
     */
    public void scrollToTop(){

          getJs(driver).executeScript("window.scrollTo(0,0)","");
    }

    /**
     * author: to scroll to the bottom of the page
     */
    public void scrollToBotton(){
        getJs(driver).executeScript("window.scrollTo(0,document.body.scrollHeight)","");
    }

    /**
     * Description: to get the WebDriverWait Object
     * @return
     */
    public Wait getExplicitWait(){
      //  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.WAIT_TIME));
       Wait fluentWait = new FluentWait(driver).withTimeout(Duration.ofSeconds(GlobalConstants.WAIT_TIME)).pollingEvery(Duration.ofSeconds(5));
        return fluentWait;
    }

    /**
     * Description: Wait for 30 seconds and check whether object is available if not try for 2 more times.
     * @param element
     * @throws CustomException
     */
    public void waitThenClick(WebElement element) throws CustomException {
        boolean isClicked = false;
        int attemptedCount=0;
        while(!isClicked && (attemptedCount++<4)) {
            try {
                getExplicitWait().until(ExpectedConditions.elementToBeClickable(element));
                isClicked = true;
                break;
            }
            catch (Exception e){
                Logs.debug("Waiting for element "+element+" to be clickable for the "+attemptedCount+" time");
            }
        }
        if(!isClicked){
            throw new CustomException("Element not clickable");
        }

    }

    public void enterText(WebElement element,String text) throws CustomException {
        try {
            getExplicitWait().until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(text);
        }
        catch(Exception e){
            Logs.debug("Element "+element+" is invisible");
            throw new CustomException("element invisible");
        }

    }

    public Select getDropdown(WebElement dropdown){
        return new Select(dropdown);
    }

    public void selectByValue(WebElement dropdown,String value) throws CustomException {
        try {
            getDropdown(dropdown).selectByValue(value);
        }
        catch(Exception e){
            Logs.debug("Failed to select  "+value+" from"+dropdown+" dropdown");
            throw new CustomException("Value selection from dropdown failed");
        }
    }

    public void selectByVisibleText(WebElement dropdown,String value) throws CustomException {
        try {
             List<WebElement> getOptions = getDropdown(dropdown).getOptions();
             for(WebElement element:getOptions){
                 if(element.getText().trim().equalsIgnoreCase(value.trim())){
                     element.click();
                     break;
                 }
             }
        }
        catch(Exception e){
            Logs.debug("Failed to select  "+value+" from"+dropdown+" dropdown");
            throw new CustomException("Value selection from dropdown failed");
        }
    }



}
