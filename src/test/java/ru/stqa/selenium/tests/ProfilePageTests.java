package ru.stqa.selenium.tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.HomePageAuthHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;
import ru.stqa.selenium.pages.ProfilePageHelper;
import ru.stqa.selenium.pages.FamilyPageHelper;

import ru.stqa.selenium.util.DataProviders;

public class ProfilePageTests<profilePage> extends TestBase {
   // PageBase pageBase;
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    HomePageAuthHelper homePageAuth;
    ProfilePageHelper profilePage;
    FamilyPageHelper familyPage;


    @BeforeMethod
    public void initTests() throws InterruptedException {

        profilePage = PageFactory.initElements(driver, ProfilePageHelper.class);
        familyPage = PageFactory.initElements(driver, FamilyPageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        homePageAuth = PageFactory.initElements(driver, HomePageAuthHelper.class);
        homePage = PageFactory.initElements(driver, HomePageHelper.class);


        homePage.waitUntilHomePageIsLoaded();
        loginPage.openLoginPage()
        .initLoginTests(LOGIN, PASSWORD);
        profilePage.openProfilePage();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "lastNameOfFamilyChangingT")
    public void lastNameOfFamilyChanging(String lastName) throws InterruptedException {
        //----------------Generation of the new last name ---------------------
       // String lastName = profilePage.newLastNameGeneration();
        //-------------- Open in edit mode and change the last name --------------------
        profilePage.changeLastName(lastName);

        //-----------------Save profile---------------------
        Assert.assertEquals(profilePage.saveProfile(), lastName, "Last name not preserved.");
    }

    @Test
    public void profileAndFamilyPageComparing() throws InterruptedException {
        String[] profileDetails;
        String[] familyDetails;
        profileDetails = profilePage.saveDataFromTheProfilePage();
        familyPage.openFamilyProfilePage();
        familyDetails = familyPage.saveDataFromTheFamilyPage();
        Boolean resultComparingPages;
        resultComparingPages = profilePage.comparingProfileAndFamilyPage(profileDetails, familyDetails);
        Assert.assertTrue(resultComparingPages);
    }
}
