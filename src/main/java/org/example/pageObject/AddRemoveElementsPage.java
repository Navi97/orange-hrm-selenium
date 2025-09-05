package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddRemoveElementsPage {
    private String url;
    private WebDriver webDriver;


    public AddRemoveElementsPage(WebDriver webDriver) {
        this.url = "https://the-internet.herokuapp.com/add_remove_elements/";
        this.webDriver = webDriver;
    }

    public void visit(){
        this.webDriver.get(this.url);
    }

    public String title(){
        return this.webDriver.getTitle();
    }
    public WebElement addButton(){
        return this.webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));
    }
    public WebElement removeButton(){
        return this.webDriver.findElement(this.removeButtonLocator());
    }

    public By removeButtonLocator() {
        return By.xpath("//*[@id=\"elements\"]/button");
    }

    public boolean isInvisible(By locator){
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(3));
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator)
        );
    }


}
