package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.stqa.selenium.util.LogLog4j;

public class SecMemberMenuHelper extends PageBase {

    @FindBy(css = "a[data-test-id='header-member-menu-profile'] span")
    WebElement profileAndVisibilityMenuItem;

    @FindBy(xpath = "//span[contains(text(),'Activity')]")
    WebElement activityMenuItem;

    @FindBy(css = "section[data-test-id='header-member-menu-popover'] button:first-child span")
    WebElement helpMenuItem;

    @FindBy(css = ".atlaskit-portal iframe")
    WebElement helpIFrame;

    //--- CTOR
    public SecMemberMenuHelper(WebDriver driver) {
        super(driver);
    }

    //--- click-only methods
    public void openProfileAndVisibilityPage() {
        LogLog4j.info("> Launching: class SecMemberMenuHelper - method openProfileAndVisibilityPage()");
        LogLog4j.info(">> Clicking on '" + profileAndVisibilityMenuItem.getText() + "' menu item");
        profileAndVisibilityMenuItem.click();
    }

    public void openActivityPage() {
        LogLog4j.info("> Launching: class SecMemberMenuHelper - method openActivityPage()");
        LogLog4j.info(">> Clicking on '" + activityMenuItem.getText() + "' menu item");
        activityMenuItem.click();
    }

    public SecMemberMenuHelper openHelpIFrame() {
        LogLog4j.info("> Launching: class SecMemberMenuHelper - method openHelpIFrame()");
        LogLog4j.info(">> Clicking on '" + helpMenuItem.getText() + "' menu item");
        helpMenuItem.click();
        return this;
    }

    //--- wait methods
    @Override
    public void waitUntilPageIsLoaded() {
        LogLog4j.info("> Launching: class SecMemberMenuHelper - method waitUntilPageIsLoaded()");
        LogLog4j.info(">> Waiting until 'Profile and Visibility' menu item is clickable...");
        waitUntilElementIsClickable(profileAndVisibilityMenuItem, 15);
        LogLog4j.info(">> Waiting until 'Activity' menu item is clickable...");
        waitUntilElementIsClickable(activityMenuItem, 15);
        LogLog4j.info(">> Waiting until 'Help' menu item is clickable...");
        waitUntilElementIsClickable(helpMenuItem, 15);
    }

    public void waitUntilHelpIFrameIsLoadedAndSwitchToIt() {
        LogLog4j.info("> Launching: class SecMemberMenuHelper, " +
                "method waitUntilHelpIFrameIsLoadedAndSwitchToIt()");
        LogLog4j.info(">> Waiting until 'Help' iframe is loaded and switch to it...");
        waitUntilFrameIsLoadedAndSwitchToIt(helpIFrame, 15);
    }

}
