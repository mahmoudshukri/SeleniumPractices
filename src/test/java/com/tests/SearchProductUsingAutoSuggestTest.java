package com.tests;

import com.pages.ProductDetailsPage;
import com.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchProductUsingAutoSuggestTest extends TestBase{
    SearchPage searchPageObj;
    ProductDetailsPage detailsObj;
    String searchTxt="mac";
    @Test
    public void UserCanSearchWithAutoSuggest(){
        try {
            searchPageObj=new SearchPage(driver);
            searchPageObj.productSearchUsingAutoSuggest(searchTxt);
            detailsObj=new ProductDetailsPage(driver);
            Assert.assertTrue(detailsObj.getProductNameBreadCrumbVisibility());
            Assert.assertEquals(detailsObj.getProductNameBreadCrumbText(),"Apple MacBook Pro 13-inch");
            System.out.println("ProductNameBreadCrumbText is : \n" + detailsObj.getProductNameBreadCrumbText());

        }
        catch (Exception e){
            System.out.println("Error Occurred"+ e.getMessage());
        }
    }
}
