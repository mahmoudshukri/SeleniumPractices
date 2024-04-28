package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductReviewPage extends PageBase {
    private final By reviewTitleTxt = By.id("AddProductReview_Title");
    private final By reviewTxt = By.id("AddProductReview_ReviewText");
    private final By rating4RadioBtn=By.id("addproductrating_4");
    private final By submitReviewBtn = By.cssSelector("button[name='add-review']");
    private final By reviewNotification = By.cssSelector("div[class='result']");
    public ProductReviewPage(WebDriver driver) {
        super(driver);
    }

    public void AddProductReview(String reviewTitle, String reviewMessage){
        type(reviewTitle,reviewTitleTxt);
        type(reviewMessage,reviewTxt);
        click(rating4RadioBtn);
        click(submitReviewBtn);
    }
    public String getReviewNotificationAfterSubmit(){
        return  find(reviewNotification).getText();
    }
}
