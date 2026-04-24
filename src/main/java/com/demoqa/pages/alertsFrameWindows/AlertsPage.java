package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.config.ConfigProvider;
import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlertsPage extends BasePage {

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    public AlertsPage open() {
        driver.get(ConfigProvider.get("base.url") + "/alerts");
        return this;
    }

    @FindBy(id = "timerAlertButton")
    WebElement timerAlertButton;

    public boolean isTimerAlertAppeared(int seconds) {
        clickWithJS(timerAlertButton, "Alert button with timer");
        return isAlertPresent(seconds);
    }

    @FindBy(id = "confirmButton")
    WebElement confirmButton;

    public AlertsPage clickOnConfirmButton(String action) {
        clickWithJS(confirmButton, "Confirm Alert button");
        if ("Ok".equals(action)) {
            driver.switchTo().alert().accept();
        } else if ("Cancel".equals(action)) {
            driver.switchTo().alert().dismiss();
        }
        return this;
    }

    @FindBy(id = "confirmResult")
    WebElement confirmResult;

    public String getConfirmResultText() {
        return confirmResult.getText();
    }

    @FindBy(id = "promtButton")
    WebElement promtButton;

    public AlertsPage clickOnPromptButton() {
        clickWithJS(promtButton, "Prompt Alert button");
        return this;
    }

    public AlertsPage sendMessageToAlert(String message) {
        if (message != null) {
            driver.switchTo().alert().sendKeys(message);
            driver.switchTo().alert().accept();
        }
        return this;
    }

    @FindBy(id = "promptResult")
    WebElement promptResult;

    public String getPromptResultText() {
        return promptResult.getText();
    }
}