import org.apache.commons.io.FileUtils;
import org.example.pageObject.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static utils.FileUtility.waitForFileDownload;

public class HerokuappTest {
    private WebDriver webDriver;
    AddRemoveElementsPage addRemoveElementsPage;
    DropdownPage dropdownPage;
    CheckboxPage checkboxPage;
    FiledownloadPage filedownloadPage;
    FileuploadPage fileuploadPage;
    AlertPage alertPage;
    BrowserAlertPage browserAlertPage;
    HoverPage hoverPage;
    KeyPressPage keyPressPage;
    JavascriptAlertPage javascriptAlertPage;
    NestedDropdownMenuPage nestedDropdownMenuPage;
    DragandDropPage dragandDropPage;
    ScrollPage scrollPage;

    @BeforeMethod
    public void before(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        addRemoveElementsPage = new AddRemoveElementsPage(webDriver);
        dropdownPage = new DropdownPage(webDriver);
        checkboxPage = new CheckboxPage(webDriver);
        filedownloadPage = new FiledownloadPage(webDriver);
        fileuploadPage = new FileuploadPage(webDriver);
        alertPage = new AlertPage(webDriver);
        browserAlertPage = new BrowserAlertPage(webDriver);
        hoverPage = new HoverPage(webDriver);
        keyPressPage = new KeyPressPage(webDriver);
        javascriptAlertPage = new JavascriptAlertPage(webDriver);
        nestedDropdownMenuPage = new NestedDropdownMenuPage(webDriver);
        dragandDropPage = new DragandDropPage(webDriver);
        scrollPage = new ScrollPage(webDriver);

    }

    @Test
    public void addElement(){
        addRemoveElementsPage.visit();
        addRemoveElementsPage.addButton().click();
        addRemoveElementsPage.removeButton().click();
        boolean isInvisible = addRemoveElementsPage.isInvisible(addRemoveElementsPage.removeButtonLocator());
        Assert.assertTrue(isInvisible, "Delete button should be invisible after clicking!");
    }

    @Test
    public void dropdown(){
        dropdownPage.visit();
        System.out.println(dropdownPage.title());
        String selectedOption = dropdownPage.selectOptions(2);
        Assert.assertEquals(selectedOption, "Option 2", "Dropdown selection is not as expected!");
    }

    @Test
    public void checkbox(){
        checkboxPage.visit();
        System.out.println(checkboxPage.title());
        checkboxPage.checkbox(1).click();
        Assert.assertTrue(checkboxPage.checkbox(1).isSelected());
    }

    @Test
    public void filedownload() throws IOException {
        filedownloadPage.visit();
        WebElement link = filedownloadPage.link(1);
        String text = link.getText().split("\\.")[0];
        File file = filedownloadPage.download(link);
        String downloadedFileName = file.getCanonicalFile().getName();
        Assert.assertTrue(downloadedFileName.contains(text),"File download assertion failed: " + downloadedFileName + " " + text);
    }

    @Test
    public void fileUpload() throws IOException {
        fileuploadPage.visit();
        File file = new File(  "src/main/resources/menu.pdf");
        fileuploadPage.upload(file);
        WebElement uploadFileSection = fileuploadPage.uploadFileSection();
        Assert.assertTrue(uploadFileSection.getText().contains(file.getCanonicalFile().getName()));
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

    @Test
    public void hover() {
        hoverPage.visit();
        hoverPage.hoverOnUser1();
        hoverPage.viewProfile().click();
        String actualText = hoverPage.notFoundMessage().getText();
        Assert.assertEquals(actualText, "Not Found", "Profile page did not show 'Not Found'");
    }

    @Test
    public void keyPresses(){
        keyPressPage.visit();
        keyPressPage.keyPressSection().sendKeys(Keys.TAB);
        Assert.assertEquals(keyPressPage.result().getText(),"You entered: TAB");
    }

    @Test
    public void javascriptAcceptAlert(){
        javascriptAlertPage.visit();
        javascriptAlertPage.jsAlert1Section().click();
        javascriptAlertPage.switchToAlert().accept();
        Assert.assertEquals(javascriptAlertPage.result().getText(),"You successfully clicked an alert");
    }

    @Test
    public void javascriptDismissAlert(){
        javascriptAlertPage.visit();
        javascriptAlertPage.jsAlert2Section().click();
        javascriptAlertPage.switchToAlert().dismiss();
        Assert.assertEquals(javascriptAlertPage.result().getText(),"You clicked: Cancel");
    }

    @Test
    public void javascriptAddTextAlert(){
        javascriptAlertPage.visit();
        javascriptAlertPage.jsAlert3Section().click();
        javascriptAlertPage.switchToAlert().sendKeys("Hello");
        javascriptAlertPage.switchToAlert().accept();
        Assert.assertEquals(javascriptAlertPage.result().getText(),"You entered: Hello");
    }

    @Test
    public void nestedDropdownMenu() throws IOException {
        nestedDropdownMenuPage.visit();
        nestedDropdownMenuPage.moveToElement(nestedDropdownMenuPage.dropdownEnable());
        nestedDropdownMenuPage.moveToElement(nestedDropdownMenuPage.dropdownDownload());
        WebElement pdf = nestedDropdownMenuPage.dropdownPdf();
        nestedDropdownMenuPage.moveToElement(pdf);
        File file = nestedDropdownMenuPage.download(pdf);
        Assert.assertTrue(file.getCanonicalFile().getName().contains("menu"));
    }

    @Test
    public void dragAndDrop(){
        dragandDropPage.visit();
        dragandDropPage.columnASection();
        dragandDropPage.columnBSection();
        dragandDropPage.dragAndDropAction().dragAndDrop( dragandDropPage.columnASection(),dragandDropPage.columnBSection()).perform();
    }

    @Test
    public void scroll(){
        scrollPage.visit();
        scrollPage.scrollAction().scrollByAmount(0,500).perform();
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
        webDriver.quit();
    }
}
