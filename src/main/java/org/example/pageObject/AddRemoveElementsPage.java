package org.example.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddRemoveElementsPage extends BasePage{
    @FindBy(xpath ="//*[@id=\"content\"]/div/button" )
    public WebElement addButton;

    @FindBy(xpath = "//*[@id=\"elements\"]/button")
    public WebElement removeButton;

    public AddRemoveElementsPage(WebDriver webDriver) {
        super(webDriver,"/add_remove_elements/");
        PageFactory.initElements(webDriver,this);
    }

    public boolean isInvisible(WebElement webElement){
        WebDriverWait wait = new WebDriverWait(super.webDriver, Duration.ofSeconds(1));
        return wait.until(
                ExpectedConditions.invisibilityOf(webElement)
        );
    }


}
