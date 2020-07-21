package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.stqa.selenium.util.LogLog4j;

public class SecHelpHelper extends PageBase {

    @FindBy(xpath = "//a[contains(text(),'Getting Started Guide')]")
    WebElement guideItem;

    //--- CTOR
    public SecHelpHelper(WebDriver driver) {
        super(driver);
    }

    public void openGuidePage() {
        LogLog4j.info("> Launching: class SecHelpHelper - method openGuidePage()");
        LogLog4j.info(">> Clicking on 'Getting Started Guide' menu item");
        guideItem.click();
    }

    @Override
    public void waitUntilPageIsLoaded() {
        LogLog4j.info("> Launching: class SecHelpHelper - method waitUntilPageIsLoaded()");
        LogLog4j.info(">> Waiting until 'Getting Started Guide' menu item is clickable...");
        waitUntilElementIsClickable(guideItem, 15);
    }

}
