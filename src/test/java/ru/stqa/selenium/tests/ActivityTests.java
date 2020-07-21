package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;
import ru.stqa.selenium.util.DataProviders;
import ru.stqa.selenium.util.LogLog4j;

public class ActivityTests extends TestBase {

    private PgLoginHelper loginPage;
    private PgBoardsHelper boardsPage;
    private PgCurrentBoardHelper currentBoardPage;
    private SecHeaderHelper header;
    private SecMemberMenuHelper memberMenu;
    private PgActivityHelper activityPage;

    @BeforeMethod
    public void initTests() {
        LogLog4j.info("===== Setting up ActivityTests environment - method initTests()");
        loginPage = PageFactory.initElements(driver, PgLoginHelper.class);
        boardsPage = PageFactory.initElements(driver, PgBoardsHelper.class);
        currentBoardPage = PageFactory.initElements(driver, PgCurrentBoardHelper.class);
        header = PageFactory.initElements(driver, SecHeaderHelper.class);
        memberMenu = PageFactory.initElements(driver, SecMemberMenuHelper.class);
        activityPage = PageFactory.initElements(driver, PgActivityHelper.class);

        boardsPage.setBoardTitle("QA Haifa56");
        currentBoardPage.setBoardTitle("QA Haifa56");

        loginPage.openLoginPage()
                .waitUntilPageIsLoaded();
        loginPage.loginAsAtlassian(LOGIN_ATLASSIAN, PASSWORD_ATLASSIAN);

        boardsPage.waitUntilPageIsLoaded();
        boardsPage.openCurrentBoard();

        currentBoardPage.waitUntilPageIsLoaded();
        LogLog4j.info("===== ActivityTests environment setup complete");
    }

    @Test(groups = "SmokeTesting",
            dataProviderClass = DataProviders.class, dataProvider = "dataProviderFourth")
    public void newEventIsInActivityTabTest(String listTitle) {
        LogLog4j.startTestCase("newEventIsInActivityTabTest: '"
                + listTitle + "'");
        LogLog4j.info("Creating a new list: '" + listTitle + "'");
        currentBoardPage.createNewList()
                .setListTitle(listTitle)
                .submitNewList()
                .closeAutoFormForNewList();

        LogLog4j.info("Navigating to 'Activity' page");
        header.openMemberMenu();
        memberMenu.waitUntilPageIsLoaded();
        memberMenu.openActivityPage();
        activityPage.waitUntilPageIsLoaded();

        LogLog4j.info("Performing test result verification (assert): " +
                "The latest activity record contains phrase" +
                "' added list " + listTitle + " to '");
        Assert.assertTrue(activityPage.getLatestActivityRecordText()
                        .contains(" added list " + listTitle + " to "),
                "Error! The text in the latest activity record doesn't correspond " +
                        "to adding a new list '" + listTitle + "'");
        LogLog4j.endTestCase();
    }

}
