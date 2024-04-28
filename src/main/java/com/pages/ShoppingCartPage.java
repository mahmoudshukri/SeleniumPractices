package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ShoppingCartPage extends PageBase{
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }
    private final By removeBtn = By.name("updatecart");
    private final By updateCartBtn= By.id("updatecart");
    private final By quantityTxt = By.xpath("//input[contains(@id,'itemquantity')]");
    private final By totalLbl= By.xpath("//span[@class='product-subtotal']");
    private final By emptyCartNotification=By.xpath("//div[@class='no-data']");
    private final By checkOutBtn = By.id("checkout");
    private final By agreeCheckBox = By.id("termsofservice");
    private final By guestBtn=By.cssSelector(".button-1.checkout-as-guest-button");



    public void RemoveProductFromCart(){
       click(removeBtn);


    }
    public void UpdateProductQuantityInCart(String quantity) throws InterruptedException{

        type(quantity,quantityTxt);
        click(updateCartBtn);
        waitForElementVisibility(quantityTxt);


    }
    public void openCheckOutPage(){

        click(agreeCheckBox);
        click(checkOutBtn);
    }
    public void openCheckOutPageAsGuest(){
        wait.until(ExpectedConditions.elementToBeClickable(guestBtn)).click();
    }
    public String getTotalLbl(){
        return find(totalLbl).getText();
    }
    public String getCartEmptyMessage(){
        return find(emptyCartNotification).getText();
    }
    public String getProductQuantity(){
        return find(quantityTxt).getText();
    }
}
