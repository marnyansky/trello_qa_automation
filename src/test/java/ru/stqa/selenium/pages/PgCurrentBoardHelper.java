package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.stqa.selenium.util.LogLog4j;

import java.util.List;

public class PgCurrentBoardHelper extends PageBase {

    @FindBy(css = "div.js-list-content")
    List<WebElement> allLists;

    @FindBy(className = "placeholder")
    WebElement addListButton;

    @FindBy(className = "list-name-input")
    WebElement listTitleInputField;

    @FindBy(css = "input[type='submit']")
    WebElement submitListButton;

    @FindBy(css = "a.js-cancel-edit")
    WebElement closeButtonForList;

    @FindBy(className = "list-card-dropzone")
    List<WebElement> numberOfCards;

    @FindBy(css = "span.js-add-a-card")
    WebElement addCardButton;

    @FindBy(css = "span.js-add-another-card")
    WebElement addAnotherCardButton;

    @FindBy(css = "textarea.js-card-title")
    WebElement cardTitleInputField;

    @FindBy(css = "input.js-add-card")
    WebElement submitCardButton;

    @FindBy(css = "a.js-cancel")
    WebElement closeButtonForCard;

    private String boardTitle;

    //--- CTOR
    public PgCurrentBoardHelper(WebDriver driver) {
        super(driver);
    }

    public void setBoardTitle(String boardTitle) {
        LogLog4j.info("> Launching: class PgCurrentBoardHelper - method setBoardTitle()");
        LogLog4j.info(">> Setting board title to '" + boardTitle + "' before launching tests");
        this.boardTitle = boardTitle;
    }

    //--- get numberOf() methods
    public int getNumberOfLists() {
        return allLists.size();
    }

    public int getNumberOfCards() {
        return numberOfCards.size();
    }

    //--- list operations methods
    public PgCurrentBoardHelper createNewList() {
        LogLog4j.info("> Launching: class PgCurrentBoardHelper - method createNewList()");
        LogLog4j.info(">> Clicking on the button for adding a new list");
        addListButton.click();
        return this;
    }

    public void createNewListIfNoLists(String listTitle) {
        if (getNumberOfLists() == 0) {
            createNewList();
            setListTitle(listTitle);
            submitNewList();
            closeAutoFormForNewList();
        }
    }

    public PgCurrentBoardHelper setListTitle(String listTitle) {
        LogLog4j.info("> Launching: class PgCurrentBoardHelper - method setListTitle()");
        LogLog4j.info(">> Filling 'listTitleInputField', value '" + listTitle + "'");
        fillField(listTitleInputField, listTitle);
        return this;
    }

    public PgCurrentBoardHelper submitNewList() {
        LogLog4j.info("> Launching: class PgCurrentBoardHelper - method submitNewList()");
        LogLog4j.info(">> Clicking on the button for submitting a new list");
        submitListButton.click();
        return this;
    }

    public void closeAutoFormForNewList() {
        LogLog4j.info("> Launching: class PgCurrentBoardHelper - method closeAutoFormForNewList()");
        LogLog4j.info(">> Clicking on the close button on auto-generated form for creating a new list");
        closeButtonForList.click();

        LogLog4j.info(">> Waiting until the close button on auto-generated form " +
                "for creating a new list is not visible...");
        waitUntilElementIsNotVisible(closeButtonForList, 3);
    }

    //--- card operations methods
    public PgCurrentBoardHelper createNewCard() {
        if (addCardButton.isDisplayed()) {
            addCardButton.click();
        } else {
            addAnotherCardButton.click();
        }
        return this;
    }

    public PgCurrentBoardHelper setCardTitle(String cardTitle) {
        fillField(cardTitleInputField, cardTitle);
        return this;
    }

    public PgCurrentBoardHelper submitNewCard() {
        submitCardButton.click();
        return this;
    }

    public void closeAutoFormForNewCard() {
        closeButtonForCard.click();
        waitUntilElementIsNotVisible(closeButtonForCard, 3);
    }

    //--- wait methods
    @Override
    public void waitUntilPageIsLoaded() {
        LogLog4j.info("> Launching: class PgCurrentBoardHelper - method waitUntilPageIsLoaded()");
        LogLog4j.info(">> Waiting until the board title in the upper-left corner " +
                "of the content area is visible...");
        waitUntilElementIsVisible(By.xpath("//span[contains(text(),'" + boardTitle + "')]"), 10);

        LogLog4j.info(">> Waiting until the button for adding a list is clickable...");
        waitUntilElementIsClickable(addListButton, 10);
    }

}
