package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AlertPage extends BasePage{
    private String url;

    public AlertPage(WebDriver webDriver) {
        super(webDriver);
        this.url = super.baseUrl + "/entry_ad";
    }

    public void visit() {
        super.webDriver.get(this.url);
    }

    public WebElement clickHereButton() {
        return super.webDriver.findElement(By.id("restart-ad"));
    }

    public void modalVisible(){
        super.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal")));
    }

    public WebElement closeButton(){
        return super.webDriver.findElement(By.xpath("//*[@id='modal']/div[2]/div[3]/p"));
    }

    public Boolean modalInvisible(){
        return super.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modal")));
    }

}
