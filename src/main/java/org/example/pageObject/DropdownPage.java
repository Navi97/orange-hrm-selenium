package org.example.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePage {

    @FindBy(id = "dropdown")
    public WebElement dropdown;

    public DropdownPage(WebDriver webDriver) {
        super(webDriver,"/dropdown");
        PageFactory.initElements(webDriver,this);
    }

    public String selectOptions(int index){
        Select select = new Select(dropdown);
        select.selectByIndex(index);
        return select.getFirstSelectedOption().getText();
    }
}

