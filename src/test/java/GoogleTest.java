import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class GoogleTest {
    @Test
    public void openGoogle(){
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.google.com");
        System.out.println(webDriver.getTitle());
        webDriver.quit();
    }


}
