package com.demoqa.pages;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        open("/");
        return this;
    }

    @FindBy(css = "a[href='/books']")
    WebElement bookStore;

    public SidePanel getBookStore() {
        clickWithJS(bookStore, "Card Book Store Application");
        return new SidePanel(driver);
    }

    @FindBy(css = "a[href='/alertsWindows']")
    WebElement alertsWindows;

    public SidePanel getAlertsFrameWindows() {
        clickWithJS(alertsWindows, "Card Alerts, Frame & Windows");
        return new SidePanel(driver);
    }

    @FindBy(css = "a[href='/forms']")
    WebElement forms;

    public SidePanel getForms() {
        clickWithJS(forms, "Card Forms");
        return new SidePanel(driver);
    }

    @FindBy(css = "a[href='/elements']")
    WebElement elements;

    public SidePanel getElements() {
        clickWithJS(elements, "Card Elements");
        return new SidePanel(driver);
    }
}