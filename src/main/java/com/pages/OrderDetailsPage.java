package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderDetailsPage extends PageBase{

    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }
    private final By pdfInvoiceLink=By.cssSelector(".button-2.pdf-invoice-button");
    private final By printInvoiceLink=By.cssSelector(".button-2.print-order-button");

    public void PrintOrderDetails(){
        waitForElementVisibility(printInvoiceLink);
        click(printInvoiceLink);
    }
    public void DownLoadPdfInvoice() throws InterruptedException {
        click(pdfInvoiceLink);
        Thread.sleep(2000);
    }

}
