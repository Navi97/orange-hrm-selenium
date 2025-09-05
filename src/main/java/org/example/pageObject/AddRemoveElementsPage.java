package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddRemoveElementsPage extends BasePage{
    private final String url;

    public AddRemoveElementsPage(WebDriver webDriver) {
        super(webDriver);
        this.url = super.baseUrl + "/add_remove_elements/";
    }

    public void visit(){
        this.webDriver.get(this.url);
    }

    public WebElement addButton(){
        return super.webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));
    }
    public WebElement removeButton(){
        return super.webDriver.findElement(this.removeButtonLocator());
    }

    public By removeButtonLocator() {
        return By.xpath("//*[@id=\"elements\"]/button");
    }

    public boolean isInvisible(By locator){
        WebDriverWait wait = new WebDriverWait(super.webDriver, Duration.ofSeconds(1));
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator)
        );
    }


}
