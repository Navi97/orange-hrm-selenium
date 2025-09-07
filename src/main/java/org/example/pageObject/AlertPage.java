package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AlertPage extends BasePage{
    @FindBy(id = "restart-ad")
    public WebElement clickHereButton;
    @FindBy(id = "modal")
    private WebElement modal;
    @FindBy(xpath = "//*[@id='modal']/div[2]/div[3]/p")
    public WebElement closeButton;

    public AlertPage(WebDriver webDriver) {
        super(webDriver, "/entry_ad");
        PageFactory.initElements(webDriver,this);
    }

    public void modalVisible(){
        super.wait.until(ExpectedConditions.visibilityOf(modal));
    }

    public Boolean modalInvisible(){
        return super.wait.until(ExpectedConditions.invisibilityOf(modal));
    }

}
