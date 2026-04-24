package com.demoqa.core;

import com.demoqa.utils.TestListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(TestListener.class)
public class TestBase {
    protected WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeEach
    public void setUp() {
        logger.info("=== Initialization WebDriver ===");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public WebDriver getDriver() {
        return driver;
    }
}