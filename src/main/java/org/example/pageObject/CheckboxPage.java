package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxPage extends BasePage{


    public CheckboxPage(WebDriver webDriver) {
        super(webDriver,"/checkboxes");

    }

    public WebElement checkbox(int index) {
        String xpath = String.format("//*[@id=\"checkboxes\"]/input[%d]",index);
        return webDriver.findElement(By.xpath(xpath));
    }

}

