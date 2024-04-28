package com.tests;

import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.MyAccountPage;
import com.pages.UserRegistrationPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyAccountTests extends TestBase {
    HomePage homePageObj;
    UserRegistrationPage userRegistrationPageObj;
    LoginPage loginObj;
    MyAccountPage myAccountPageObj;
    String oldPassword = "test@123456";
    String newPassword = "test@1234567";
    String email = "mahmoudshukri352@gmail.com";

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
                        oldPassword
                );
        String SuccessfulMessage = userRegistrationPageObj.getSuccessfulMessage();
        System.out.println(SuccessfulMessage);
        Assert.assertTrue(userRegistrationPageObj
                .getSuccessfulMessage()
                .contains
                        ("Your registration completed"));
    }

    @Test(dependsOnMethods = "userCanRegisterSuccessfully")
    public void RegisteredUserCanLogIn() {
        homePageObj.openLoginPage();
        loginObj = new LoginPage(driver);
        loginObj.userLogin(email, oldPassword);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL is : \n" + currentUrl);
        Assert.assertTrue(loginObj.checkLogOutIsDisplayed(), "LogOut Button is displayed!");
    }

    @Test(dependsOnMethods = "RegisteredUserCanLogIn")
    public void RegisteredUserCanChangePassword() {
        myAccountPageObj = new MyAccountPage(driver);
        userRegistrationPageObj.openMyAccountPage();
        myAccountPageObj.openChangePasswordPage();
        myAccountPageObj.changePassword(oldPassword, newPassword);
        myAccountPageObj.closeContentMessage();
        Assert.assertTrue(myAccountPageObj.successContent(),
                "Message is Displayed as following: \n Your Password Changed Successfully!");
    }

    @Test(dependsOnMethods = "RegisteredUserCanChangePassword")
    public void RegisteredUserCanLogOut() throws InterruptedException {
        loginObj=new LoginPage(driver);
        Thread.sleep(1000);
        loginObj.userLogOut();
    }

    @Test(dependsOnMethods = "RegisteredUserCanLogOut")
    public void RegisteredUserCanLogInAgainWithNewPassword() {
        homePageObj.openLoginPage();
        loginObj = new LoginPage(driver);
        loginObj.userLogin(email, newPassword);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL is : \n" + currentUrl);
        Assert.assertTrue(loginObj.checkLogOutIsDisplayed(), "LogOut Button is displayed!");
    }


}
