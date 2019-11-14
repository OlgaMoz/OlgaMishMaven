package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import pages.HomePageAuthHelper;
//import pages.HomePageHelper;
import ru.stqa.selenium.pages.HomePageAuthHelper;
import ru.stqa.selenium.pages.HomePageHelper;

public class HomePageTests extends TestBase {
    HomePageHelper homePage;
    HomePageAuthHelper homePageAuth;

    @BeforeMethod
    public void initTests() {
      /* homePage = new HomePageHelper(driver);
        homePageAuth = new HomePageAuthHelper(driver);*/

        homePageAuth = PageFactory.initElements(driver, HomePageAuthHelper.class);
        homePage = PageFactory.initElements(driver, HomePageHelper.class);

        homePage.waitUntilHomePageIsLoaded();


    }

    @Test
    public void homePageVerificationTest() {
        //-----------------------"List events element exists: " -----------------------------------------
        Assert.assertTrue(homePage.correctHomePageIsLoaded());
        //-----------------------"Login Icon exists: "-----------------------------------------
        Assert.assertTrue(homePage.userIsNotLoggedIn());
    }

    @Test(enabled = true)
    public void singleFilterHolidays() throws InterruptedException {

        homePage.waitThatFilterByHolidayAndAllOptionsAreLoaded()

        .verifyTheStatusClearButton()

        .getSelectElementFilterByHoliday()

        .waitThatFilterHolidayIsChosenAndAllEventsByFilterAreLoaded();

        Assert.assertTrue(homePage.getAllHolidaysValuesForAllEventsChosenByFilterShabbat());

    }

    @Test(enabled = true)
    public void singleFilterFood() throws InterruptedException {

        homePage.waitThatFilterByFoodAndAllOptionsAreLoaded()

        .verifyTheStatusClearButton()

        .getSelectElementFilterByFood()

        .waitThatFilterFoodIsChosenAndAllEventsByFilterAreLoaded();

        Assert.assertTrue(homePage.getAllHolidaysValuesForAllEventsChosenByFilterKosher());
    }

    @Test(enabled = true)
    public void singleFilterLanguage() throws InterruptedException {

        homePage.waitThatFilterByLanguageAndAllOptionsAreLoaded()

        .verifyTheStatusClearButton()

        .getSelectElementFilterByLanguage()

        .waitThatFilterLanguageIsChosenAndAllEventsByFilterAreLoaded();

        Assert.assertTrue(homePage.getAllHolidaysValuesForAllEventsChosenByFilterEnglish());
    }

}
