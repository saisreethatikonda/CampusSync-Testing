package com.cognizant.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GenericScreenshots {

    private GenericScreenshots() {
    }

    public static String capture(WebDriver driver, String screenshotName) {
        String safeScreenshotName = screenshotName.replaceAll("[^a-zA-Z0-9._-]", "_");
        String fileName = safeScreenshotName + "_" + GenericUtil.getCurrentDateTime() + ".png";
        String destinationPath = FrameworkConstants.SCREENSHOT_FOLDER_PATH + File.separator + fileName;

        try {
            GenericUtil.createDirectory(FrameworkConstants.SCREENSHOT_FOLDER_PATH);
            File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(sourceFile.toPath(), Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING);
            return destinationPath;
        } catch (IOException exception) {
            throw new RuntimeException("Unable to capture screenshot", exception);
        }
    }
}
