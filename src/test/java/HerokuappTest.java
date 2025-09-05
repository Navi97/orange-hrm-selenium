import org.apache.commons.io.FileUtils;
import org.example.pageObject.AddRemoveElementsPage;
import org.example.pageObject.CheckboxPage;
import org.example.pageObject.DropdownPage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class HerokuappTest {
    private WebDriver webDriver;
    AddRemoveElementsPage addRemoveElementsPage;
    DropdownPage dropdownPage;
    CheckboxPage checkboxPage;

    @BeforeMethod
    public void before(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        addRemoveElementsPage = new AddRemoveElementsPage(webDriver);
        dropdownPage = new DropdownPage(webDriver);
        checkboxPage = new CheckboxPage(webDriver);
    }
    @Test
    public void addElement(){
        addRemoveElementsPage.visit();
        System.out.println(addRemoveElementsPage.title());
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
    public File waitForFileDownload(String downloadPath, int timeoutSeconds) {
        File dir = new File(downloadPath);
        FluentWait<File> wait = new FluentWait<>(dir)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(Exception.class);

        return wait.until((file) -> {
            File latest = getLatestModifiedFile(downloadPath);
            return (latest != null && !latest.getName().endsWith(".crdownload")) && !latest.getName().startsWith(".com.google") ? latest : null;
        });
    }
    @Test
    public void filedownload() throws IOException {
        webDriver.get("https://the-internet.herokuapp.com/download");
        WebElement element = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/a[1]"));
        String text = element.getText().split("\\.")[0];
        System.out.println(text);
        element.click();
        String downloadPath = "/Users/vaishnavipukale/Downloads";
        File file = waitForFileDownload(downloadPath , 15);
        System.out.println(file.getCanonicalFile().getName());
        Assert.assertTrue(file.getCanonicalFile().getName().contains(text));
        System.out.println("File download successful ✅");
    }
    private File getLatestModifiedFile(String downloadPath) {
        File dir = new File(downloadPath);
        File[] files = dir.listFiles();
        if((files == null) || (files.length == 0)){
            return null;
        }
        File assumedFile = files[0];
        for (File file : files) {
            if (assumedFile.lastModified() < file.lastModified()) {
                assumedFile = file;
            }
        }
        return assumedFile;
    }
    @Test
    public void fileUpload() {
        webDriver.get("https://the-internet.herokuapp.com/upload");
        File file = new File(  "src/main/resources/menu.pdf");
        WebElement selectFile = webDriver.findElement(By.id("file-upload"));
        selectFile.sendKeys(file.getAbsolutePath());
        System.out.println("Uploaded file: " + file.getAbsolutePath());
        WebElement uploadFile = webDriver.findElement(By.id("file-submit"));
        uploadFile.click();
        WebElement fileUploaded = webDriver.findElement(By.id("uploaded-files"));
        Assert.assertEquals(fileUploaded.getText(),"menu.pdf");
    }
    @Test
    public void alert(){
        webDriver.get("https://the-internet.herokuapp.com/entry_ad");
        WebElement clickHere = webDriver.findElement(By.id("restart-ad"));
        clickHere.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal")));
        WebElement closeButton = webDriver.findElement(By.xpath("//*[@id='modal']/div[2]/div[3]/p"));
        closeButton.click();
        Boolean modalClosed = wait.until(ExpectedConditions.invisibilityOf(modal));
        Assert.assertTrue(modalClosed,"Modal didn't closed");
        System.out.println("Modal closed successfully");
    }
    @Test
    public void alert1(){
        webDriver.get("https://the-internet.herokuapp.com/context_menu");
        WebElement boxRightClick = webDriver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(webDriver);
        actions.contextClick(boxRightClick).perform();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = webDriver.switchTo().alert();
        System.out.println("Alert says: " + alert.getText());
        alert.accept();
        System.out.println("Alert closed successfully");
    }
    @Test
    public void hover() {
        webDriver.get("https://the-internet.herokuapp.com/hovers");
        WebElement user1 = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(user1).perform();
        WebElement clickViewProfile = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a"));
        clickViewProfile.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        WebElement notFoundMsg = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Not Found']"))
        );
        String actualText = notFoundMsg.getText();
        Assert.assertEquals(actualText, "Not Found", "Profile page did not show 'Not Found'");
    }
    @Test
    public void keyPresses(){
        webDriver.get("https://the-internet.herokuapp.com/key_presses");
        WebElement keyPress = webDriver.findElement(By.id("target"));
        keyPress.sendKeys(Keys.TAB);
        WebElement result = webDriver.findElement(By.id("result"));
        Assert.assertEquals(result.getText(),"You entered: TAB");
    }
    @Test
    public void javascriptAlert(){
        webDriver.get("https://the-internet.herokuapp.com/javascript_alerts");
        WebElement jsAlert1 = webDriver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        jsAlert1.click();
        Alert alert = webDriver.switchTo().alert();
        System.out.println("Alert says: " + alert.getText());
        alert.accept();
        System.out.println("Alert closed successfully");
        WebElement result = webDriver.findElement(By.id("result"));
        Assert.assertEquals(result.getText(),"You successfully clicked an alert");
        WebElement jsAlert2 = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[2]/button"));
        jsAlert2.click();
        Alert alert2 = webDriver.switchTo().alert();
        alert2.dismiss();
        Assert.assertEquals(result.getText(),"You clicked: Cancel");
        WebElement jsAlert3 = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[3]/button"));
        jsAlert3.click();
        Alert alert3 = webDriver.switchTo().alert();
        alert3.sendKeys("Hello");
        alert3.accept();
        Assert.assertEquals(result.getText(),"You entered: Hello");
    }
    @Test
    public void jQueryUi() throws IOException {
        webDriver.get("https://the-internet.herokuapp.com/jqueryui/menu#");
        Actions actions = new Actions(webDriver);
        WebElement clickEnable = webDriver.findElement(By.id("ui-id-3"));
        actions.moveToElement(clickEnable).perform();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement clickDownloads = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-4")));
        actions.moveToElement(clickDownloads).perform();
        WebElement clickPdf = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-5")));
        clickPdf.click();
        String downloadPath = "/Users/vaishnavipukale/Downloads";
        File file = waitForFileDownload(downloadPath , 15);
        System.out.println(file.getCanonicalFile().getName());
        Assert.assertTrue(file.getCanonicalFile().getName().contains("menu"));
        System.out.println("File download successful ✅");
    }
    @Test
    public void dragAndDrop(){
        webDriver.get("https://the-internet.herokuapp.com/drag_and_drop");
        WebElement columnA = webDriver.findElement(By.id("column-a"));
        WebElement columnB = webDriver.findElement(By.id("column-b"));
        Actions actions = new Actions(webDriver);
        actions.dragAndDrop(columnA,columnB).perform();
    }
    @Test
    public void scroll(){
        webDriver.get("https://the-internet.herokuapp.com/infinite_scroll");
        Actions actions = new Actions(webDriver);
        actions.scrollByAmount(0,500).perform();
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
                e.printStackTrace();
            }
        }
        webDriver.quit();
    }
}
