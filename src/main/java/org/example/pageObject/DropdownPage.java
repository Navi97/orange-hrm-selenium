package org.example.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage {
    private String url;
    private WebDriver webDriver;

    public DropdownPage(WebDriver webDriver) {
        this.url = "https://the-internet.herokuapp.com/dropdown";
        this.webDriver = webDriver;
    }
    public void visit(){
        this.webDriver.get(this.url);
    }
    public String title(){
        return this.webDriver.getTitle();
    }
    public WebElement dropdown(){
        return this.webDriver.findElement(By.id("dropdown"));
    }
    public String selectOptions(int index){
        WebElement dropdown = this.dropdown();
        Select select = new Select(dropdown);
        select.selectByIndex(index);
        return select.getFirstSelectedOption().getText();
    }
}

