package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.forms.PracticeFormPage;
import com.demoqa.utils.DataHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PracticeFormTests extends TestBase {

    PracticeFormPage practiceForm;

    @BeforeEach
    public void precondition() {
        practiceForm = new PracticeFormPage(driver);
        practiceForm.open();
    }

    @Test
    @DisplayName("Регистрация пользователя с использованием действительных данных.")
    public void createAccountPositiveTest() {
        String actualModalTitle = practiceForm.enterPersonalData(
                        DataHelper.getFirstName(),
                        DataHelper.getLastName(),
                        DataHelper.getEmail(),
                        DataHelper.getPhoneNumber()
                )
                .selectGender("Male")
                .typeOfDate("1 Aug 1987")
                .addSubject(new String[]{"Maths", "English"})
                .selectHobby(new String[]{"Sports", "Music"})
                .uploadFile("C:/Tools/image.png")
                .enterState("NCR")
                .enterCity("Delhi")
                .submit()
                .getModalTitleText(10);

        Assertions.assertEquals("Thanks for submitting the form", actualModalTitle,
                "The modal window title after registration does not match the expected one.!");
    }

    @Test
    @DisplayName("Email field validation check (negative scenario)")
    public void createAccountNegativeTest() {
        practiceForm.enterPersonalData(
                        DataHelper.getFirstName(),
                        DataHelper.getLastName(),
                        DataHelper.getInvalidEmail(),
                        DataHelper.getPhoneNumber()
                )
                .selectGender("Female")
                .submit();

        String actualModalTitle = practiceForm.getModalTitleText(2);
        Assertions.assertTrue(actualModalTitle.isEmpty(),
                "Error: A modal window appeared when an invalid email was entered!");
    }
}