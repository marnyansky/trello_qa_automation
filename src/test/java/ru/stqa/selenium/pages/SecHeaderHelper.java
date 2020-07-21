package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.stqa.selenium.util.LogLog4j;

public class SecHeaderHelper extends PageBase {

    @FindBy(css = "button[data-test-id='header-boards-menu-button'] span:nth-child(2)")
    WebElement boardsHeaderButton;

    @FindBy(css = "button[data-test-id='header-member-menu-button']")
    WebElement memberMenuHeaderButton;

    //--- CTOR
    public SecHeaderHelper(WebDriver driver) {
        super(driver);
    }

    public void openMemberMenu() {
        LogLog4j.info("> Launching: class SecHeaderHelper - method openMemberMenu()");
        LogLog4j.info(">> Clicking on Member Menu header button");
        memberMenuHeaderButton.click();
    }

    public String getButtonBoardsText() {
        return boardsHeaderButton.getText();
    }

    @Override
    public void waitUntilPageIsLoaded() {
        LogLog4j.info("> Launching: class SecHeaderHelper - method waitUntilPageIsLoaded()");
        LogLog4j.info(">> Waiting until 'boardsHeaderButton' is clickable...");
        waitUntilElementIsClickable(boardsHeaderButton, 50);
        LogLog4j.info(">> Waiting until 'memberMenuHeaderButton' is clickable...");
        waitUntilElementIsClickable(memberMenuHeaderButton, 55);
    }

}
