import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.function.Function;

import java.sql.Driver;
import java.time.Duration;

public class HerokuappTest {
    private WebDriver webDriver;
    @BeforeMethod
    public void before(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void addElement(){
        webDriver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        System.out.println(webDriver.getTitle());
        WebElement clickAddButton = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));
        clickAddButton.click();
        WebElement clickRemoveButton = webDriver.findElement(By.xpath("//*[@id=\"elements\"]/button"));
        clickRemoveButton.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        boolean isInvisible = wait.until(
                ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"elements\"]/button"))
        );
        Assert.assertTrue(isInvisible, "Delete button should be invisible after clicking!");

    }
    @Test
    public void dropdown(){
        webDriver.get("https://the-internet.herokuapp.com/dropdown");
        System.out.println(webDriver.getTitle());
        WebElement dropdownList = webDriver.findElement(By.id("dropdown"));
        Select select = new Select(dropdownList);
        select.selectByIndex(1);
        String selectedOption = select.getFirstSelectedOption().getText();
        System.out.println("Selected option is: " + selectedOption);
        Assert.assertEquals(selectedOption, "Option 1", "Dropdown selection is not as expected!");

    }
    @Test
    public void checkbox(){
        webDriver.get("https://the-internet.herokuapp.com/checkboxes");
        System.out.println(webDriver.getTitle());
        WebElement checkbox1 = webDriver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
        checkbox1.click();
        Assert.assertTrue(checkbox1.isSelected());
        checkbox1.click();
        Assert.assertFalse(checkbox1.isSelected());
        WebElement checkbox2 = webDriver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]"));
        checkbox2.click();
        Assert.assertFalse(checkbox2.isSelected());
        checkbox2.click();
        Assert.assertTrue(checkbox2.isSelected());

    }
    public File waitForFileDownload(String downloadPath, int timeoutSeconds) {
        File dir = new File(downloadPath);
        FluentWait<File> wait = new FluentWait<File>(dir)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(Exception.class);

        return wait.until((file) -> {
            File latest = getLatestModifiedFile(downloadPath);
            return (latest != null && !latest.getName().endsWith(".crdownload")) ? latest : null;
        });
    }
    @Test
    public void filedownload() throws IOException {
        webDriver.get("https://the-internet.herokuapp.com/download");
        WebElement clickImage = webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/a[1]"));
        clickImage.click();
        String downloadPath = "/Users/vaishnavipukale/Downloads";

        File file = waitForFileDownload(downloadPath , 15);
        System.out.println(file.getCanonicalFile().getName());
        Assert.assertTrue(file.getCanonicalFile().getName().contains("kote"));
        System.out.println("File download successful ✅");
    }

    private File getLatestModifiedFile(String downloadPath) {
        File dir = new File(downloadPath);
        File[] files = dir.listFiles();
        if(files.length == 0 || files == null){
            return null;
        }
        File assumedFile = files[0];
        for(int i=0;i< files.length;i++){
            if(assumedFile.lastModified() < files[i].lastModified()){
                assumedFile = files[i];
            }

        }
        return assumedFile;

    }




    @AfterMethod
    public void after(){
        webDriver.quit();
    }
}
