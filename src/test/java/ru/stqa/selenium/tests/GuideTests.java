package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;
import ru.stqa.selenium.util.LogLog4j;

public class GuideTests extends TestBase {

    //TODO run on server

    private PgLoginHelper loginPage;
    private SecHeaderHelper header;
    private SecMemberMenuHelper memberMenu;
    private SecHelpHelper helpSection;
    private PgGuideHelper guidePage;

    @BeforeMethod
    public void initTests() {
        LogLog4j.info("===== Setting up GuideTests environment - method initTests()");
        loginPage = PageFactory.initElements(driver, PgLoginHelper.class);
        header = PageFactory.initElements(driver, SecHeaderHelper.class);
        memberMenu = PageFactory.initElements(driver, SecMemberMenuHelper.class);
        helpSection = PageFactory.initElements(driver, SecHelpHelper.class);
        guidePage = PageFactory.initElements(driver, PgGuideHelper.class);

        loginPage.openLoginPage()
                .waitUntilPageIsLoaded();
        loginPage.loginAsAtlassian(LOGIN_ATLASSIAN, PASSWORD_ATLASSIAN);

        header.waitUntilPageIsLoaded();
        header.openMemberMenu();

        memberMenu.waitUntilPageIsLoaded();
        memberMenu.openHelpIFrame()
                .waitUntilHelpIFrameIsLoadedAndSwitchToIt();

        helpSection.waitUntilPageIsLoaded();
        helpSection.openGuidePage();
        LogLog4j.info("===== GuideTests environment setup complete");
    }


    @Test
    public void verifyGuidePageTitleTest() {
        LogLog4j.startTestCase("verifyGuidePageTitleTest");
        LogLog4j.info("Switching to 'Getting Started Guide' page and waiting until it is loaded");
        guidePage.switchToGuidePage()
                .waitUntilPageIsLoaded();

        LogLog4j.info("Performing test result verification (assert): " +
                "The actual title of the page corresponds to " +
                "its expected title ('Getting Started with Trello')");
        Assert.assertEquals(guidePage.getTitle(), "Getting Started with Trello",
                "Error! Actual title doesn't correspond to expected title");
        LogLog4j.endTestCase();
    }

}
