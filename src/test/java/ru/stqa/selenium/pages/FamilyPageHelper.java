package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FamilyPageHelper extends PageBase {

    @FindBy(id = "family")
    WebElement familyIcon;

    @FindBy(id = "fieldobjconfession")
    WebElement confessionField;

    public FamilyPageHelper(WebDriver driver) {
        super(driver);
    }

   /* public void openFamilyProfilePage() {
        openPage("family", By.id("fieldobjconfession"));
    }*/
    public FamilyPageHelper openFamilyProfilePage() throws InterruptedException {
        openPage(familyIcon, confessionField);
        return this;
    }

    public String[] saveDataFromTheFamilyPage() {
        String[] array;
        Boolean familySign;
        familySign = false;
        array = saveDataFromTheProfileOrFamilyPage(familySign);
        return array;
    }
}
