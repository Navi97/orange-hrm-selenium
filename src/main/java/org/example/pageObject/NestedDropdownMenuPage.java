package org.example.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

import static utils.FileUtility.waitForFileDownload;

public class NestedDropdownMenuPage extends BasePage{
    @FindBy(id = "ui-id-3")
    public WebElement dropdownEnable;

    @FindBy(id = "ui-id-4")
    public WebElement dropdownDownload;

    @FindBy(id = "ui-id-5")
    public WebElement dropdownPdf;

    public NestedDropdownMenuPage(WebDriver webDriver) {
        super(webDriver,"/jqueryui/menu#");
        PageFactory.initElements(webDriver,this);
    }

    public WebElement dropdownDownload(){
        return super.wait.until(ExpectedConditions.visibilityOf(dropdownDownload));
    }

    public WebElement dropdownPdf(){
        return super.wait.until(ExpectedConditions.visibilityOf(dropdownPdf));
    }

    public File download(WebElement link) {
        link.click();
        String downloadPath = "/Users/vaishnavipukale/Downloads";
        return waitForFileDownload(downloadPath , 15);
    }

}
