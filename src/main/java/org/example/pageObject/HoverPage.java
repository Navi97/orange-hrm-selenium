package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class HoverPage extends BasePage {
    @FindBy(xpath = "//*[@id=\"content\"]/div/div[1]" )
    public WebElement user1;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div[1]/div/a")
    public WebElement viewProfile;

    @FindBy(xpath = "//*[text()='Not Found']")
    public WebElement messageNotFound;
    public HoverPage(WebDriver webDriver) {
        super(webDriver,"/hovers");
        PageFactory.initElements(webDriver,this);
    }

    public WebElement notFoundMessage(){
        return super.wait.until(
                ExpectedConditions.visibilityOf(messageNotFound));
    }

}
