package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IntroPageHelper extends PageBase{
    @FindBy(id = "closedIntro")
    WebElement closedIntro;

    @FindBy(className = "titlefilter")
    WebElement headerFilters;

    public IntroPageHelper(WebDriver driver) {
        super(driver);
    }

    public IntroPageHelper openIntroPage() {
        openPage(closedIntro, headerFilters);
        return this;
    }
}
