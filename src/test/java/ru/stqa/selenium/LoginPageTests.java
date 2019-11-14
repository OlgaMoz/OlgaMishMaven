package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import pages.HomePageAuthHelper;
//import pages.HomePageHelper;
//import pages.LoginPageHelper;
import ru.stqa.selenium.pages.HomePageAuthHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;

public class LoginPageTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    HomePageAuthHelper homePageAuth;

    @BeforeMethod
    public void initTests() {
      /* homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        homePageAuth = new HomePageAuthHelper(driver);*/

        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        homePageAuth = PageFactory.initElements(driver, HomePageAuthHelper.class);
        homePage = PageFactory.initElements(driver, HomePageHelper.class);

        homePage.waitUntilHomePageIsLoaded();
        loginPage.openLoginPage()
        .waitUntilLoginPageIsLoaded();
    }

    @Test
    public void loginPositiveTest() {

        loginPage.initLoginTests(LOGIN, PASSWORD);
        homePageAuth.waitUntilHomePageAuthIsLoaded();
        Assert.assertTrue(homePageAuth.correctAuthorizationIsEnded());

    }

    @Test
    public void loginNegativeTest() {
        loginPage.initLoginTests(LOGIN, LOGIN);
        Assert.assertTrue(loginPage.loginToTheSystemIncorrect());
        loginPage.closeLoginWindowByX();
        Assert.assertTrue(homePage.correctHomePageIsLoaded());
    }


    @Test
    public void loginExitByCancelTest() {
        loginPage.waitUntilLoginPageIsLoaded()
        .closeLoginWindowByX();
       Assert.assertTrue(homePage.correctHomePageIsLoaded()&homePage.userIsNotLoggedIn());
    }

}
