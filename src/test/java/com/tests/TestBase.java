package com.tests;

import Utilities.Helper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestBase {
    //public static WebDriver driver;
    protected WebDriver driver;
//    @BeforeSuite
//    public void setUpDriver() {
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
//        driver.get("https://demo.nopcommerce.com");
//
//    }

    @BeforeClass
    @Parameters({"browser"})
    public void setUpDriver(@Optional("firefox") String browserName) {

        if (browserName.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("Edge")) {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com");
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

    }

    //Taking Screenshot when test case fail and add it in the ScreenShot folder.
    @AfterMethod
    public void screenshotOnFailure(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed");
            System.out.println("Taking ScreenShot....");
            Helper.captureScreenshot(driver, result.getName());

        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
