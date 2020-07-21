package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.stqa.selenium.util.LogLog4j;

import java.util.List;

public class PgActivityHelper extends PageBase {

    @FindBy(css = "div.phenom-desc")
    List<WebElement> activityRecordsList;

    //--- CTOR
    public PgActivityHelper(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        LogLog4j.info("> Launching: class PgActivityHelper - method waitUntilPageIsLoaded()");
        LogLog4j.info(">> Waiting until all activity records are visible...");
        waitUntilAllElementsAreVisible(activityRecordsList, 10);
    }

    public String getLatestActivityRecordText() {
        return activityRecordsList.get(0).getText();
    }

}
