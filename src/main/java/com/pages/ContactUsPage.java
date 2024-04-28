package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends PageBase {
    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    private final By fullNameTxt = By.id("FullName");
    private final By emailTxt = By.id("Email");
    private final By enquiryTxt = By.id("Enquiry");
    private final By submitBtn = By.name("send-email");
    private final By successMessage = By.cssSelector(".result");

    public void ContactUs(String fullName, String email, String message) {
        type(fullName, fullNameTxt);
        type(email, emailTxt);
        type(message, enquiryTxt);
        click(submitBtn);
    }

    public String getSuccessMessage() {
        return find(successMessage).getText();
    }
    public Boolean checkSuccessMessageVisibility(){
        return find(successMessage).isDisplayed();

    }
}