package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PgGuideHelper extends PageBase {

    @FindBy(className = "global-header-section-button")
    WebElement goToYourBoardsLink;

    //--- CTOR
    public PgGuideHelper(WebDriver driver) {
        super(driver);
    }

    public PgGuideHelper switchToGuidePage() {
        waitUntilNumberOfWindows(2, 30);
        String anotherHandle = getAnotherWindowHandle(driver.getWindowHandle());
        switchToWindow(anotherHandle);
        return this;
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(goToYourBoardsLink, 20);
    }

}
