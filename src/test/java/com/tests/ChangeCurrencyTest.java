package com.tests;

import com.pages.HomePage;
import com.pages.ProductDetailsPage;
import com.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ChangeCurrencyTest extends TestBase{
    HomePage homeObj;
    ProductDetailsPage detailsObj;
    SearchPage searchPageObj;

    String searchTxt="mac";
    @Test(priority = 1,alwaysRun = true)
    public void UserCanChangeCurrency(){
        homeObj=new HomePage(driver);
        detailsObj=new ProductDetailsPage(driver);
        homeObj.changeCurrency();

    }

    @Test(priority = 2,dependsOnMethods = "UserCanChangeCurrency")
    public void UserCanSearchWithAutoSuggest(){
        try {
            searchPageObj=new SearchPage(driver);
            searchPageObj.productSearchUsingAutoSuggest(searchTxt);
            detailsObj=new ProductDetailsPage(driver);
            Assert.assertTrue(detailsObj.getProductNameBreadCrumbVisibility());
            Assert.assertEquals(detailsObj.getProductNameBreadCrumbText(),"Apple MacBook Pro 13-inch");
            System.out.println("ProductNameBreadCrumbText is : \n" + detailsObj.getProductNameBreadCrumbText());
            Assert.assertTrue(detailsObj.getProductPriceLabel().contains("€"));
            System.out.println("Product Price Label is : \n " +detailsObj.getProductPriceLabel());


        }
        catch (Exception e){
            System.out.println("Error Occurred"+ e.getMessage());
        }
    }
}
