package ru.stqa.selenium.tests;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.selenium.SuiteConfiguration;
import ru.stqa.selenium.factory.WebDriverPool;
import ru.stqa.selenium.pages.PgHomePageHelper;
import ru.stqa.selenium.util.ListenerMethodsImpl;
import ru.stqa.selenium.util.LogLog4j;
import ru.stqa.selenium.util.ScreenshotTool;

import java.io.IOException;
import java.net.URL;

public abstract class TestBase {

    protected static URL gridHubUrl = null;
    protected static String baseUrl;
    protected static Capabilities capabilities;

    protected ListenerMethodsImpl myListener;
    protected EventFiringWebDriver driver;
    protected PgHomePageHelper homePage;

    public static final String LOGIN_ATLASSIAN = "grudcyn.news@gmail.com";
    public static final String PASSWORD_ATLASSIAN = "validPasswordForAtlassianLogin";
    public static final String USERNAME = "aryehtest";

    @BeforeSuite
    public void initTestSuite() throws IOException {
        SuiteConfiguration config = new SuiteConfiguration();
        baseUrl = config.getProperty("site.url");
        if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
            gridHubUrl = new URL(config.getProperty("grid.url"));
        }
        capabilities = config.getCapabilities();
    }

    @BeforeMethod
    public void initWebDriver() {
        myListener = new ListenerMethodsImpl();
        driver = new EventFiringWebDriver(WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities));
        driver.register(myListener);
        homePage = PageFactory.initElements(driver, PgHomePageHelper.class);

        driver.get(baseUrl);
        driver.manage().window().maximize();
        homePage.waitUntilPageIsLoaded();
    }

    private void enableVerboseLogging() {
        System.setProperty("webdriver.chrome.logfile", "c:\\_qa\\_test\\_qaauto\\chromedriver.log");
        System.setProperty("webdriver.chrome.verboseLogging", "true");
    }

    private ChromeOptions setLocale(String locale) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=" + locale);
        return options;
    }

    private ChromeOptions enableHeadlessMode() {
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        return options;
    }

    @AfterMethod
    public void tearDownForTest(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ScreenshotTool scrTool = new ScreenshotTool(driver);
            LogLog4j.error("Test failure. See screenshot file "
                    + scrTool.getScreenshotName() + " for details");
        }
        driver.quit();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownForSuite() {
        WebDriverPool.DEFAULT.dismissAll();
    }

}
