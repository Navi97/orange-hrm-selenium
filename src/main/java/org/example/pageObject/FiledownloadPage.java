package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

import static utils.FileUtility.waitForFileDownload;

public class FiledownloadPage {
    private final WebDriver webDriver;
    private String url;

    public FiledownloadPage(WebDriver webDriver) {
        this.url = "https://the-internet.herokuapp.com/download";
        this.webDriver = webDriver;
    }

    public void visit() {
        this.webDriver.get(this.url);
    }

    public WebElement link(int index) {
        String format = String.format("//*[@id=\"content\"]/div/a[%d]", index);
        By xpath = By.xpath(format);
        return webDriver.findElement(xpath);
    }


    public File download(WebElement link) {
        link.click();
        String downloadPath = "/Users/vaishnavipukale/Downloads";
        return waitForFileDownload(downloadPath , 15);
    }
}
