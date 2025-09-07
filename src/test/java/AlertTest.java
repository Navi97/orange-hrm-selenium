import manager.DriverManager;
import org.apache.commons.io.FileUtils;
import org.example.pageObject.AlertPage;
import org.example.pageObject.BrowserAlertPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class AlertTest {
    private WebDriver webDriver;
    AlertPage alertPage;
    BrowserAlertPage browserAlertPage;

    @BeforeMethod
    public void before() {
        webDriver = DriverManager.getWebdriver();
        alertPage = new AlertPage(webDriver);
        browserAlertPage = new BrowserAlertPage(webDriver);
    }

    @Test
    public void alert(){
        alertPage.visit();
        alertPage.clickHereButton().click();
        alertPage.modalVisible();
        alertPage.closeButton().click();
        Assert.assertTrue(alertPage.modalInvisible(),"Modal didn't closed");
    }

    @Test
    public void browserAlert(){
        browserAlertPage.visit();
        browserAlertPage.rightClickBox();
        browserAlertPage.switchToAlert().accept();
        browserAlertPage.verifyAlertInvisible();
    }
    @AfterMethod
    public void captureScreenshot(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) webDriver;
                File src = ts.getScreenshotAs(OutputType.FILE);
                String path = "target/screenshots/" + result.getName() + ".png";
                FileUtils.copyFile(src, new File(path));
                System.out.println("📸 Screenshot saved: " + path);
            } catch (Exception e) {
                System.out.println("Unable to capture screenshot" + e.getMessage());
            }
        }
        DriverManager.quitWebdriver();
    }
}
