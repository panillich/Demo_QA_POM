
package com.demoqa.core;

import com.demoqa.config.ConfigProvider;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    public static JavascriptExecutor js;
    protected WebDriverWait wait;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    protected void open(String relativePath) {
        String url = ConfigProvider.get("base.url") + relativePath;
        logger.info("Opening the page: {}", url);
        driver.get(url);
    }

    public void click(WebElement element, String name) {
        logger.info("Click on the element: [{}]", name);
        element.click();
    }

    public void type(WebElement element, String text, String name) {
        if (text != null) {
            logger.info("Entering text '{}' in the field: [{}]", text, name);
            clearField(element, name);
            element.sendKeys(text);
        }
    }

    public void clickWithJS(WebElement element, String name) {
        wait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});", element);
        logger.info("Click (JS) on an element: [{}]", name);
        js.executeScript("arguments[0].click();", element);
    }

    public void clearField(WebElement element, String name) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        String os = System.getProperty("os.name");
        Keys modifier = os.startsWith("Mac") ? Keys.COMMAND : Keys.CONTROL;
        element.sendKeys(modifier, "a");
        element.sendKeys(Keys.BACK_SPACE);
    }

    public boolean isAlertPresent(int time) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(time)).until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean shouldHaveText(WebElement element, String text, int time) {
        try {
            return new WebDriverWait(driver, Duration.ofSeconds(time))
                    .until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (TimeoutException e) {
            return false;
        }
    }
}