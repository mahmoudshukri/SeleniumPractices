package com.tests;

import com.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisteredUserCheckOutProductTest extends TestBase {
    /**
     * 1.Register user.
     * 2.User login
     * 3.Search for product.
     * 4.Add to Cart.
     * 5.CheckOut
     * 6.LogOut
     **/
    String productName = "Apple MacBook Pro 13-inch";
    String email = "mahmoudshukri351@gmail.com";
    String password = "test@123456";
    HomePage homeObj;
    UserRegistrationPage userRegistrationObj;
    LoginPage loginObj;

    SearchPage searchObj;
    ProductDetailsPage detailsPageObj;
    ShoppingCartPage cartPageObj;
    CheckOutPage checkOutObj;
    OrderDetailsPage orderDetailsPageObj;


    @Test(priority =1, alwaysRun = true)
    public void userCanRegisterSuccessfully() {
        homeObj = new HomePage(driver);
        homeObj.openRegistrationPage();
        userRegistrationObj = new UserRegistrationPage(driver);
        userRegistrationObj.userRegistration
                (
                        "Mahmoud",
                        "shukri",
                        email,
                        password
                );
        String SuccessfulMessage = userRegistrationObj.getSuccessfulMessage();
        System.out.println(SuccessfulMessage);
        Assert.assertTrue(userRegistrationObj
                .getSuccessfulMessage()
                .contains
                        ("Your registration completed"));
    }
    @Test(priority =2)
    public void RegisteredUserCanLogIn(){
        homeObj.openLoginPage();
        loginObj = new LoginPage(driver);
        loginObj.userLogin(email, password);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL is : \n"+currentUrl);
        Assert.assertTrue(loginObj.checkLogOutIsDisplayed(),"LogOut Button is displayed!");
    }


    @Test(priority = 3)
    public void UserCanSearchForProductWithAutoSuggest() {
        searchObj = new SearchPage(driver);
        searchObj.productSearchUsingAutoSuggest("mac");
        detailsPageObj = new ProductDetailsPage(driver);
        Assert.assertTrue(detailsPageObj.getProductNameBreadCrumbVisibility());
        Assert.assertTrue(detailsPageObj.getProductNameBreadCrumbText().contains(productName));
    }

    @Test(priority = 4,dependsOnMethods = "UserCanSearchForProductWithAutoSuggest")
    public void UserCanAddProductToShoppingCart() throws InterruptedException {
        detailsPageObj = new ProductDetailsPage(driver);
        detailsPageObj.AddToCart();
        Thread.sleep(2000);
        driver.navigate().to("https://demo.nopcommerce.com" + "/cart");
        cartPageObj = new ShoppingCartPage(driver);
        Assert.assertTrue(cartPageObj.getTotalLbl().equals("$3,600.00"));
    }

    @Test(priority = 5,dependsOnMethods = "UserCanAddProductToShoppingCart")
    public void UserCanCheckOutProduct() throws InterruptedException {

        cartPageObj=new ShoppingCartPage(driver);
        cartPageObj.openCheckOutPage();
        Thread.sleep(2000);
        checkOutObj=new CheckOutPage(driver);
        Thread.sleep(2000);
        checkOutObj.RegisteredUserCanCheckOutProduct(
                "Egypt",
                "testAddress1",
                "Cairo",
                "1234567",
                "01078655676",productName);

        Assert.assertTrue(checkOutObj.getProductName().equals(productName));
        Assert.assertTrue(checkOutObj.checkProductNameVisibility());
        checkOutObj.confirmOrder();
        Assert.assertTrue(checkOutObj.checkThankYouLblVisibility());
        checkOutObj.printOrder();
        Assert.assertTrue(driver.getCurrentUrl().contains("orderdetails"));
        orderDetailsPageObj=new OrderDetailsPage(driver);
        orderDetailsPageObj.DownLoadPdfInvoice();
        orderDetailsPageObj.PrintOrderDetails();

    }

    //Print Invoice as PDF or Print page



    @Test(priority = 6)
    public void RegisteredUserCanLogOut(){
        loginObj.userLogOut();
    }





}
