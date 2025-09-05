package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxPage {
    private final WebDriver webDriver;
    private final String url;

    public CheckboxPage(WebDriver webDriver) {
        this.url = "https://the-internet.herokuapp.com/checkboxes";
        this.webDriver = webDriver;
    }

    public void visit() {
        this.webDriver.get(this.url);
    }

    public String title() {
        return this.webDriver.getTitle();
    }

    public WebElement checkbox(int index) {
        String xpath = String.format("//*[@id=\"checkboxes\"]/input[%d]",index);
        return webDriver.findElement(By.xpath(xpath));
    }

}

