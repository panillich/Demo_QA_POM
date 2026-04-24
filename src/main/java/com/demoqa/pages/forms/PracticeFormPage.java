package com.demoqa.pages.forms;

import com.demoqa.core.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PracticeFormPage extends BasePage {
    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    public PracticeFormPage open() {
        open("/automation-practice-form");
        return this;
    }

    @FindBy(id = "firstName")
    WebElement firstName;
    @FindBy(id = "lastName")
    WebElement lastName;
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userNumber")
    WebElement userNumber;

    public PracticeFormPage enterPersonalData(String name, String surName, String email, String phone) {
        type(firstName, name, "Name");
        type(lastName, surName, "Last Name");
        type(userEmail, email, "Email");
        type(userNumber, phone, "Phone");
        return this;
    }

    @FindBy(css = "[for='gender-radio-1']")
    WebElement male;
    @FindBy(css = "[for='gender-radio-2']")
    WebElement female;
    @FindBy(css = "[for='gender-radio-3']")
    WebElement other;

    public PracticeFormPage selectGender(String gender) {
        if (gender.equals("Male")) click(male, "Sex: Male");
        else if (gender.equals("Female")) click(female, "Sex: Female");
        else click(other, "Sex: Other");
        return this;
    }

    @FindBy(id = "dateOfBirthInput")
    WebElement dateOfBirthInput;

    public PracticeFormPage typeOfDate(String date) {
        click(dateOfBirthInput, "Date of birth field");
        String os = System.getProperty("os.name");
        if (os.startsWith("Mac")) {
            dateOfBirthInput.sendKeys(Keys.COMMAND);
        } else {
            dateOfBirthInput.sendKeys(Keys.CONTROL, "a");
        }
        dateOfBirthInput.sendKeys(date);
        dateOfBirthInput.sendKeys(Keys.ENTER);
        return this;
    }

    @FindBy(id = "subjectsInput")
    WebElement subjectsInput;

    public PracticeFormPage addSubject(String[] subjects) {
        for (String subject : subjects) {
            if (subject != null) {
                type(subjectsInput, subject, "Item: " + subject);
                subjectsInput.sendKeys(Keys.ENTER);
            }
        }
        return this;
    }

    @FindBy(css = "[for='hobbies-checkbox-1']")
    WebElement sports;
    @FindBy(css = "[for='hobbies-checkbox-2']")
    WebElement reading;
    @FindBy(css = "[for='hobbies-checkbox-3']")
    WebElement music;

    public PracticeFormPage selectHobby(String[] hobbies) {
        for (String hobby : hobbies) {
            if (hobby.equals("Sports")) click(sports, "Hobby: Sports");
            if (hobby.equals("Reading")) click(reading, "Hobby: Reading");
            if (hobby.equals("Music")) click(music, "Hobby: Music");
        }
        return this;
    }

    @FindBy(id = "uploadPicture")
    WebElement uploadPicture;

    public PracticeFormPage uploadFile(String path) {
        uploadPicture.sendKeys(path);
        logger.info("The file has been uploaded.: {}", path);
        return this;
    }

    @FindBy(id = "react-select-3-input")
    WebElement stateInput;

    public PracticeFormPage enterState(String state) {
        stateInput.sendKeys(state);
        stateInput.sendKeys(Keys.ENTER);
        logger.info("The state has been selected: {}", state);
        return this;
    }

    @FindBy(id = "react-select-4-input")
    WebElement cityInput;

    public PracticeFormPage enterCity(String city) {
        cityInput.sendKeys(city);
        cityInput.sendKeys(Keys.ENTER);
        logger.info("The city has been selected: {}", city);
        return this;
    }

    @FindBy(id = "submit")
    WebElement submit;

    public PracticeFormPage submit() {
        clickWithJS(submit, "Submit button");
        return this;
    }

    @FindBy(id = "example-modal-sizes-title-lg")
    WebElement modalTitle;

    public String getModalTitleText(int timeoutSeconds) {
        boolean isAppeared = shouldHaveText(modalTitle, "Thanks for submitting the form", timeoutSeconds);
        return isAppeared ? modalTitle.getText() : "";
    }
}