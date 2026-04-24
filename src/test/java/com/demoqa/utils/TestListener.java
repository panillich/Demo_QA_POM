package com.demoqa.utils;

import com.demoqa.core.TestBase;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements TestWatcher {
    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        String testName = context.getDisplayName();
        Object testInstance = context.getRequiredTestInstance();
        if (testInstance instanceof TestBase) {
            WebDriver driver = ((TestBase) testInstance).getDriver();
            if (driver != null) {
                takeScreenshot(driver, testName);
                driver.quit();
                logger.info("=== Driver closed after test crash ===");
            }
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        closeDriver(context);
        logger.info("=== The test was successful, the driver is closed ===");
    }

    private void closeDriver(ExtensionContext context) {
        Object testInstance = context.getRequiredTestInstance();
        if (testInstance instanceof TestBase) {
            WebDriver driver = ((TestBase) testInstance).getDriver();
            if (driver != null) {
                driver.quit();
            }
        }
    }

    private void takeScreenshot(WebDriver driver, String testName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = testName.replaceAll("[^a-zA-Z0-9.-]", "_") + "_" + timestamp + ".png";
            Path directory = Paths.get("screenshots");
            if (!Files.exists(directory)) Files.createDirectory(directory);
            Path destination = directory.resolve(fileName);
            Files.copy(screenshot.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            logger.info("📸 Screenshot saved: {}", destination.toAbsolutePath());
        } catch (IOException e) {
            logger.error("Error saving screenshot: {}", e.getMessage());
        }
    }
}