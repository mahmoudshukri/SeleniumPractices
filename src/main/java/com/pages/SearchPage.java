package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;



import java.util.List;

public class SearchPage extends PageBase{

    public SearchPage(WebDriver driver) {
        super(driver);

    }
    private final By searchTxtBox=By.id("small-searchterms");
    private final By searchButton=By.cssSelector("button[type='submit']");
    //private final List <WebElement> productList = (List<WebElement>) driver.findElement(By.id("ui-id-3"));
    public List<WebElement> getProductList() {
        // Wait for the element to be present on the page
        List<WebElement> productList = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("ui-menu-item-wrapper")));
        return productList;
    }
    private final By appleLink = By.cssSelector("h2[class='product-title'] a");

    public void productSearch(String productName){
        type(productName,searchTxtBox);
        click(searchButton);

    }
    public void openProductDetailsPage(){
        click(appleLink);
    }
    public void productSearchUsingAutoSuggest(String searchTxt){
        type(searchTxt,searchTxtBox);
        getProductList().get(0).click();


    }
}
