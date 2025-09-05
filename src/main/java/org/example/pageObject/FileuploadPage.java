package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.File;

public class FileuploadPage {
    private final WebDriver webDriver;
    private String url;

    public FileuploadPage(WebDriver webDriver) {
        this.url ="https://the-internet.herokuapp.com/upload";
        this.webDriver = webDriver;

    }

    public void visit() {
        this.webDriver.get(this.url);
    }

    public void upload(File file) {
        this.select(file);
        this.submit().click();
    }

    public WebElement uploadFileSection() {
        return webDriver.findElement(By.id("uploaded-files"));
    }

    private WebElement submit() {
        return webDriver.findElement(By.id("file-submit"));
    }

    private void select(File file) {
        webDriver.findElement(By.id("file-upload")).sendKeys(file.getAbsolutePath());
    }
}
