package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;//
import org.testng.Assert;//
import org.testng.annotations.BeforeMethod;//
import org.testng.annotations.Test;//
import ru.stqa.selenium.pages.HomePageAuthHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;
import ru.stqa.selenium.util.DataProviders;//

public class LoginPageTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    HomePageAuthHelper homePageAuth;

    @BeforeMethod
    public void initTests() throws InterruptedException {

        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        homePageAuth = PageFactory.initElements(driver, HomePageAuthHelper.class);
        homePage = PageFactory.initElements(driver, HomePageHelper.class);

        homePage.waitUntilHomePageIsLoaded();
        loginPage.openLoginPage()
        .waitUntilLoginPageIsLoaded();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginPositiveT")
    public void loginPositiveTest(String login, String password) {

        loginPage.initLoginTests(login, password);
        homePageAuth.waitUntilHomePageAuthIsLoaded();
        Assert.assertTrue(homePageAuth.correctAuthorizationIsEnded(login));

    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginNegativeT")
    public void loginNegativeTest(String login, String password) throws InterruptedException {
        loginPage.initLoginTests(login, password);
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
