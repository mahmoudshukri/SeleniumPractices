
package com.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {
    protected WebDriver driver;
    protected JavascriptExecutor jse;
    protected Select select;
    public Actions action ;
    protected WebDriverWait wait;

    // Constructor to initialize WebDriver
    protected PageBase(WebDriver driver) {
        this.driver = driver; // Assigning the driver parameter to the driver field
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        jse = (JavascriptExecutor) driver;
    }

    // Find and return WebElement using the given locator
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    // Type the given text into the input field identified by the locator
    protected void type(String text, By locator) {
        waitForElementVisibility(locator);
        find(locator).clear(); // Clear any existing text in the input field
        find(locator).sendKeys(text); // Type the given text into the input field
    }

    // Click on the element identified by the locator
    protected void click(By locator) {
        waitForElementVisibility(locator);
        find(locator).click();
    }

    // Check if the element identified by the locator is displayed on the page
    protected Boolean isDisplayed(By locator) {
        try {
            return waitForElementVisibility(locator).isDisplayed();
        }
        // If NoSuchElementException occurs, return false
        catch (NoSuchElementException exception) {
            return false;
        }
    }

    // Wait for element visibility using FluentWait
    public WebElement waitForElementVisibility(By locator) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(120))
                .pollingEvery(Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class);

        return fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void scrollToBottom() {
        jse.executeScript("scrollBy(0,2500)");
    }
}
