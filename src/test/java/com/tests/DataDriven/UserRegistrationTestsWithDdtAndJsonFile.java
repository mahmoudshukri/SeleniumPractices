package com.tests.DataDriven;

import Utilities.JsonDataReader;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.UserRegistrationPage;
import com.tests.TestBase;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserRegistrationTestsWithDdtAndJsonFile extends TestBase {

    HomePage homePageObj;
    UserRegistrationPage userRegistrationPageObj;
    LoginPage loginObj;

    @Test(priority = 1, alwaysRun = true)
    public void userCanRegisterSuccessfully() throws IOException, ParseException {
        // User Registration
        JsonDataReader jsonReader = new JsonDataReader();
        jsonReader.JsonReader();

        for (int i = 0; i < jsonReader.firstNames.size(); i++) {
            homePageObj = new HomePage(driver);
            homePageObj.openRegistrationPage();
            userRegistrationPageObj = new UserRegistrationPage(driver);
            userRegistrationPageObj.userRegistration(
                    jsonReader.firstNames.get(i),
                    jsonReader.lastNames.get(i),
                    jsonReader.emails.get(i),
                    jsonReader.passwords.get(i));
            // Assertions of User Registeration Step
            String SuccessfulMessage = userRegistrationPageObj.getSuccessfulMessage();
            System.out.println(SuccessfulMessage);
            Assert.assertTrue(userRegistrationPageObj.getSuccessfulMessage().contains("Your registration completed"));
            // User Logging In.
            loginObj = new LoginPage(driver);
            loginObj.userLogin(jsonReader.emails.get(i), jsonReader.passwords.get(i));
            // Assertions of User Login Step
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL is : \n" + currentUrl);
            Assert.assertTrue(loginObj.checkLogOutIsDisplayed(), "LogOut Button is displayed!");
            // User Logging out.
            loginObj.userLogOut();
        }
    }
}