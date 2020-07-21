package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.selenium.util.LogLog4j;

public class PgBoardsHelper extends PageBase {

    private String boardTitle;

    //--- CTOR
    public PgBoardsHelper(WebDriver driver) {
        super(driver);
    }

    public void setBoardTitle(String boardTitle) {
        LogLog4j.info("> Launching: class PgBoardsHelper, method setBoardTitle()");
        LogLog4j.info(">> Setting board title to '" + boardTitle + "' before launching tests");
        this.boardTitle = boardTitle;
    }

    public void openCurrentBoard() {
        LogLog4j.info("> Launching: class PgBoardsHelper - method openCurrentBoard()");
        LogLog4j.info(">> Searching for tile with boardTitle '" + boardTitle + "'");
        WebElement currentBoard = driver.findElement(By
                .xpath("//div[@title='" + boardTitle + "']/../.."));
        LogLog4j.info(">> Clicking on tile with boardTitle '" + boardTitle + "'");
        currentBoard.click();
    }

    @Override
    public void waitUntilPageIsLoaded() {
        LogLog4j.info("> Launching: class PgBoardsHelper - method waitUntilPageIsLoaded()");
        LogLog4j.info(">> Waiting until board tile with boardTitle " + boardTitle + " is loaded...");
        waitUntilElementIsClickable(By
                .xpath("//div[@title='" + boardTitle + "']/../.."), 60);
    }

}
