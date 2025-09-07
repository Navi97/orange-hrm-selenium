package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class DriverManager {

    private static WebDriver driver;

    private DriverManager() {
    }

    public static WebDriver getWebdriver(){
    String property = System.getProperty("browser", "chrome");
    if(driver == null){
        driver =
                switch (property){
                    case "firefox" -> new FirefoxDriver();
                    case "safari" -> new SafariDriver();
                    default -> new ChromeDriver();
                };
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    return driver;
}
    public static void quitWebdriver() {
        if(driver != null) {
            System.out.println("Quitting driver instance");
            driver.quit();
            driver = null;
        }
    }

}


