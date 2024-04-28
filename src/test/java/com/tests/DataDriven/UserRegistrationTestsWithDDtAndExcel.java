package com.tests.DataDriven;


import Utilities.ExcelReader;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.UserRegistrationPage;
import com.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserRegistrationTestsWithDDtAndExcel extends TestBase {
    HomePage homePageObj;
    UserRegistrationPage userRegistrationPageObj;
    LoginPage loginObj;


    @DataProvider(name = "ExcelData")
    public Object[][] userRegisterData() throws IOException {
        // get data from Excel reader class
        ExcelReader ER = new ExcelReader();
        return ER.getExcelData();
    }


    @Test(priority = 1, alwaysRun = true, dataProvider = "ExcelData")
    public void userCanRegisterSuccessfully(String firstName, String lastName, String email, String password) {
        homePageObj = new HomePage(driver);
        homePageObj.openRegistrationPage();
        userRegistrationPageObj = new UserRegistrationPage(driver);
        userRegistrationPageObj.userRegistration
                (
                        firstName, lastName, email, password
                );
        String SuccessfulMessage = userRegistrationPageObj.getSuccessfulMessage();
        System.out.println(SuccessfulMessage);
        Assert.assertTrue(userRegistrationPageObj
                .getSuccessfulMessage()
                .contains
                        ("Your registration completed"));
    }


    @Test(dependsOnMethods = "userCanRegisterSuccessfully",dataProvider="ExcelData")
    public void RegisteredUserCanLogIn(String firstName, String lastName, String email, String password) {
        homePageObj.openLoginPage();
        loginObj = new LoginPage(driver);
        loginObj.userLogin(email, password);
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL is : \n" + currentUrl);
        Assert.assertTrue(loginObj.checkLogOutIsDisplayed(), "LogOut Button is displayed!");
        loginObj.userLogOut();
    }


}
