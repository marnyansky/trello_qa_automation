package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.stqa.selenium.util.LogLog4j;

import static ru.stqa.selenium.tests.TestBase.USERNAME;

public class PgProfileHelper extends PageBase {

    @FindBy(css = "div[data-test-id='profile-tab-container'] button")
    WebElement saveButton;

    @FindBy(css = "button.js-open-header-member-menu span")
    WebElement rightUpperAbbreviationOnMemberMenuButton;

    @FindBy(css = "div.js-header-wrapper span:first-child")
    WebElement middleAbbreviationOnCircleNextToName;

    @FindBy(css = "div.tabbed-pane-header span:nth-child(2)")
    WebElement middleUserName;

    //--- CTOR
    public PgProfileHelper(WebDriver driver) {
        super(driver);
    }

    //--- get label text methods
    public String getLabelTextFromRightUpperButton() {
        return rightUpperAbbreviationOnMemberMenuButton.getText();
    }

    public String getLabelTextFromMiddleCircle() {
        return middleAbbreviationOnCircleNextToName.getText();
    }

    //--- get username methods
    public String getMiddleUserName() {
        return middleUserName.getText().substring(1); // .substring(1) allows to ignore '@' character
    }

    public String getLowerUserName() {
        WebElement lowerUserName = driver.findElement(By
                .xpath("//input[@value='" + USERNAME + "']"));
        return lowerUserName.getAttribute("value");
    }

    //--- wait methods
    @Override
    public void waitUntilPageIsLoaded() {
        LogLog4j.info("> Launching: class PgProfileHelper - method waitUntilPageIsLoaded()");
        LogLog4j.info(">> Waiting until button 'Save' in the lower part of the page is clickable...");
        waitUntilElementIsClickable(saveButton, 10);
    }

}
