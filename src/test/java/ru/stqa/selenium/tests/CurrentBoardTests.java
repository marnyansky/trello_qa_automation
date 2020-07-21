package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.PgBoardsHelper;
import ru.stqa.selenium.pages.PgCurrentBoardHelper;
import ru.stqa.selenium.pages.PgLoginHelper;
import ru.stqa.selenium.util.DataProviders;

public class CurrentBoardTests extends TestBase {

    //TODO logging

    private PgLoginHelper loginPage;
    private PgBoardsHelper boardsPage;
    private PgCurrentBoardHelper currentBoardPage;

    @BeforeMethod
    public void initTests() {
        loginPage = PageFactory.initElements(driver, PgLoginHelper.class);
        boardsPage = PageFactory.initElements(driver, PgBoardsHelper.class);
        currentBoardPage = PageFactory.initElements(driver, PgCurrentBoardHelper.class);

        boardsPage.setBoardTitle("QA Haifa56");
        currentBoardPage.setBoardTitle("QA Haifa56");

        loginPage.openLoginPage()
                .waitUntilPageIsLoaded();
        loginPage.loginAsAtlassian(LOGIN_ATLASSIAN, PASSWORD_ATLASSIAN);

        boardsPage.waitUntilPageIsLoaded();
        boardsPage.openCurrentBoard();

        currentBoardPage.waitUntilPageIsLoaded();
    }

    @Test(groups = "SmokeTesting")
    public void createNewListTest() {
        int initialNumberOfLists = currentBoardPage.getNumberOfLists();

        currentBoardPage.createNewList()
                .setListTitle("SeleniumList")
                .submitNewList()
                .closeAutoFormForNewList();

        int newNumberOfLists = currentBoardPage.getNumberOfLists();

        Assert.assertEquals(initialNumberOfLists + 1, newNumberOfLists,
                "Test failed: calculation error and/or list creation error");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderFifth")
    public void createNewCardTest(String randomCardTitle) {
        currentBoardPage.createNewListIfNoLists("SeleniumList with cards");

        int initialNumberOfCards = currentBoardPage.getNumberOfCards();

        currentBoardPage.createNewCard()
                .setCardTitle(randomCardTitle)
                .submitNewCard()
                .closeAutoFormForNewCard();

        int newNumberOfCards = currentBoardPage.getNumberOfCards();

        Assert.assertEquals(initialNumberOfCards + 1, newNumberOfCards,
                "Test failed: calculation error and/or card creation error");
    }

}
