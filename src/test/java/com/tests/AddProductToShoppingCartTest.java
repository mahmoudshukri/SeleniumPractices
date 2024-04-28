package com.tests;

import com.pages.ProductDetailsPage;
import com.pages.SearchPage;
import com.pages.ShoppingCartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductToShoppingCartTest extends TestBase{
    SearchPage searchPageObj;
    ProductDetailsPage productDetailsPageObj;
    ShoppingCartPage shoppingCartPageObj;
    String productName="Apple MacBook Pro 13-inch";
    String productQuantity="4";

    @Test(priority = 1)
    public void UserCanSearchForProductWithAutoSuggest(){
        searchPageObj=new SearchPage(driver);
        searchPageObj.productSearchUsingAutoSuggest("mac");
        productDetailsPageObj=new ProductDetailsPage(driver);
        Assert.assertTrue(productDetailsPageObj.getProductNameBreadCrumbVisibility());
        Assert.assertTrue(productDetailsPageObj.getProductNameBreadCrumbText().contains(productName));
    }


    @Test(priority = 2)
    public void UserCanAddProductToShoppingCart() throws InterruptedException {
        productDetailsPageObj=new ProductDetailsPage(driver);
        productDetailsPageObj.AddToCart();
        Thread.sleep(2000);
        driver.navigate().to("https://demo.nopcommerce.com"+"/cart");
        shoppingCartPageObj=new ShoppingCartPage(driver);
        Assert.assertTrue(shoppingCartPageObj.getTotalLbl().equals("$3,600.00"));
    }
    @Test(priority = 3)
    public void UserCanUpdateProductQuantity() throws InterruptedException {
        shoppingCartPageObj=new ShoppingCartPage(driver);
        shoppingCartPageObj.UpdateProductQuantityInCart(productQuantity);
        Thread.sleep(3000);
        System.out.println(shoppingCartPageObj.getProductQuantity());

    }
    @Test(priority = 4)
    public void UserCanRemoveProductFromCart(){
        shoppingCartPageObj=new ShoppingCartPage(driver);
        shoppingCartPageObj.RemoveProductFromCart();
        Assert.assertTrue(shoppingCartPageObj.getCartEmptyMessage().equals("Your Shopping Cart is empty!"));
    }

}
