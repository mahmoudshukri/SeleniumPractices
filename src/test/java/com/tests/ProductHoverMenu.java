package com.tests;

import com.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductHoverMenu extends TestBase{
    HomePage homePageObj;
    @Test
    public void UserCanSelectSubCategoryFromMainMenu(){
        homePageObj=new HomePage(driver);
        homePageObj.computerMenu();
        homePageObj.noteBooksMenu();
        String URL=driver.getCurrentUrl();
        System.out.println("Current URL is \n" + URL);
        Assert.assertTrue(driver.getCurrentUrl().contains("computers"));
    }
}
