package com.tests.DataDriven;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.UserRegistrationPage;
import com.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

public class UserRegistrationTestsWithDdtUsingCsV extends TestBase {
    HomePage homePageObj;
    UserRegistrationPage userRegistrationPageObj;
    LoginPage loginObj;
    CSVReader reader;


    @Test(priority = 1, alwaysRun = true)
    public void userCanRegisterSuccessfully() throws IOException, CsvValidationException {
        // Get Path of CSV file.
        String CSV_file = System.getProperty("user.dir") + "/src/test/java/Data/UserRegData.csv";
        reader = new CSVReader(new FileReader(CSV_file));
        String[] csvCell;
        //While loop will be executed till the last line/value in CSV file.
        while ((csvCell = reader.readNext()) != null) {
            String firstname = csvCell[0];
            String lastname = csvCell[1];
            String email = csvCell[2];
            String password = csvCell[3];

            homePageObj = new HomePage(driver);
            homePageObj.openRegistrationPage();
            userRegistrationPageObj = new UserRegistrationPage(driver);
            userRegistrationPageObj.userRegistration
                    (
                            firstname,
                            lastname,
                            email,
                            password
                    );
//            String SuccessfulMessage = userRegistrationPageObj.getSuccessfulMessage();
//            System.out.println(SuccessfulMessage);
//            Assert.assertTrue(userRegistrationPageObj
//                    .getSuccessfulMessage()
//                    .contains
//                            ("Your registration completed"));
            loginObj = new LoginPage(driver);
            loginObj.userLogin(email, password);
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL is : \n" + currentUrl);
            Assert.assertTrue(loginObj.checkLogOutIsDisplayed(), "LogOut Button is displayed!");
            loginObj.userLogOut();
        }


    }


//    @Test(dependsOnMethods = "userCanRegisterSuccessfully")
//    public void RegisteredUserCanLogIn() {
//
//        homePageObj.openLoginPage();
//        loginObj = new LoginPage(driver);
//        loginObj.userLogin(email, password);
//        String currentUrl = driver.getCurrentUrl();
//        System.out.println("Current URL is : \n" + currentUrl);
//        Assert.assertTrue(loginObj.checkLogOutIsDisplayed(), "LogOut Button is displayed!");
//        loginObj.userLogOut();
//    }

//    @Test(dependsOnMethods = "RegisteredUserCanLogIn")
//    public void RegisteredUserCanLogOut() {
//        loginObj.userLogOut();
//    }


    }
