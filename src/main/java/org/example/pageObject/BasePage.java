package org.example.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriverWait wait;
    protected WebDriver webDriver;
    protected String baseUrl ;

    public BasePage(WebDriver webDriver) {
        this.baseUrl = "https://the-internet.herokuapp.com" ;
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
    }

    public BasePage(WebDriver webDriver, String path) {
        this.baseUrl = "https://the-internet.herokuapp.com" + path;
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(10));
    }


    public String title(){
        return this.webDriver.getTitle();
    }

    public void visit() {
        this.webDriver.get(this.baseUrl);
    }
}
