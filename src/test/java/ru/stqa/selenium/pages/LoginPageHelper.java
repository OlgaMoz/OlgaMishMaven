package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase {

    @FindBy(id = "signinrequest")
    WebElement signInButton;

    @FindBy(id = "idsignin")
    WebElement loginIcon;

    @FindBy(id = "logininput")
    WebElement loginField;

    @FindBy(id = "passwordinput")
    WebElement passwordField;

    @FindBy(id = "wrongloginorpassword")
    WebElement wrongAuth;

    @FindBy(id = "closedsignin")
    WebElement closeByXButton;

    public LoginPageHelper(WebDriver driver) {
        super(driver);
    }

    public LoginPageHelper openLoginPage() {
        openPage(loginIcon, signInButton);
        return this;
    }

    public LoginPageHelper waitUntilLoginPageIsLoaded() {
        waitUntilPageIsLoaded(signInButton, 20);
        return this;
    }

    public LoginPageHelper initLoginTests(String login, String passw) {
        loginField.sendKeys(login);
        passwordField.sendKeys(passw);
        signInButton.click();
        return this;
    }

    public LoginPageHelper closeLoginWindowByX() {
        closeByXButton.click();
        return this;
    }

    public boolean loginToTheSystemIncorrect() {
        waitUntilElementIsVisible(wrongAuth, 30);
        return wrongAuth.getText().contains("Wrong Authorization!");
    }
}
