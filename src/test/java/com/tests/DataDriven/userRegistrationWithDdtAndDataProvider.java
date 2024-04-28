package com.tests.DataDriven;


import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.UserRegistrationPage;
import com.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class userRegistrationWithDdtAndDataProvider extends TestBase {
    HomePage homePageObj;
    UserRegistrationPage userRegistrationPageObj;
    LoginPage loginObj;
    


    @DataProvider(name = "testData")
    public static Object[][] userData() {
        return new Object[][]
                {

                        {"Moo_01", "Nabil", "test11@gmail.com", "test@123"

                        },
                        {"Moo_02", "Salah", "test12@gmail.com", "test@123"

                        }

                };
    }


    @Test(priority = 1, alwaysRun = true,dataProvider = "testData")
    public void userCanRegisterSuccessfully(String fName,String lName, String email, String password) {
        homePageObj = new HomePage(driver);
        homePageObj.openRegistrationPage();
        userRegistrationPageObj = new UserRegistrationPage(driver);
        userRegistrationPageObj.userRegistration
                (
                        fName,lName,email,password
                );
        String SuccessfulMessage = userRegistrationPageObj.getSuccessfulMessage();
        System.out.println(SuccessfulMessage);
        Assert.assertTrue(userRegistrationPageObj
                .getSuccessfulMessage()
                .contains
                        ("Your registration completed"));
    }


    @Test(dependsOnMethods = "userCanRegisterSuccessfully", dataProvider = "testData")
    public void RegisteredUserCanLogIn(String fName,String lName, String email, String password) {
        homePageObj.openLoginPage();
        loginObj = new LoginPage(driver);
        loginObj.userLogin(email,password );
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL is : \n" + currentUrl);
        Assert.assertTrue(loginObj.checkLogOutIsDisplayed(), "LogOut Button is displayed!");
        loginObj.userLogOut();
    }

//    @Test(dependsOnMethods = "RegisteredUserCanLogIn")
//    public void RegisteredUserCanLogOut() {
//        loginObj.userLogOut();
//    }


}
