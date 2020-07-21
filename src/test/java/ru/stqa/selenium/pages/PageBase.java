package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public abstract class PageBase {

    WebDriver driver;

    //--- CTOR
    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    //--- Utilitarian methods
    public String getTitle() {
        return driver.getTitle();
    }

    public void fillField(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value);
    }

    public void switchToWindow(String handle) {
        driver.switchTo().window(handle);
    }

    public String getAnotherWindowHandle(String parentHandle) {
        Set<String> handles = driver.getWindowHandles();
        String anotherHandle = "";
        for (String handle : handles) {
            if (!handle.equals(parentHandle)) {
                anotherHandle = handle;
            }
        }
        return anotherHandle;
    }

    public void scrollByCoordinates(int pixelsAxisX, int pixelsAxisY) {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("window.scrollBy(" + pixelsAxisX + ", " + pixelsAxisY + ")");
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor jsExec = (JavascriptExecutor) driver;
        jsExec.executeScript("arguments[0].scrollIntoView();", element);
    }

    //--- WebDriverWait methods
    public abstract void waitUntilPageIsLoaded();

    public void waitUntilElementIsClickable(By locator, int time) {
        try {
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsClickable(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(By locator, int time) {
        try {
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsNotVisible(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsAreVisible(List<WebElement> elementList, int time) {
        try {
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.visibilityOfAllElements(elementList));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAttributeValueIs(WebElement element, String attribute, String value, int time) {
        try {
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.attributeToBe(element, attribute, value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilFrameIsLoadedAndSwitchToIt(WebElement element, int time) {
        try {
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilNumberOfWindows(int number, int time) {
        try {
            new WebDriverWait(driver, time).until(ExpectedConditions
                    .numberOfWindowsToBe(number));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
