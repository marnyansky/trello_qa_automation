package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.PgHomePageHelper;
import ru.stqa.selenium.util.LogLog4j;

public class HomePageTests extends TestBase {

    private PgHomePageHelper homePage;

    @BeforeMethod
    public void initTests() {
        LogLog4j.info("===== Setting up HomePageTests environment - method initTests()");
        homePage = PageFactory.initElements(driver, PgHomePageHelper.class);
        homePage.waitUntilPageIsFullyLoaded();
        LogLog4j.info("===== HomePageTests environment setup complete");
    }

    @Test
    public void verifyAboutLinkInFooterTextTest() {
        LogLog4j.startTestCase("verifyAboutLinkInFooterTextTest");
        LogLog4j.info("Scrolling down the current page (homepage) by 4000px vertically");
        homePage.scrollByCoordinates(0, 4000);

        LogLog4j.info("Performing test result verification (assert): " +
                "The text of 'About' link in the footer is 'About'");
        Assert.assertEquals(homePage.getTextOfAboutLinkInFooter(), "About",
                "Error! The text of 'About' link in the footer is not 'About'");
        LogLog4j.endTestCase();
    }

    @Test(groups = "SmokeTesting")
    public void verifyAboutLinkInFooterTextAdvancedTest() {
        LogLog4j.startTestCase("verifyAboutLinkInFooterTextAdvancedTest");
        LogLog4j.info("Scrolling down the current page (homepage) to 'About' link");
        homePage.scrollToFooter();

        LogLog4j.info("Performing test result verification (assert): " +
                "The text of 'About' link in the footer is 'About'");
        Assert.assertEquals(homePage.getTextOfAboutLinkInFooter(), "About",
                "Error! The text of 'About' link in the footer is not 'About'");
        LogLog4j.endTestCase();
    }

}
