package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.PgBoardsHelper;
import ru.stqa.selenium.pages.PgLoginHelper;
import ru.stqa.selenium.pages.SecHeaderHelper;
import ru.stqa.selenium.pages.SecMemberMenuHelper;
import ru.stqa.selenium.util.DataProviders;
import ru.stqa.selenium.util.LogLog4j;

public class LoginTests extends TestBase {

    private PgLoginHelper loginPage;
    private PgBoardsHelper boardsPage;
    private SecHeaderHelper header;
    private SecMemberMenuHelper memberMenu;

    @BeforeMethod(alwaysRun = true)
    public void initTests() {
        LogLog4j.info("===== Setting up LoginTests environment - method initTests()");
        loginPage = PageFactory.initElements(driver, PgLoginHelper.class);
        boardsPage = PageFactory.initElements(driver, PgBoardsHelper.class);
        boardsPage.setBoardTitle("QA Haifa56");
        header = PageFactory.initElements(driver, SecHeaderHelper.class);
        memberMenu = PageFactory.initElements(driver, SecMemberMenuHelper.class);

        loginPage.openLoginPage()
                .waitUntilPageIsLoaded();
        LogLog4j.info("===== LoginTests environment setup complete");
    }

    @Test(groups = {"login", "regression", "smoke"})
    public void loginAtlassianTestPositive() {
        LogLog4j.startTestCase("loginAtlassianTestPositive");
        LogLog4j.info("Entering LOGIN_ATLASSIAN ('" + LOGIN_ATLASSIAN + "') " +
                "and PASSWORD_ATLASSIAN ('" + PASSWORD_ATLASSIAN + "')");
        loginPage.inputLoginAtlassianAndClickLoginAtlassian(LOGIN_ATLASSIAN)
                .inputPasswordAtlassianAndClickLogin(PASSWORD_ATLASSIAN);

        LogLog4j.info("Waiting until header of page 'Boards' is loaded...");
        header.waitUntilPageIsLoaded();

        LogLog4j.info("Performing test result verification (assert): " +
                "The text on the 'Boards' header button (icon) should be 'Boards'");
        Assert.assertEquals(header.getButtonBoardsText(),
                "Boards",
                "Error! The text on the 'Boards' " +
                        "header button (icon) is not 'Boards'!");
        LogLog4j.endTestCase();
    }

    @Test
    public void loginNoLoginNoPasswordTestNegative() {
        LogLog4j.startTestCase("loginNoLoginNoPasswordTestNegative");
        LogLog4j.info("Clicking on 'Log In' button " +
                "and waiting until error message for no login is visible");
        loginPage.clickLoginButton()
                .waitUntilErrorMessageForNoLogin();

        LogLog4j.info("Performing test result verification (assert): " +
                "The actual error message for no login corresponds " +
                "to the expected error message");
        Assert.assertEquals(loginPage.getErrorMessageForNoLogin(),
                "Missing email",
                "Error! The text of the message is not 'Missing email'!");
        LogLog4j.endTestCase();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderFirst")
    public void loginLoginErrorTestNegativeDp1(
            String login, String password, String message) {
        LogLog4j.startTestCase("loginLoginErrorTestNegativeDp1: '"
                + login + "', '" + password + "', '" + message + "'");
        LogLog4j.info("Logging in to the system: '" + login + "', '" + password + "'");
        loginPage.inputLogin(login)
                .inputPassword(password)
                .clickLoginButton()
                .waitUntilErrorMessageForLoginError();

        LogLog4j.info("Performing test result verification (assert): " +
                "The actual error message for login error corresponds " +
                "to the expected error message ('" + message + "')");
        Assert.assertEquals(loginPage.getErrorMessageForLoginError(),
                message,
                "Error! The error message for login error is incorrect!");
        LogLog4j.endTestCase();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderSecond")
    public void loginLoginErrorTestNegativeDp2(
            String login, String password, String message) {
        LogLog4j.startTestCase("loginLoginErrorTestNegativeDp2: '"
                + login + "', '" + password + "', '" + message + "'");
        LogLog4j.info("Logging in to the system: '" + login + "', '" + password + "'");
        loginPage.inputLogin(login)
                .inputPassword(password)
                .clickLoginButton()
                .waitUntilErrorMessageForLoginError();

        LogLog4j.info("Performing test result verification (assert): " +
                "The actual error message for login error corresponds " +
                "to the expected error message ('" + message + "')");
        Assert.assertEquals(loginPage.getErrorMessageForLoginError(),
                message,
                "Error! The error message for login error is incorrect!");
        LogLog4j.endTestCase();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderThird")
    public void loginLoginErrorTestNegativeDp3(String login, String password) {
        LogLog4j.startTestCase("loginLoginErrorTestNegativeDp3: '"
                + login + "', '" + password + "'");
        LogLog4j.info("Logging in to the system: '" + login + "', '" + password + "'");
        loginPage.inputLogin(login)
                .inputPassword(password)
                .clickLoginButton()
                .waitUntilErrorMessageForLoginError();

        LogLog4j.info("Performing test result verification (assert): " +
                "The actual error message for login error corresponds " +
                "to the expected error message");
        Assert.assertEquals(loginPage.getErrorMessageForLoginError(),
                "There isn't an account for this email",
                "The error message for login error is incorrect!");
        LogLog4j.endTestCase();
    }

    @Test(dataProviderClass = DataProviders.class,
            dataProvider = "DpNegativePasswordIncorrect")
    public void loginNonValidPasswordTestNegativeDp(String login, String password) {
        LogLog4j.startTestCase("loginNonValidPasswordTestNegativeDp: '"
                + login + "', '" + password + "'");
        LogLog4j.info("Logging in to the system: '" + login + "', '" + password + "'");
        loginPage.loginAsAtlassian(login, password)
                .waitUntilErrorMessageForNonValidPassword();

        LogLog4j.info("Performing test result verification (assert): " +
                "The actual error message for non-valid password corresponds " +
                "to the expected error message");
        Assert.assertTrue(loginPage.getErrorMessageForNonValidPassword()
                        .contains("Incorrect email address and / or password."),
                "Error! No error message displayed or it's text is incorrect!");
        LogLog4j.endTestCase();
    }

}
