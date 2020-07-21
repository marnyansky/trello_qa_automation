package ru.stqa.selenium.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class ListenerMethodsImpl extends AbstractWebDriverEventListener {

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        LogLog4j.info("Find element: " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        LogLog4j.info("Element " + by + " found");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        ScreenshotTool scrTool = new ScreenshotTool(driver);
        scrTool.saveScreenshot();
        LogLog4j.error("Error: " + throwable + " See screenshot file '"
                + scrTool.getScreenshotName() + "' for details");
    }

}
