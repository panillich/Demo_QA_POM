package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IframesPage extends BasePage {

    public IframesPage(WebDriver driver) {
        super(driver);
    }

    public IframesPage open() {
        open("/frames");
        return this;
    }

    @FindBy(id = "frame1")
    WebElement frame1;

    public IframesPage switchToFirstIframe() {
        logger.info("Switch to frame: [frame1]");
        driver.switchTo().frame(frame1);
        return this;
    }

    @FindBy(css = "h1")
    WebElement frameTitle;

    public String getIframeTitleText() {
        String text = frameTitle.getText();
        logger.info("Header text in a frame: '{}'", text);
        return text;
    }

    public IframesPage switchToDefaultContent() {
        logger.info("Return to main page content");
        driver.switchTo().defaultContent();
        return this;
    }
}