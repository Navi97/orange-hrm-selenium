import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;


public class OrangeProjectTest {
    private WebDriver webDriver;
    @BeforeTest
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

    @AfterTest
    public void after(){
        webDriver.quit();
    }


}
