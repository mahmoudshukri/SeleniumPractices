package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class ProductDetailsPage extends PageBase {
    private final By productNameBreadCrumb = By.cssSelector(".current-item");
    private final By emailFriendButton = By.cssSelector(".button-2.email-a-friend-button");
    private final By productPriceLabel = By.cssSelector("#price-value-4");
    private final By addReviewLink=By.linkText("Add your review");
    private final By addToWishListBtn=By.id("add-to-wishlist-button-4");
    private final By wishListNotificationLink = By.xpath("//a[normalize-space()='wishlist']");
    private final By addToCompareList = By.cssSelector("div[class='compare-products'] button[type='button']");
    private final By addToCartBtn=By.id("add-to-cart-button-4");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductNameBreadCrumbText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productNameBreadCrumb)).getText();
        waitForElementVisibility(productNameBreadCrumb);
        return find(productNameBreadCrumb).getText();

    }

    public boolean getProductNameBreadCrumbVisibility() {
        waitForElementVisibility(productNameBreadCrumb);
        return find(productNameBreadCrumb).isDisplayed();
    }

    public void openSendEmail() {
        waitForElementVisibility(emailFriendButton);
        click(emailFriendButton);
    }

    public String getProductPriceLabel() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(productPriceLabel)).getText();
        return find(productPriceLabel).getText();
    }
    public void openAddReviewPage(){
        waitForElementVisibility(addReviewLink);
        click(addReviewLink);
    }
    public void addProductToWishList(){
        scrollToBottom();
        waitForElementVisibility(addToWishListBtn);
        click(addToWishListBtn);
        waitForElementVisibility(wishListNotificationLink);
        wait.until(ExpectedConditions.elementToBeClickable(wishListNotificationLink)).click();


    }
    public void addProductToCompare(){
        waitForElementVisibility(addToCompareList);
        click(addToCompareList);
    }
    public void AddToCart(){
        waitForElementVisibility(addToCartBtn);
        click(addToCartBtn);
    }

}
