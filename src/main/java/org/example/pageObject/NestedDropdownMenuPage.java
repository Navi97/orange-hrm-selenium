package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

import static utils.FileUtility.waitForFileDownload;

public class NestedDropdownMenuPage extends BasePage{
    private final String url;
    private final Actions actions;

    public NestedDropdownMenuPage(WebDriver webDriver) {
        super(webDriver);
        this.url = super.baseUrl + "/jqueryui/menu#";
        actions = new Actions(webDriver);
    }

    public void visit() {
        super.webDriver.get(this.url);
    }

    public WebElement dropdownEnable(){
        return super.webDriver.findElement(By.id("ui-id-3"));
    }

    public void moveToElement(WebElement webElement){
        actions.moveToElement(webElement).perform();
    }

    public WebElement dropdownDownload(){
        return super.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-4")));
    }

    public WebElement dropdownPdf(){
        return super.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ui-id-5")));
    }

    public File download(WebElement link) {
        link.click();
        String downloadPath = "/Users/vaishnavipukale/Downloads";
        return waitForFileDownload(downloadPath , 15);
    }

}
