package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlertsFrameWindowsTests extends TestBase {

    AlertsPage alerts;

    @BeforeEach
    public void precondition() {
        alerts = new AlertsPage(driver);
    }

    @Test
    public void waitAlertTest() {
        logger.info("Test: Waiting for an alert with a timer");
        boolean isAlertPresent = alerts.open().isTimerAlertAppeared(6);
        Assertions.assertTrue(isAlertPresent, "The alert with the timer did not appear.!");
    }

    @Test
    public void alertWithSelectResult() {
        logger.info("Test: Selecting 'Cancel' in the confirmation alert");
        String resultText = alerts.open()
                .clickOnConfirmButton("Cancel")
                .getConfirmResultText();

        Assertions.assertTrue(resultText.contains("Cancel"), "The result text does not contain 'Cancel'");
    }

    @Test
    public void sendMessageToAlertTest() {
        logger.info("Test: Entering a Message in Prompt-alert");
        String expectedMessage = "Hello Professional QA";

        String actualResultText = alerts.open()
                .clickOnPromptButton()
                .sendMessageToAlert(expectedMessage)
                .getPromptResultText();

        Assertions.assertTrue(actualResultText.contains(expectedMessage), "The alert text does not match the entered text!");
    }
}