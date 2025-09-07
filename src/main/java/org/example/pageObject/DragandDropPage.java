package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragandDropPage extends BasePage{
    private String url;
    private Actions actions;

    public DragandDropPage(WebDriver webDriver) {
        super(webDriver);
        this.url = super.baseUrl + "/drag_and_drop";
    }

    public void visit(){
        super.webDriver.get(this.url);
    }

    public WebElement columnASection() {
        return super.webDriver.findElement((By.id("column-a")));
    }

    public WebElement columnBSection(){
        return super.webDriver.findElement(By.id("column-b"));
    }

    public Actions dragAndDropAction(){
        return this.actions = new Actions(webDriver);

    }
}
