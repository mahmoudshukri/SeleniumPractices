package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends PageBase {
    private final By registerButton = By.linkText("Register");
    private final By loginLink = By.className("ico-login");
    private final By contactUsLink = By.cssSelector("a[href='/contactus']");
    private final By currencyDDL = By.id("customerCurrency");
    private final By computerMenuLink = By.xpath("//a[@href='/computers']");
    private final By noteBooksMenuLink = By.xpath("//a[@href='/notebooks']");


    public HomePage(WebDriver driver) {
        super(driver);
        jse = (JavascriptExecutor) driver;
        action = new Actions(driver);

    }

    public void openRegistrationPage() {
        click(registerButton);
    }

    public void openLoginPage() {
        waitForElementVisibility(loginLink);
        click(loginLink);

    }

    public void OpenContactUsPage() {
        scrollToBottom();
        //waitForElementVisibility(contactUsLink);
        click(contactUsLink);

    }

    public void changeCurrency() {
        select = new Select(find(currencyDDL));
        select.selectByVisibleText("Euro");
    }

    public void computerMenu() {
        action = new Actions(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(computerMenuLink)).click();
        action.moveToElement(find(computerMenuLink)).build().perform();}
    public void noteBooksMenu(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(noteBooksMenuLink));
        action.moveToElement(find(noteBooksMenuLink)).build().perform();

    }
}