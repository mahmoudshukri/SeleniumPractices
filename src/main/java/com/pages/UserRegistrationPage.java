package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserRegistrationPage extends PageBase {
    private final By genderRadioBtn = By.id("gender-male");
    private final By firstNameTxtBox = By.id("FirstName");
    private final By lastNameTxtBox = By.id("LastName");
    private final By emailTxtBox = By.id("Email");
    private final By passWordTxtBox = By.id("Password");
    private final By confirmPasswordTxtBox = By.id("ConfirmPassword");
    private final By registerBtn = By.id("register-button");
    private final By continueBtn=By.xpath("//a[normalize-space()='Continue']");
    private final By successMessage = By.xpath("//div[@class='result']");
    private final By myAccountLink = By.cssSelector(".ico-account");
    public UserRegistrationPage(WebDriver driver) {
        super(driver);

    }

    public void userRegistration(String firstName, String lastName, String email, String password) {

        click(genderRadioBtn);
        type(firstName, firstNameTxtBox);
        type(lastName, lastNameTxtBox);
        type(email, emailTxtBox);
        type(password, passWordTxtBox);
        type(password, confirmPasswordTxtBox);
        click(registerBtn);
        //click(continueBtn);

    }

    public String getSuccessfulMessage() {
        return find(successMessage).getText();
    }

    public void openMyAccountPage(){
        //waitForElementVisibility(myAccountLink);
        click(myAccountLink);
    }


}
