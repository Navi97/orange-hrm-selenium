package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HoverPage extends BasePage {
    private final String url;
    public HoverPage(WebDriver webDriver) {
        super(webDriver);
        this.url = super.baseUrl + "/hovers";
    }

    public void visit() {
        super.webDriver.get(this.url);
    }

    public void hoverOnUser1() {
        WebElement element = super.webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element).perform();
    }

    public WebElement viewProfile() {
        return super.webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/div/a"));
    }

    public WebElement notFoundMessage(){
        return super.wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Not Found']")));
    }


}
