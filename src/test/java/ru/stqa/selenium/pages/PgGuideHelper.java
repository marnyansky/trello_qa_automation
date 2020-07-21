package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.stqa.selenium.util.LogLog4j;

public class PgGuideHelper extends PageBase {

    @FindBy(className = "global-header-section-button")
    WebElement goToYourBoardsLink;

    //--- CTOR
    public PgGuideHelper(WebDriver driver) {
        super(driver);
    }

    public PgGuideHelper switchToGuidePage() {
        LogLog4j.info("> Launching: class PgGuideHelper - method switchToGuidePage()");
        LogLog4j.info(">> Waiting until number of windows will be 2...");
        waitUntilNumberOfWindows(2, 30);
        LogLog4j.info(">> Getting handle (unique identifier) of another window");
        String anotherHandle = getAnotherWindowHandle(driver.getWindowHandle());
        LogLog4j.info(">> Switching to another window by handle '" + anotherHandle + "'");
        switchToWindow(anotherHandle);
        return this;
    }

    @Override
    public void waitUntilPageIsLoaded() {
        LogLog4j.info("> Launching: class PgGuideHelper - method waitUntilPageIsLoaded()");
        LogLog4j.info(">> Waiting until 'Go to your boards' link is clickable...");
        waitUntilElementIsClickable(goToYourBoardsLink, 20);
    }

}
