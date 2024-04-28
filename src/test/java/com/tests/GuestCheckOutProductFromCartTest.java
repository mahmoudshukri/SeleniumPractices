package com.tests;

import com.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GuestCheckOutProductFromCartTest extends TestBase {
    String productName = "Apple MacBook Pro 13-inch";
    String email = "mahmoudshukri353@gmail.com";
    SearchPage searchObj;
    ProductDetailsPage detailsPageObj;
    ShoppingCartPage cartPageObj;
    CheckOutPage checkOutObj;
    OrderDetailsPage orderDetailsPageObj;

    @Test(priority = 1)
    public void UserCanSearchForProductWithAutoSuggest() {
        searchObj = new SearchPage(driver);
        searchObj.productSearchUsingAutoSuggest("mac");
        detailsPageObj = new ProductDetailsPage(driver);
        Assert.assertTrue(detailsPageObj.getProductNameBreadCrumbVisibility());
        Assert.assertTrue(detailsPageObj.getProductNameBreadCrumbText().contains(productName));
    }

    @Test(priority = 2, dependsOnMethods = "UserCanSearchForProductWithAutoSuggest")
    public void UserCanAddProductToShoppingCart() throws InterruptedException {
        detailsPageObj = new ProductDetailsPage(driver);
        detailsPageObj.AddToCart();
        Thread.sleep(2000);
        driver.navigate().to("https://demo.nopcommerce.com" + "/cart");
        cartPageObj = new ShoppingCartPage(driver);
        Assert.assertTrue(cartPageObj.getTotalLbl().equals("$3,600.00"));
    }

    @Test(priority = 3)
    public void UserCanCheckOutProduct() throws InterruptedException {
        cartPageObj = new ShoppingCartPage(driver);
        cartPageObj.openCheckOutPage();
        Thread.sleep(1000);
        cartPageObj.openCheckOutPageAsGuest();
        Thread.sleep(1000);
        checkOutObj = new CheckOutPage(driver);
        Thread.sleep(2000);
        checkOutObj.CheckOutProduct(
                "test",
                "user",
                "Egypt",
                email,
                "testAddress",
                "123434",
                "0104324234",
                "test",
                productName
        );
        Thread.sleep(1000);
        Assert.assertTrue(checkOutObj.checkProductNameVisibility());
        Assert.assertTrue(checkOutObj.getProductName().contains(productName));
        checkOutObj.confirmOrder();
        Assert.assertTrue(checkOutObj.checkThankYouLblVisibility());
    }

    @Test(priority = 4)
    public void UserCanViewOrderDetails() throws InterruptedException {
        orderDetailsPageObj = new OrderDetailsPage(driver);
        checkOutObj.printOrder();
        Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
        orderDetailsPageObj = new OrderDetailsPage(driver);
        orderDetailsPageObj.DownLoadPdfInvoice();
        orderDetailsPageObj.PrintOrderDetails();
    }
}
