package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.PgBoardsHelper;
import ru.stqa.selenium.pages.PgCurrentBoardHelper;
import ru.stqa.selenium.pages.PgLoginHelper;
import ru.stqa.selenium.util.DataProviders;
import ru.stqa.selenium.util.LogLog4j;

public class CurrentBoardTests extends TestBase {

    private PgLoginHelper loginPage;
    private PgBoardsHelper boardsPage;
    private PgCurrentBoardHelper currentBoardPage;

    @BeforeMethod(alwaysRun = true)
    public void initTests() {
        LogLog4j.info("===== Setting up CurrentBoardTests environment - method initTests()");
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
        LogLog4j.info("===== CurrentBoardTests environment setup complete");
    }

    @Test(groups = {"current_board", "regression", "smoke"})
    public void createNewListTest() {
        LogLog4j.startTestCase("createNewListTest");
        LogLog4j.info("Getting initial number of lists in the current board");
        int initialNumberOfLists = currentBoardPage.getNumberOfLists();

        LogLog4j.info("Creating a new list in the current board");
        currentBoardPage.createNewList()
                .setListTitle("SeleniumList")
                .submitNewList()
                .closeAutoFormForNewList();

        LogLog4j.info("Getting new number of lists in the current board");
        int newNumberOfLists = currentBoardPage.getNumberOfLists();

        LogLog4j.info("Performing test result verification (assert): " +
                "The initial number of lists plus 1 list should be equal " +
                "to the new number of lists");
        Assert.assertEquals(initialNumberOfLists + 1, newNumberOfLists,
                "Test failed: calculation error and/or list creation error");
        LogLog4j.endTestCase();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderFifth")
    public void createNewCardTest(String randomCardTitle) {
        LogLog4j.startTestCase("createNewCardTest");
        LogLog4j.info("Creating a new list if there is no lists in the current board");
        currentBoardPage.createNewListIfNoLists("SeleniumList with cards");

        LogLog4j.info("Getting initial number of cards in the first list");
        int initialNumberOfCards = currentBoardPage.getNumberOfCards();

        LogLog4j.info("Creating a new card in the first list");
        currentBoardPage.createNewCard()
                .setCardTitle(randomCardTitle)
                .submitNewCard()
                .closeAutoFormForNewCard();

        LogLog4j.info("Getting new number of cards in the first list");
        int newNumberOfCards = currentBoardPage.getNumberOfCards();

        LogLog4j.info("Performing test result verification (assert): " +
                "The initial number of cards plus 1 list should be equal " +
                "to the new number of cards");
        Assert.assertEquals(initialNumberOfCards + 1, newNumberOfCards,
                "Test failed: calculation error and/or card creation error");
        LogLog4j.endTestCase();
    }

}
