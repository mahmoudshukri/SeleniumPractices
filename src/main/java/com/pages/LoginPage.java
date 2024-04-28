package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends PageBase {
    private final By emailTxtBox = By.id("Email");
    private final By passwordTxtBox = By.id("Password");
    private final By loginButton = By.cssSelector("button[class='button-1 login-button']");
    private final By logOutLink = By.cssSelector(".ico-logout");

    //Create constructor to initialize WebDriver and elements
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void userLogin(String email, String password) {
        type(email, emailTxtBox);
        type(password, passwordTxtBox);
        waitForElementVisibility(loginButton);
        click(loginButton);

    }
    public void userLogOut() {
        waitForElementVisibility(logOutLink);
        wait.until(ExpectedConditions.elementToBeClickable(logOutLink)).click();

    }
   public boolean checkLogOutIsDisplayed(){
        waitForElementVisibility(logOutLink);
        return find(logOutLink).isDisplayed();
   }


}

