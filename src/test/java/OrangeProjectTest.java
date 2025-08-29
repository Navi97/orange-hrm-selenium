import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;


public class OrangeProjectTest {
    private WebDriver webDriver;
    @BeforeMethod
    public void before(){
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @Test
    public void login(){

        webDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        System.out.println(webDriver.getTitle());
        WebElement username = webDriver.findElement(By.name("username"));
        username.sendKeys("Admin");
        WebElement password = webDriver.findElement(By.name("password"));
        password.sendKeys("admin123");
        WebElement loginButton = webDriver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        WebElement dashboard = webDriver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[1]/header/div[1]/div[1]"));
        Assert.assertEquals(dashboard.getText(), "Dashboard");
    }
    @Test
    public void loginFail(){
        webDriver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        System.out.println(webDriver.getTitle());
        WebElement username = webDriver.findElement(By.name("username"));
        username.sendKeys("Heath");
        WebElement password = webDriver.findElement(By.name("password"));
        password.sendKeys("123");
        WebElement loginButton = webDriver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement invalidCredential = wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/div/div[1]/div[1]/p")));
        Assert.assertEquals(invalidCredential.getText(),"Invalid credentials");

    }


    @AfterMethod
    public void after(){
        webDriver.quit();
    }


}
