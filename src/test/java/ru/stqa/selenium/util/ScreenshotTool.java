package ru.stqa.selenium.util;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotTool {

    private String screenshotFileName;
    private File tmpFile;
    private File screenshot;

    //--- CTOR
    public ScreenshotTool(WebDriver driver) {
        screenshotFileName = "scr-"
                + timeMillisToDateTimeFormat() + ".png";
        tmpFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        screenshot = new File(screenshotFileName);
    }

    private String timeMillisToDateTimeFormat() {
        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd__HH-mm-ss.SSS");
        return sdf.format(currentDate);
    }

    public String getScreenshotName() {
        return screenshot.getName();
    }

    public void saveScreenshot() {
        try {
            Files.copy(tmpFile, screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
