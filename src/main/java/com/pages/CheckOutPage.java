package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckOutPage extends PageBase {
    private final By fNameTxt = By.cssSelector("#BillingNewAddress_FirstName");
    private final By lNameTxt = By.id("BillingNewAddress_LastName");
    private final By emailTxt = By.id("BillingNewAddress_Email");

    private final By countryList = By.id("BillingNewAddress_CountryId");
    private final By cityTxt = By.id("BillingNewAddress_City");
    private final By address1Txt = By.id("BillingNewAddress_Address1");
    private final By zipCodeTxt = By.id("BillingNewAddress_ZipPostalCode");
    private final By phoneNoTxt = By.id("BillingNewAddress_PhoneNumber");
    private final By continueBtn = By.xpath("//button[@onclick='if (!window.__cfRLUnblockHandlers) return false; Billing.save()']");
    private final By shippingMethodRdo = By.id("shippingoption_0");
    private final By continueShippingBtn = By.xpath("//button[@class='button-1 shipping-method-next-step-button']");
    private final By continuePaymentBtn = By.xpath("//button[@class='button-1 payment-method-next-step-button']");
    private final By continueInfoBtn = By.xpath("//button[@class='button-1 payment-info-next-step-button']");
    private final By productName = By.xpath("//a[@class='product-name']");
    private final By confirmBtn = By.xpath("//button[normalize-space()='Confirm']");
    private final By thankYouLbl = By.cssSelector("div[class='page-title'] h1");
    private final By successMessage = By.cssSelector("div[class='section order-completed'] div[class='title'] strong");
    private final By orderDetailsLink = By.linkText("Click here for order details.");

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public void RegisteredUserCanCheckOutProduct(String countryName, String address, String city,
                                                 String postcode, String phone, String productNAme) throws InterruptedException {
        select = new Select(find(countryList));
        select.selectByVisibleText(countryName);
        type(city, cityTxt);
        scrollToBottom();
        type(address, address1Txt);
        type(postcode, zipCodeTxt);
        type(phone, phoneNoTxt);
        click(continueBtn);
        wait.until(ExpectedConditions.elementToBeClickable(shippingMethodRdo)).click();
        //click(shippingMethodRdo);
        click(continueShippingBtn);
        Thread.sleep(1000);
        click(continuePaymentBtn);
        Thread.sleep(1000);
        click(continueInfoBtn);
    }

    public void confirmOrder() {
        waitForElementVisibility(confirmBtn);
        wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();

    }
    public void CheckOutProduct(String firstName,
                                String lastName,
                                String countryName,
                                String email,
                                String address,
                                String postCode,
                                String phone,
                                String city,
                                String productName)throws InterruptedException{
        type(firstName,fNameTxt);
        type(lastName,lNameTxt);
        scrollToBottom();
        type(email,emailTxt);
        select=new Select(find(countryList));
        select.selectByVisibleText(countryName);
        type(city,cityTxt);
        type(address,address1Txt);
        type(postCode,zipCodeTxt);
        type(phone,phoneNoTxt);
        click(continueBtn);
        wait.until(ExpectedConditions.elementToBeClickable(shippingMethodRdo)).click();
        //click(shippingMethodRdo);
        click(continueShippingBtn);
        Thread.sleep(1000);
        click(continuePaymentBtn);
        Thread.sleep(1000);
        click(continueInfoBtn);
    }

    public void printOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(orderDetailsLink)).click();
    }

    public String getProductName() {
        waitForElementVisibility(productName);
        return find(productName).getText();
    }

    public Boolean checkProductNameVisibility() {
        waitForElementVisibility(productName);
        return find(productName).isDisplayed();
    }

    public Boolean checkThankYouLblVisibility() {
        return find(thankYouLbl).isDisplayed();
    }
}
