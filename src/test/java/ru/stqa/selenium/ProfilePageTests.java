package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
//import pages.*;
import ru.stqa.selenium.pages.*;

public class ProfilePageTests<profilePage> extends TestBase {
   // PageBase pageBase;
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    HomePageAuthHelper homePageAuth;
    ProfilePageHelper profilePage;
    FamilyPageHelper familyPage;


    @BeforeMethod
    public void initTests() {
      /*  homePage = new HomePageHelper(driver);
        loginPage = new LoginPageHelper(driver);
        homePageAuth = new HomePageAuthHelper(driver);
        profilePage = new ProfilePageHelper(driver);
        familyPage = new FamilyPageHelper(driver);*/

        profilePage = PageFactory.initElements(driver, ProfilePageHelper.class);
        familyPage = PageFactory.initElements(driver, FamilyPageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        homePageAuth = PageFactory.initElements(driver, HomePageAuthHelper.class);
        homePage = PageFactory.initElements(driver, HomePageHelper.class);


        homePage.waitUntilHomePageIsLoaded();
        loginPage.openLoginPage()
        .initLoginTests(LOGIN, PASSWORD);
        profilePage.openProfilePage();
       // driver.manage().window().maximize();
    }

    @Test
    public void lastNameOfFamilyChanging() throws InterruptedException {
        //----------------Generation of the new last name ---------------------
        String lastName = profilePage.newLastNameGeneration();
        //-------------- Open in edit mode and change the last name --------------------
        profilePage.changeLastName(lastName);

        //-----------------Save profile---------------------
        Assert.assertEquals(profilePage.saveProfile(), lastName, "Last name not preserved.");
    }

    @Test
    public void profileAndFamilyPageComparing() {
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
