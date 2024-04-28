package com.tests;

import com.pages.ComparePage;
import com.pages.ProductDetailsPage;
import com.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductToCompareTest extends TestBase {
    String firstProductName = "Apple MacBook Pro 13-inch";
    String secondProductName = "Asus N551JK-XO076H Laptop";

    //1. Search for product Number 1.
    //2. Search for product Number 2.
    //3. Add to compare.
    //4. Clear List.
    ProductDetailsPage detailsPageObj;

    ComparePage comparePageObj;
    SearchPage searchPageObj;

    @Test(priority = 1, alwaysRun = true)
    public void UserCanCompareProducts() throws InterruptedException {
        detailsPageObj = new ProductDetailsPage(driver);
        comparePageObj = new ComparePage(driver);
        searchPageObj = new SearchPage(driver);

        searchPageObj.productSearchUsingAutoSuggest("mac");
        Assert.assertTrue(detailsPageObj.getProductNameBreadCrumbText().contains(firstProductName));
        detailsPageObj.addProductToCompare();
        Thread.sleep(2000);

        searchPageObj.productSearchUsingAutoSuggest("Asus");
        Assert.assertTrue(detailsPageObj.getProductNameBreadCrumbText().contains(secondProductName));
        detailsPageObj.addProductToCompare();
        Thread.sleep(2000);
        driver.navigate().to("https://demo.nopcommerce.com" + "/compareproducts");
        Assert.assertTrue(comparePageObj.getFirstProductName().equals(firstProductName));
        Assert.assertTrue(comparePageObj.getSecondProductName().equals(secondProductName));
        comparePageObj.CompareProducts();

    }

    @Test(priority = 2, dependsOnMethods = "UserCanCompareProducts")
    public void UserCanClearCompareList() {
        comparePageObj.clearCompareList();
        Assert.assertTrue(comparePageObj.getNoDataLbl().contains("You have no items to compare."));
    }
}
