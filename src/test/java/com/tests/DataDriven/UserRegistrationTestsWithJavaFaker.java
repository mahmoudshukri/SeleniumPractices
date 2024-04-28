package com.tests.DataDriven;


import com.github.javafaker.Faker;
import com.pages.HomePage;
import com.pages.LoginPage;
import com.pages.UserRegistrationPage;
import com.tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserRegistrationTestsWithJavaFaker extends TestBase {
    HomePage homePageObj;
    UserRegistrationPage userRegistrationPageObj;
    LoginPage loginObj;
    Faker fakeData =new Faker();
    String firstName=fakeData.name().firstName();
    String lastName=fakeData.name().lastName();
    String email=fakeData.internet().emailAddress();
    String password=fakeData.number().digits(8).toString();

    @Test(priority = 1, alwaysRun = true)
    public void userCanRegisterSuccessfully() {
        homePageObj = new HomePage(driver);
        homePageObj.openRegistrationPage();
        userRegistrationPageObj = new UserRegistrationPage(driver);
        userRegistrationPageObj.userRegistration
                (
                        firstName,lastName,email,password
                );
        String SuccessfulMessage = userRegistrationPageObj.getSuccessfulMessage();
        System.out.println(SuccessfulMessage);
        Assert.assertTrue(userRegistrationPageObj
                .getSuccessfulMessage()
                .contains
                        ("Your registration completed"));
        System.out.println(
                "First Name is: \n" +firstName +"\n"
                +"Last Name is: \n" +lastName +"\n"
                + "email is : \n"+email +"\n"
                + "password is : \n"+ password +"\n"
        ) ;
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
