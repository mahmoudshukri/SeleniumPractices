package com.tests;


import com.pages.ProductDetailsPage;
import com.pages.SearchPage;
import com.pages.WishListPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddProductToWishListTest extends TestBase{
    SearchPage searchPageObj;
    ProductDetailsPage detailsObj;
    WishListPage wishListObj;
    String searchTxt = "Apple MacBook Pro 13-inch";

    @Test(priority = 1, alwaysRun = true)
    public void UserCanSearchWithAutoSuggest() {
        try {
            searchPageObj = new SearchPage(driver);
            searchPageObj.productSearchUsingAutoSuggest(searchTxt);
            detailsObj = new ProductDetailsPage(driver);
        } catch (Exception e) {
            System.out.println("Error Occurred" + e.getMessage());
        }
    }
    @Test(priority = 2,dependsOnMethods = "UserCanSearchWithAutoSuggest")
    public void UserCanAddProductToWishList(){
        detailsObj=new ProductDetailsPage(driver);
        detailsObj.addProductToWishList();
        wishListObj=new WishListPage(driver);
        Assert.assertTrue(wishListObj.getWishListHeader().equals("Wishlist"));
      // Assert.assertTrue(wishListObj.getProductNameInCell().equals(searchTxt));

    }
@Test(priority = 3,dependsOnMethods = "UserCanAddProductToWishList")
public void UserCanRemoveProductFromCart() {
        wishListObj=new WishListPage(driver);
        wishListObj.removeProductFromCart();
       // String productNameInCell = wishListObj.getProductNameInCell();
        String emptyWishList=wishListObj.getEmptyCartMessage();
       // Assert.assertTrue(productNameInCell.equals(searchTxt));
        Assert.assertTrue(emptyWishList.equals("The wishlist is empty!"));
}



}
