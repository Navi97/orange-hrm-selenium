package org.example.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JavascriptAlertPage extends BasePage {

    @FindBy(xpath = "//button[text()='Click for JS Alert']")
    public WebElement jsAlert1Section;

    @FindBy(id = "result")
    public WebElement result;

    @FindBy(xpath = "//*[@id=\"content\"]/div/ul/li[2]/button")
    public WebElement jsAlert2Section;

    @FindBy(xpath = "//*[@id=\"content\"]/div/ul/li[3]/button")
    public WebElement jsAlert3Section;

    public JavascriptAlertPage(WebDriver webDriver) {
        super(webDriver,"/javascript_alerts");
        PageFactory.initElements(webDriver,this);
    }

}
