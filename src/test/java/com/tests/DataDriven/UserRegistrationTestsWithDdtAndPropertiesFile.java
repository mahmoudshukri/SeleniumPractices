package com.tests.DataDriven;


import Utilities.LoadProperties;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.UserRegistrationPage;
import com.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRegistrationTestsWithDdtAndPropertiesFile extends TestBase {
    HomePage homePageObj;
    UserRegistrationPage userRegistrationPageObj;
    LoginPage loginObj;
    String fn= LoadProperties.userRegData.getProperty("firstname");
    String ln=LoadProperties.userRegData.getProperty("lastname");
    String email=LoadProperties.userRegData.getProperty("email");
    String password=LoadProperties.userRegData.getProperty("password");

    @Test(priority = 1, alwaysRun = true)
    public void userCanRegisterSuccessfully() {
        homePageObj = new HomePage(driver);
        homePageObj.openRegistrationPage();
        userRegistrationPageObj = new UserRegistrationPage(driver);
        userRegistrationPageObj.userRegistration
                (
                        fn,
                        ln,
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


    @Test(dependsOnMethods = "userCanRegisterSuccessfully")
    public void RegisteredUserCanLogIn(){
        homePageObj.openLoginPage();
        loginObj = new LoginPage(driver);
        loginObj.userLogin(email, password);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL is : \n"+currentUrl);
        Assert.assertTrue(loginObj.checkLogOutIsDisplayed(),"LogOut Button is displayed!");
    }

    @Test(dependsOnMethods = "RegisteredUserCanLogIn")
    public void RegisteredUserCanLogOut() {
        loginObj.userLogOut();
    }


}
