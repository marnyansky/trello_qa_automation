package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;
import ru.stqa.selenium.util.LogLog4j;

public class ProfileTests extends TestBase {

    PgLoginHelper loginPage;
    PgBoardsHelper boardsPage;
    PgProfileHelper profilePage;
    SecHeaderHelper header;
    SecMemberMenuHelper memberMenu;

    @BeforeMethod
    public void initTests() {
        LogLog4j.info("===== Setting up ProfileTests environment - method initTests()");
        loginPage = PageFactory.initElements(driver, PgLoginHelper.class);
        boardsPage = PageFactory.initElements(driver, PgBoardsHelper.class);
        profilePage = PageFactory.initElements(driver, PgProfileHelper.class);
        header = PageFactory.initElements(driver, SecHeaderHelper.class);
        memberMenu = PageFactory.initElements(driver, SecMemberMenuHelper.class);

        loginPage.openLoginPage()
                .waitUntilPageIsLoaded();
        loginPage.loginAsAtlassian(LOGIN_ATLASSIAN, PASSWORD_ATLASSIAN);
        header.waitUntilPageIsLoaded();
        header.openMemberMenu();
        memberMenu.waitUntilPageIsLoaded();
        memberMenu.openProfileAndVisibilityPage();
        profilePage.waitUntilPageIsLoaded();
        LogLog4j.info("===== ProfileTests environment setup complete");
    }

    @Test
    public void verifyLabelTextTest() {
        LogLog4j.startTestCase("verifyLabelTextTest");
        LogLog4j.info("Performing test result verification (assert): " +
                "The abbreviation on the Member Menu header button should be " +
                "equal to the abbreviation in the circle under the page header bar");
        Assert.assertEquals(profilePage.getLabelTextFromRightUpperButton(),
                profilePage.getLabelTextFromMiddleCircle(),
                "Error! The abbreviation on the member menu button " +
                        "doesn't match the abbreviation inside the circle " +
                        "located next to the profile name");
        LogLog4j.endTestCase();
    }

    @Test(groups = "SmokeTesting")
    public void verifyUserNameTest() {
        LogLog4j.startTestCase("verifyUserNameTest");
        LogLog4j.info("Performing test result verification (assert): " +
                "The username (excluding '@' char) under the page header bar should be " +
                "equal to the pre-filled username in 'Username' field " +
                "located in 'About' section in the lower part of the page");
        Assert.assertEquals(profilePage.getMiddleUserName(),
                profilePage.getLowerUserName(),
                "Error! The username in the middle part of the page " +
                        "doesn't match the username in the lower part of the page");
        LogLog4j.endTestCase();
    }

}
