package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class WindowsPage extends BasePage {

    public WindowsPage(WebDriver driver) {
        super(driver);
    }

    public WindowsPage open() {
        open("/browser-windows");
        return this;
    }

    @FindBy(id = "tabButton")
    WebElement tabButton;

    public WindowsPage clickNewTabButton() {
        clickWithJS(tabButton, "Button New Tab");
        return this;
    }

    public WindowsPage switchToNewTab(int index) {
        logger.info("Switch to a tab with an index: {}", index);
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(index));
        return this;
    }

    @FindBy(id = "sampleHeading")
    WebElement sampleHeading;

    public String getNewTabTitleText() {
        shouldHaveText(sampleHeading, "This is a sample page", 5);
        String text = sampleHeading.getText();
        logger.info("Headline text in a new tab: '{}'", text);
        return text;
    }
}
