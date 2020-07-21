package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.stqa.selenium.util.LogLog4j;

public class PgLoginHelper extends PageBase {

    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(id = "login-submit")
    WebElement loginSubmitButton;

    @FindBy(id = "user")
    WebElement loginField;

    @FindBy(css = "a.text-white")
    WebElement loginLink;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(css = "div#error>p")
    WebElement errorMessageForNoLogin;

    @FindBy(css = ".error-message:first-child")
    WebElement errorMessageForLoginError;

    @FindBy(css = "div#login-error>span")
    WebElement errorMessageForNonValidPassword;

    //--- CTOR
    public PgLoginHelper(WebDriver driver) {
        super(driver);
    }

    public PgLoginHelper openLoginPage() {
        LogLog4j.info("> Launching: class PgLoginHelper - method openLoginPage()");
        LogLog4j.info(">> Clicking on 'loginLink'");
        loginLink.click();
        return this;
    }

    @Override
    public void waitUntilPageIsLoaded() {
        LogLog4j.info("> Launching: class PgLoginHelper - method waitUntilPageIsLoaded()");
        LogLog4j.info(">> Waiting until 'loginButton' is clickable...");
        waitUntilElementIsClickable(loginButton, 10);
    }

    public PgLoginHelper clickLoginButton() {
        LogLog4j.info("> Launching: class PgLoginHelper - method clickLoginButton()");
        LogLog4j.info(">> Clicking on 'loginButton'");
        loginButton.click();
        return this;
    }

    //--- login input methods
    public PgLoginHelper inputLoginAtlassianAndClickLoginAtlassian(String loginAtlassian) {
        LogLog4j.info("> Launching: class PgLoginHelper - " +
                "method inputLoginAtlassianAndClickLoginAtlassian()");
        LogLog4j.info(">> Filling 'loginField', value '" + loginAtlassian + "'");
        fillField(loginField, loginAtlassian);

        LogLog4j.info(">> Waiting until the text on 'loginButton' is 'Log in with Atlassian'...");
        waitUntilAttributeValueIs(loginButton,
                "value", "Log in with Atlassian", 3);
        LogLog4j.info(">> Clicking on 'loginButton'");
        clickLoginButton();

        LogLog4j.info(">> Waiting until 'loginSubmitButton' is clickable...");
        waitUntilElementIsClickable(loginSubmitButton, 10);
        return this;
    }

    public PgLoginHelper inputIncorrectLoginAndClickLogin(String incorrectLogin)
            throws InterruptedException {
        fillField(loginField, incorrectLogin);
        Thread.sleep(500); // wait time for login evaluation by Trello
        clickLoginButton();
        return this;
    }

    public PgLoginHelper inputLogin(String login) {
        LogLog4j.info("> Launching: class PgLoginHelper - method inputLogin()");
        LogLog4j.info(">> Filling 'loginField', value '" + login + "'");
        fillField(loginField, login);
        return this;
    }

    //--- password input methods
    public void inputPasswordAtlassianAndClickLogin(String passwordAtlassian) {
        LogLog4j.info("> Launching: class PgLoginHelper - " +
                "method inputPasswordAtlassianAndClickLogin()");
        LogLog4j.info(">> Filling 'passwordField', value '" + passwordAtlassian + "'");
        fillField(passwordField, passwordAtlassian);
        LogLog4j.info(">> Clicking on 'loginSubmitButton'");
        loginSubmitButton.click();
    }

    public PgLoginHelper inputPassword(String password) {
        LogLog4j.info("> Launching: class PgLoginHelper - method inputPassword()");
        LogLog4j.info(">> Filling 'passwordField', value '" + password + "'");
        fillField(passwordField, password);
        return this;
    }

    //--- combined login/password input methods
    public PgLoginHelper loginAsAtlassian(String loginAtlassian, String passwordAtlassian) {
        LogLog4j.info("> Launching: class PgLoginHelper - method loginAsAtlassian()");
        LogLog4j.info(">> Launching method inputLoginAtlassianAndClickLoginAtlassian() "
                + ", loginAtlassian value '" + loginAtlassian + "'");
        this.inputLoginAtlassianAndClickLoginAtlassian(loginAtlassian);
        LogLog4j.info(">> Launching method inputPasswordAtlassianAndClickLogin() "
                + ", passwordAtlassian value '" + passwordAtlassian + "'");
        this.inputPasswordAtlassianAndClickLogin(passwordAtlassian);
        return this;
    }

    //--- wait for error message methods
    public void waitUntilErrorMessageForNoLogin() {
        waitUntilElementIsVisible(errorMessageForNoLogin, 3);
    }

    public void waitUntilErrorMessageForLoginError() {
        LogLog4j.info("> Launching: class PgLoginHelper - method waitUntilErrorMessageForLoginError()");
        LogLog4j.info(">> Waiting until error message for login error is visible...");
        waitUntilElementIsVisible(errorMessageForLoginError, 3);
    }

    public void waitUntilErrorMessageForNonValidPassword() {
        waitUntilElementIsVisible(errorMessageForNonValidPassword, 5);
    }

    //--- get error message methods
    public String getErrorMessageForNoLogin() {
        return errorMessageForNoLogin.getText();
    }

    public String getErrorMessageForLoginError() {
        return errorMessageForLoginError.getText();
    }

    public String getErrorMessageForNonValidPassword() {
        return errorMessageForNonValidPassword.getText();
    }

}
