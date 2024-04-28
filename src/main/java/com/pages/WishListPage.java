package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishListPage extends PageBase{
    public WishListPage(WebDriver driver) {
        super(driver);
    }
   // private final By productCell= By.cssSelector("a.product-name[href='/apple-macbook-pro-13-inch']");
    private final By wishListHeader = By.cssSelector("div[class='page-title'] h1");
   // private final By updateWishListBtn= By.id("updatecart");
    private final By removeFromCartBtn=By.xpath("//button[@class='remove-btn']");
    private final By emptyCartLbl=By.cssSelector(".no-data");

    public void removeProductFromCart(){
       // click(updateWishListBtn);
        waitForElementVisibility(removeFromCartBtn);
        click(removeFromCartBtn);
        waitForElementVisibility(emptyCartLbl);


    }

//    public String getProductNameInCell(){
//        return find(productCell).getText();
//    }
    public String getWishListHeader(){
        return find(wishListHeader).getText();
    }
    public String getEmptyCartMessage(){
        return find(emptyCartLbl).getText();
    }







}
