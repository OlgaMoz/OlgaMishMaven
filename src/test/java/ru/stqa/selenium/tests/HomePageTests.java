package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.HomePageAuthHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.util.DataProviders;

public class HomePageTests extends TestBase {
    HomePageHelper homePage;
    HomePageAuthHelper homePageAuth;

    @BeforeMethod
    public void initTests() {

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

    @Test(enabled = true, dataProviderClass = DataProviders .class, dataProvider = "singleFilterHolidaysT")
    public void singleFilterHolidays(String holiday) throws InterruptedException {

        homePage.waitThatFilterByHolidayAndAllOptionsAreLoaded()

        .verifyTheStatusClearButton()

        .getSelectElementFilterByHoliday(holiday)

        .waitThatFilterHolidayIsChosenAndAllEventsByFilterAreLoaded(holiday);

        Assert.assertTrue(homePage.getAllHolidaysValuesForAllEventsChosenByFilterShabbat(holiday));

    }

    @Test(enabled = true, dataProviderClass = DataProviders .class, dataProvider = "singleFilterFoodT")
    public void singleFilterFood(String food) throws InterruptedException {

        homePage.waitThatFilterByFoodAndAllOptionsAreLoaded()

                .verifyTheStatusClearButton()

                .getSelectElementFilterByFood(food)

                .waitThatFilterFoodIsChosenAndAllEventsByFilterAreLoaded(food);

        Assert.assertTrue(homePage.getAllHolidaysValuesForAllEventsChosenByFilterKosher(food));
    }

    @Test(enabled = true, dataProviderClass = DataProviders .class, dataProvider = "singleFilterLanguageT")
    public void singleFilterLanguage(String language) throws InterruptedException {

        homePage.waitThatFilterByLanguageAndAllOptionsAreLoaded()

        .verifyTheStatusClearButton()

        .getSelectElementFilterByLanguage(language)

        .waitThatFilterLanguageIsChosenAndAllEventsByFilterAreLoaded(language);

        Assert.assertTrue(homePage.getAllHolidaysValuesForAllEventsChosenByFilterEnglish(language));
    }

}
