package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProfilePageHelper extends PageBase {
    @FindBy(id = "profile")
    WebElement profileButton;

    @FindBy(id = "idfamilyinfoimg")
    WebElement profilePhoto;

    @FindBy(xpath = "//div[@id='idbtnsaveprofile']")
    WebElement saveButton;

    @FindBy(id = "idbtneditprofile")
    WebElement editButton;

    @FindBy(id = "fieldobjfamilyName")
    WebElement fieldSavedLastName;

    @FindBy(css = ".itemprofilefit #fieldobjconfession")
    WebElement profileConfession;

    @FindBy(id = "helpselectinfoinprofile")
    WebElement helpSelectInfoInProfile;

    @FindBy(xpath = "//span[@id='fieldobjfamilyName']//input")
    WebElement fieldEditedLastName;

    public ProfilePageHelper(WebDriver driver) {
        super(driver);
    }

    public ProfilePageHelper openProfilePage() {
        openPage(profileButton, profilePhoto);
        return this;
    }

    public String[] saveDataFromTheProfilePage() {
        waitUntilElementIsVisible(profileConfession, 40);
        String[] array;
        Boolean familySign;
        familySign = true;
        array = saveDataFromTheProfileOrFamilyPage(familySign);
        return array;
    }
    public String saveProfile() {
        scrollPageUp();
        String profileFamilyName;
        waitUntilElementIsClickable(saveButton, 40);
        saveButton.click();
        waitUntilElementIsVisible(editButton, 30);

        scrollPageDown();
        //----------------------------??????---------------------------------
        waitUntilElementIsVisible(fieldSavedLastName, 50);
        profileFamilyName =fieldSavedLastName.getText();
        //----------------------------??????---------------------------------
        System.out.println("Family Name in the profile: " + profileFamilyName);
        return profileFamilyName;
    }

    public ProfilePageHelper changeLastName(String lastName) throws InterruptedException {

        openElementByLocator(editButton);
        waitUntilElementIsVisible(helpSelectInfoInProfile, 50);
        waitUntilElementIsVisible(fieldEditedLastName,20);
        fieldEditedLastName.click();

        fieldEditedLastName.clear();
        fieldEditedLastName.sendKeys(lastName);
        //Thread.sleep(7000);
        return this;
    }
    public String newLastNameGeneration() {
        waitUntilElementIsClickable(fieldSavedLastName, 50);

        String profileFamilyName =fieldSavedLastName.getText();
        System.out.println("profileFamilyName: " + profileFamilyName);
        String lastName;
        lastName = "Shapiro";
        if(!(profileFamilyName.equals("Abramov"))) {lastName = "Abramov";}
        System.out.println("lastName new: " + lastName);
        return lastName;
    }

    public Boolean comparingProfileAndFamilyPage(String[] arrayProfile, String[] arrayFamily) {
        System.out.println("Profile confession: " + arrayProfile[0]);
        System.out.println("Family confession: " + arrayFamily[0]);
        Boolean signOfTruth = arrayProfile[0].equals(arrayFamily[0]);
        System.out.println("Confession of the profile is equivalent to confession of the family: "
                + signOfTruth);

        System.out.println("Profile language: " + arrayProfile[1]);
        System.out.println("Family language: " + arrayFamily[1]);
        signOfTruth = signOfTruth&arrayProfile[1].equals(arrayFamily[1]);
        System.out.println("Language of the profile is equivalent to language of the family: "
                + signOfTruth);

        System.out.println("Profile Food preference: " + arrayProfile[2]);
        System.out.println("Family Food preference: " + arrayFamily[2]);
        signOfTruth = signOfTruth&arrayProfile[2].equals(arrayFamily[2]);
        System.out.println("Food preference of the profile is equivalent to food preference of the family: "
                + signOfTruth);

        System.out.println("Profile Email: " + arrayProfile[3]);
        System.out.println("Family Email: " + arrayFamily[3]);
        signOfTruth = signOfTruth&arrayProfile[3].equals(arrayFamily[3]);
        System.out.println("Email of the profile is equivalent to email of the family: "
                + signOfTruth);

        System.out.println("Profile Last Name: : " + arrayProfile[4]);
        System.out.println("Family Last Name: : " + arrayFamily[4]);
        signOfTruth = signOfTruth&arrayFamily[4].contains(arrayProfile[4]);
        System.out.println("The family name of the profile is equivalent to the last name of the family: "
                + signOfTruth);

        System.out.println("Profile Family Description: " + arrayProfile[5]);
        System.out.println("Family Family Description: " + arrayFamily[5]);
        signOfTruth = signOfTruth&arrayProfile[5].equals(arrayFamily[5]);
        System.out.println("Family description of the profile is equivalent to family description of the family: "
                + signOfTruth);

        return signOfTruth;
    }
}
