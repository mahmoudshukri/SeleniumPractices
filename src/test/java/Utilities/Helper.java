package Utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Helper {
    // Method to take screenshot when the test case fails
    public static void captureScreenshot(WebDriver driver, String screenshotName) {


        Path pathDes = Paths.get("./ScreenShots", screenshotName + ".png");
        try {
            // Create parent directory if it doesn't exist
            Files.createDirectories(pathDes.getParent());
            FileOutputStream out = new FileOutputStream(pathDes.toString());
            out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
            out.close();
            System.out.println("Screenshot captured successfully at: " + pathDes);
        } catch (IOException e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for detailed error information
        }

    }
}
