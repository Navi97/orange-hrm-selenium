package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class FileuploadPage extends BasePage {
    @FindBy(id = "uploaded-files")
    public WebElement uploadFileSection;

    @FindBy(id = "file-submit")
    public WebElement submit;

    public FileuploadPage(WebDriver webDriver) {
      super(webDriver,"/upload");
        PageFactory.initElements(webDriver,this);

    }

    public void upload(File file) {
        this.select(file);
        this.submit.click();
    }

    private void select(File file) {
        webDriver.findElement(By.id("file-upload")).sendKeys(file.getAbsolutePath());
    }
}
