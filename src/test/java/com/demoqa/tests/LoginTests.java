package com.demoqa.tests;

import com.demoqa.config.ConfigProvider;
import com.demoqa.core.TestBase;
import com.demoqa.pages.bookStore.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    LoginPage loginPage;

    @BeforeEach
    public void precondition() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginPositivePage() {
        String username = ConfigProvider.get("test.username");
        String password = ConfigProvider.get("test.password");

        String actualName = loginPage.open()
                .enterUserData(username, password)
                .clickOnLoginButton()
                .getLoggedInUserName();
        Assertions.assertEquals(username, actualName, "The authorized user name does not match!");
    }
}