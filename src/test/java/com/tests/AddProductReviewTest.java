package com.tests;

import com.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductReviewTest extends TestBase{
    /*
     * 1.User registration.
     * 2.User Login.
     * 3.User Search.
     * 4.User Add review.
     * 5.User log out.
     */

    HomePage homePageObj;
    UserRegistrationPage userRegistrationPageObj;
    LoginPage loginObj;
    SearchPage searchPageObj;
    ProductDetailsPage detailsObj;
    ProductReviewPage reviewObj;
    String email = "mahmoudshukri3551@gmail.com";

    String password = "test@123456";
    String searchTxt = "mac";


    @Test(priority = 1, alwaysRun = true)
    public void userCanRegisterSuccessfully() {
        homePageObj = new HomePage(driver);
        homePageObj.openRegistrationPage();
        userRegistrationPageObj = new UserRegistrationPage(driver);
        userRegistrationPageObj.userRegistration
                (
                        "Mahmoud",
                        "shukri",
                        email,
                        password
                );
        String SuccessfulMessage = userRegistrationPageObj.getSuccessfulMessage();
        System.out.println(SuccessfulMessage);
        Assert.assertTrue(userRegistrationPageObj
                .getSuccessfulMessage()
                .contains
                        ("Your registration completed"));
    }
    // 2.User log in

    @Test(priority = 2, dependsOnMethods = "userCanRegisterSuccessfully")
    public void RegisteredUserCanLogIn() {
        homePageObj.openLoginPage();
        loginObj = new LoginPage(driver);
        loginObj.userLogin(email, password);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL is : \n" + currentUrl);
        Assert.assertTrue(loginObj.checkLogOutIsDisplayed(), "LogOut Button is displayed!");
    }

    // 3. Search for product.
    @Test(priority = 3, dependsOnMethods = "RegisteredUserCanLogIn")
    public void UserCanSearchWithAutoSuggest() {
        try {
            searchPageObj = new SearchPage(driver);
            searchPageObj.productSearchUsingAutoSuggest(searchTxt);
            detailsObj = new ProductDetailsPage(driver);
            Assert.assertTrue(detailsObj.getProductNameBreadCrumbVisibility());
            Assert.assertEquals(detailsObj.getProductNameBreadCrumbText(),"Apple MacBook Pro 13-inch");
            System.out.println("ProductNameBreadCrumbText is : \n" + detailsObj.getProductNameBreadCrumbText());

        } catch (Exception e) {
            System.out.println("Error Occurred" + e.getMessage());
        }
    }

    // 4.User Add review.
    @Test(priority = 4, dependsOnMethods = "UserCanSearchWithAutoSuggest")

public void RegisteredUserCanReviewProduct(){
        detailsObj = new ProductDetailsPage(driver);
        detailsObj.openAddReviewPage();
        reviewObj=new ProductReviewPage(driver);
        reviewObj.AddProductReview("New Review Title","This product is very good.");
        Assert.assertTrue(reviewObj.getReviewNotificationAfterSubmit().contains("Product review is successfully added."));
    }


    //5.User LogOut
    @Test(priority = 5, dependsOnMethods = "RegisteredUserCanReviewProduct")
    public void RegisteredUserCanLogOut() {
        loginObj.userLogOut();
    }
}

