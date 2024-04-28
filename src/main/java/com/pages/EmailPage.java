package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EmailPage extends PageBase{
    public EmailPage(WebDriver driver) {
        super(driver);
    }
    private final By emailFriendTxt = By.id("FriendEmail");
    private final By personalMessageTxt = By.id("PersonalMessage");
    private final By sendEmailButton= By.cssSelector("button[name='send-email']");
    private final By messageNotification=By.cssSelector(".result");

    public void SendEmailToFriend(String friendEmail, String personalMessage){
            type(friendEmail,emailFriendTxt);
            type(personalMessage,personalMessageTxt);
            click(sendEmailButton);
    }
    public String getMessageNotification(){
        return find(messageNotification).getText();
    }
    public Boolean checkMessageNotification(){
        return find(messageNotification).isDisplayed();
    }





}
