package org.example.pageObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BrowserAlertPage extends BasePage {
    @FindBy(id = "hot-spot")
    public WebElement box;

    public BrowserAlertPage(WebDriver webDriver) {
        super(webDriver ,"/context_menu");
        PageFactory.initElements(webDriver,this);
    }

    public void rightClickBox() {
        Actions actions = new Actions(super.webDriver);
        actions.contextClick(box).perform();
        super.wait.until(ExpectedConditions.alertIsPresent());
    }

    public Alert switchToAlert(){
        return super.webDriver.switchTo().alert();
    }

    public void verifyAlertInvisible(){
        super.wait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
    }

}
