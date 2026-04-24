package com.demoqa.pages.bookStore;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        open("/login");
        return this;
    }

    @FindBy(id = "userName")
    WebElement userNameInput;
    @FindBy(id = "password")
    WebElement passwordInput;


    public LoginPage enterUserData(String userName, String password) {
        type(userNameInput, userName, "Field User Name");
        type(passwordInput, password, "Field Password");
        return this;
    }

    @FindBy(id = "login")
    WebElement loginButton;


    public LoginPage clickOnLoginButton() {
        clickWithJS(loginButton, "Button Login");
        return this;
    }

    @FindBy(id = "userName-value")
    WebElement userNameValue;

    public String getLoggedInUserName() {
        wait.until(ExpectedConditions.visibilityOf(userNameValue));
        String loggedInName = userNameValue.getText();
        logger.info("The authorized user name has been received: '{}'", loggedInName);
        return loggedInName;
    }
}