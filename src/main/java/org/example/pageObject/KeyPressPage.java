package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KeyPressPage extends BasePage{

    @FindBy(id = "target")
    public WebElement keyPressSection;

    @FindBy(id = "result")
    public WebElement result;

    public KeyPressPage(WebDriver webDriver) {
        super(webDriver,"/key_presses");
        PageFactory.initElements(webDriver,this);
    }

}
