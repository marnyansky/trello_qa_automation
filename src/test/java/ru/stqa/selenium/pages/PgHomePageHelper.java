package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.stqa.selenium.util.LogLog4j;

import java.util.List;

public class PgHomePageHelper extends PageBase {

    @FindBy(css = "a.text-white")
    WebElement loginLink;

    @FindBy(css = "button.px-4")
    WebElement signUpItsFreeButton;

    @FindBy(id = "language-picker")
    WebElement languageSelect;

    @FindBy(css = "a[data-analytics-link='aboutFooterLink']")
    WebElement aboutLinkInFooter;

    @FindBy(className = "global-footer-list-item")
    List<WebElement> footerLinksList;

    //--- CTOR
    public PgHomePageHelper(WebDriver driver) {
        super(driver);
    }

    public String getTextOfAboutLinkInFooter() {
        LogLog4j.info("> Launching: class PgHomePageHelper - " +
                "method getTextOfAboutLinkInFooter()");
        LogLog4j.info(">> Getting text of 'About' link in the footer...");
        return aboutLinkInFooter.getText();
    }

    public void scrollToFooter() {
        LogLog4j.info("> Launching: class PgHomePageHelper - method scrollToFooter()");
        LogLog4j.info(">> Scrolling to the 'About' link in the footer...");
        scrollToElement(aboutLinkInFooter);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        LogLog4j.info("> Launching: class PgHomePageHelper - method waitUntilPageIsLoaded()");
        LogLog4j.info(">> Waiting until 'Log In' link in the header is clickable...");
        waitUntilElementIsClickable(loginLink, 10);
    }

    public void waitUntilPageIsFullyLoaded() {
        LogLog4j.info("> Launching: class PgHomePageHelper - " +
                "method waitUntilPageIsFullyLoaded()");
        LogLog4j.info(">> Waiting until 'Sign up it's free' button is clickable...");
        waitUntilElementIsClickable(signUpItsFreeButton, 15);
        LogLog4j.info(">> Waiting until language selection dropdown menu is clickable...");
        waitUntilElementIsClickable(languageSelect, 15);
        LogLog4j.info(">> Waiting until all footer links are visible...");
        waitUntilAllElementsAreVisible(footerLinksList, 15);
    }

}
