package org.example.pageObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BrowserAlertPage extends BasePage {
    private String url;

    public BrowserAlertPage(WebDriver webDriver) {
        super(webDriver);
        this.url = super.baseUrl + "/context_menu";
    }

    public void visit() {
        super.webDriver.get(this.url);
    }

    public void rightClickBox() {
        WebElement element = super.webDriver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(super.webDriver);
        actions.contextClick(element).perform();
        super.wait.until(ExpectedConditions.alertIsPresent());
    }

    public Alert switchToAlert(){
        return super.webDriver.switchTo().alert();
    }

    public void verifyAlertInvisible(){
        super.wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
    }

}
