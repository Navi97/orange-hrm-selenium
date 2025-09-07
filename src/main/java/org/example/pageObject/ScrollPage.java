package org.example.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ScrollPage extends BasePage {
    private final String url;
    private Actions actions;

    public ScrollPage(WebDriver webDriver) {
        super(webDriver);
        this.url = super.baseUrl + "/infinite_scroll";
    }

    public void visit(){
        super.webDriver.get(this.url);
    }

    public Actions scrollAction(){
        return this.actions = new Actions(webDriver);
    }
}
