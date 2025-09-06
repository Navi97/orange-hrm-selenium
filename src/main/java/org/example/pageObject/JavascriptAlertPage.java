package org.example.pageObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptAlertPage extends BasePage {
    private final String url;

    public JavascriptAlertPage(WebDriver webDriver) {
        super(webDriver);
        this.url = baseUrl + "/javascript_alerts";
    }

    public void visit(){
        super.webDriver.get(this.url);
    }

    public WebElement jsAlert1Section() {
        return super.webDriver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
    }

    public Alert switchToAlert() {
        return super.webDriver.switchTo().alert();
    }

    public WebElement result(){
        return super.webDriver.findElement(By.id("result"));
    }

    public WebElement jsAlert2Section() {
        return super.webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[2]/button"));
    }

    public WebElement jsAlert3Section(){
        return super.webDriver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[3]/button"));
    }
}
