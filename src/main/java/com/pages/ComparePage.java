package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ComparePage extends PageBase{
    public ComparePage(WebDriver driver) {
        super(driver);
    }

    private final By clearListLink = By.xpath("//a[normalize-space()='Clear list']");
    private final By noDataLbl = By.xpath("//div[@class='no-data']");
    private final By compareTable=By.xpath("//table[@class='compare-products-table']");
    private final By firstProductName=By.xpath("    //tr[@class='product-name']//a[contains(text(),'Apple MacBook Pro 13-inch')]");

    private final By secondProductName=By.xpath("//tr[@class='product-name']//a[contains(text(),'Asus N551JK-XO076H Laptop')]");
    private final By allRowsLocator = By.cssSelector("table.compare-products-table tr");
    private final By allColumnsLocator = By.cssSelector("table.compare-products-table td");


    public void clearCompareList() {
        waitForElementVisibility(clearListLink);
        wait.until(ExpectedConditions.elementToBeClickable(clearListLink)).click();

    }

    public void CompareProducts() {
        // Get all rows.
        List<WebElement> allRows = driver.findElements(allRowsLocator);
        waitForElementVisibility(allRowsLocator);
        System.out.println("Number of rows: " + allRows.size());

        // Get data from each row.
        for (WebElement row : allRows) {
            List<WebElement> columnsInRow = row.findElements(allColumnsLocator);
            waitForElementVisibility(allColumnsLocator);
            for (WebElement column : columnsInRow) {
                System.out.print(column.getText() + "\t");
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }
    public String getFirstProductName(){
        waitForElementVisibility(firstProductName);
        return find(firstProductName).getText();
    }
    public String getSecondProductName(){
        waitForElementVisibility(secondProductName);
        return find(secondProductName).getText();
    }
    public String getNoDataLbl(){
        waitForElementVisibility(noDataLbl);
        return find(noDataLbl).getText();
    }
}
