package com.tests;

import com.pages.ContactUsPage;
import com.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactUsTest extends TestBase {
    HomePage homePageObj;
    ContactUsPage contactUsPageObj;
    String email = "test@test.com";
    String fullName = "Test User";
    String enquiry = "Hello Admin , This for test";
    @Test

    public void UserCanContactUs(){
        homePageObj=new HomePage(driver);
        homePageObj.OpenContactUsPage();
        contactUsPageObj=new ContactUsPage(driver);
        contactUsPageObj.ContactUs(fullName,email,enquiry);
        Assert.assertTrue(contactUsPageObj.checkSuccessMessageVisibility());
        Assert.assertEquals(contactUsPageObj.getSuccessMessage(),"Your enquiry has been successfully sent to the store owner.");
    }
}
