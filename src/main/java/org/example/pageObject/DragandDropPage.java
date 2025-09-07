package org.example.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DragandDropPage extends BasePage{
    @FindBy(id = "column-a")
    public WebElement columnA;

    @FindBy(id = "column-b")
    public WebElement columnB;

    public DragandDropPage(WebDriver webDriver) {
        super(webDriver,"/drag_and_drop");
        PageFactory.initElements(webDriver,this);

    }

}
