package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class KeyPressPage extends BasePage{
    private final String url;

    public KeyPressPage(WebDriver webDriver) {
        super(webDriver);
        this.url = super.baseUrl + "/key_presses";
    }

    public void visit() {
        super.webDriver.get(this.url);
    }

    public WebElement keyPressSection(){
        return super.webDriver.findElement(By.id("target"));
    }

    public WebElement result(){
        return super.webDriver.findElement(By.id("result"));
    }
}
