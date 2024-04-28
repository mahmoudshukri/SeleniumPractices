package com.tests;

import com.pages.ProductDetailsPage;
import com.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchProductTests extends TestBase{
    String productName="Apple MacBook Pro 13-inch";
    SearchPage searchPageObj;
    ProductDetailsPage detailsObj;
    @Test
    public void UserCanSearchForProducts(){
        searchPageObj=new SearchPage(driver);
        detailsObj = new ProductDetailsPage(driver);
        searchPageObj.productSearch(productName);
        searchPageObj.openProductDetailsPage();
         Assert.assertEquals(detailsObj.getProductNameBreadCrumbText(), productName);
         Assert.assertTrue(detailsObj.getProductNameBreadCrumbVisibility());
    }
}
