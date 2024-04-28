package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountPage extends PageBase {

    private final By changePasswordLink = By.xpath("//a[@href=\"/customer/changepassword\"]");
    private final By oldPasswordTxt = By.id("OldPassword");
    private final By newPasswordTxt = By.id("NewPassword");
    private final By confirmPasswordTxt=By.id("ConfirmNewPassword");
    private final By confirmChangeButton = By.cssSelector("button[class='button-1 change-password-button']");
    private final By successContent = By.cssSelector(".content");
    private final By  closeContent= By.className("close");
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public void openChangePasswordPage() {
        waitForElementVisibility(changePasswordLink);
        click(changePasswordLink);
    }
    public void changePassword(String oldPassword, String newPassword){
        type(oldPassword,oldPasswordTxt);
        type(newPassword,newPasswordTxt);
        type(newPassword,confirmPasswordTxt);
        click(confirmChangeButton);
    }
    public void closeContentMessage(){
        wait.until(ExpectedConditions.elementToBeClickable(closeContent)).click();


    }
    public boolean successContent(){
        return find(successContent).isDisplayed();
    }
}
