package com.jalavathi.objectRepo;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.jalavathi.utils.generics.CustomException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

public class SwagLabsPage extends BasePage {
    public SwagLabsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "user-name")
    private WebElement textUserName;
    @FindBy(id = "password")
    private WebElement textPassword;
    @FindBy(id="login-button")
    private WebElement btnLogin;

    @FindBys(
            @FindBy( className = "error-button"))
    private List<WebElement> errorMessageList;
    public HomePage loginToApplication(String userName,String password) throws CustomException {
       enterText(textUserName,userName);
       enterText(textPassword,password);
       clickElement(btnLogin);
        return new HomePage(driver);
    }

    public boolean isErrorMessageDisplayed(){
        boolean flag = errorMessageList.size()==0 ?  false :  true;
            return flag;
    }

    public static SwagLabsPage using(WebDriver driver){
        return new SwagLabsPage(driver);
    }
}
