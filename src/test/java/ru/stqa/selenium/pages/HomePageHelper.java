package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePageHelper extends PageBase {

    @FindBy(id = "idsignin")
    WebElement loginIcon;

    @FindBy(xpath = "//div[@class = 'itemEventInsert']")
    List<WebElement> eventsList;

    @FindBy(id = "idtitletypesearchevents")
    WebElement listEvent;

    @FindBy(name = "selectholidays")
    WebElement filterHolidays;

    @FindBy(name = "selectfood")
    WebElement filterFood;

    @FindBy(name = "selectlangues")
    WebElement filterLanguages;

    @FindBy(id = "idbtnclearfilter")
    WebElement clearFilterButton;

    @FindBy(css  = ".holidayItemEvents")
    List<WebElement> listHolidaysHoliday;

    @FindBy(xpath = "//i[@class='fa fa-cutlery']/..")
    List<WebElement> listHolidaysFood;

    @FindBy(xpath = "//i[@class='fa fa-globe']/..")
    List<WebElement> listHolidaysLanguage;

    @FindBy(xpath = "//select[@name = 'selectholidays']/option")
    List<WebElement> optionsHolidays;

    @FindBy(xpath = "//select[@name='selectfood']/option")
    List<WebElement> optionsFood;

    @FindBy(xpath = "//select[@name='selectlangues']/option")
    List<WebElement> optionsLanguage;

    @FindBy(xpath = "//option[@selected][@value = 'Shabbat']")
    WebElement chosenFilterHolidays;

    @FindBy(xpath = "//option[@selected][@value = 'Kosher']")
    WebElement chosenFilterFood;

    @FindBy(xpath = "//option[@selected][@value = 'English']")
    WebElement chosenFilterLanguage;

    public HomePageHelper(WebDriver driver) {
        super(driver);
    }

    public HomePageHelper waitUntilHomePageIsLoaded(){
        waitUntilPageIsLoaded(loginIcon,20);
        return this;
    }

    public boolean correctHomePageIsLoaded(){
        return correctPageIsLoaded(listEvent, "List events");
    }

    public boolean userIsNotLoggedIn(){
        return correctPageIsLoaded(loginIcon, "Login");
    }

    public boolean getAllHolidaysValuesForAllEventsChosenByFilterShabbat(String holiday){
        return getAllHolidaysValuesForAllEventsChosenByFilter(listHolidaysHoliday, holiday);
    }

  /*  public boolean getAllHolidaysValuesForAllEventsChosenByFilterShabbat(){
        return getAllHolidaysValuesForAllEventsChosenByFilter(listHolidaysHoliday, "Shabbat");
    }
*/
    public boolean getAllHolidaysValuesForAllEventsChosenByFilterKosher(String food) {
        return getAllHolidaysValuesForAllEventsChosenByFilter(listHolidaysFood,food);
    }

  /*  public boolean getAllHolidaysValuesForAllEventsChosenByFilterKosher() {
        return getAllHolidaysValuesForAllEventsChosenByFilter(listHolidaysFood,"Kosher");
    }*/

    public boolean getAllHolidaysValuesForAllEventsChosenByFilterEnglish(String language) {
       return getAllHolidaysValuesForAllEventsChosenByFilter(listHolidaysLanguage,language);
    }

  /*  public boolean getAllHolidaysValuesForAllEventsChosenByFilterEnglish() {
        return getAllHolidaysValuesForAllEventsChosenByFilter(listHolidaysLanguage,"English");
    }*/

    public HomePageHelper waitThatFilterByHolidayAndAllOptionsAreLoaded() throws InterruptedException {
        waitThatFilterAndAllOptionsAreLoaded(filterHolidays, optionsHolidays);
        return this;
    }

    public HomePageHelper waitThatFilterByFoodAndAllOptionsAreLoaded() throws InterruptedException {
        waitThatFilterAndAllOptionsAreLoaded(filterFood, optionsFood);
        return this;
    }

    public HomePageHelper waitThatFilterByLanguageAndAllOptionsAreLoaded() throws InterruptedException {
        waitThatFilterAndAllOptionsAreLoaded(filterLanguages, optionsLanguage);
        return this;
    }

    public HomePageHelper getSelectElementFilterByHoliday(String holiday) throws InterruptedException {
        getSelectElementFilterBy(filterHolidays, holiday, optionsHolidays);
        return this;
    }

   /* public HomePageHelper getSelectElementFilterByHoliday() throws InterruptedException {
        getSelectElementFilterBy(filterHolidays, "Shabbat", optionsHolidays);
        return this;
    }*/

    public HomePageHelper getSelectElementFilterByFood(String food) throws InterruptedException {
        getSelectElementFilterBy(filterFood, food, optionsFood);
        return this;
    }

   /* public HomePageHelper getSelectElementFilterByFood() throws InterruptedException {
        getSelectElementFilterBy(filterFood, "Kosher", optionsFood);
        return this;
    }*/

    public HomePageHelper getSelectElementFilterByLanguage(String language) throws InterruptedException {
        getSelectElementFilterBy(filterLanguages, language, optionsLanguage);
        return this;
    }

 /*   public HomePageHelper getSelectElementFilterByLanguage() throws InterruptedException {
        getSelectElementFilterBy(filterLanguages, "English", optionsLanguage);
        return this;
    }*/
//-------------------------------------------------------------------------------------------------------------------
    public HomePageHelper waitThatFilterHolidayIsChosenAndAllEventsByFilterAreLoaded(String holiday) {
        waitThatFilterIsChosenAndAllEventsByFilterAreLoaded(By.xpath("//option[@selected][@value = '" + holiday +"']"));
        return this;
    }
  //  By.xpath("//option[@selected][@value = '" + holiday +"']")

  /*  public HomePageHelper waitThatFilterHolidayIsChosenAndAllEventsByFilterAreLoaded(String holiday) {
        waitThatFilterIsChosenAndAllEventsByFiterAreLoaded(chosenFilterHolidays);
        return this;
    }*/
   /* public HomePageHelper waitThatFilterHolidayIsChosenAndAllEventsByFilterAreLoaded() {
        waitThatFilterIsChosenAndAllEventsByFiterAreLoaded(chosenFilterHolidays);
        return this;
    }*/

    public HomePageHelper waitThatFilterFoodIsChosenAndAllEventsByFilterAreLoaded(String food) {
        waitThatFilterIsChosenAndAllEventsByFilterAreLoaded(By.xpath("//option[@selected][@value = '" + food +"']"));
        return this;
    }

 /*   public HomePageHelper waitThatFilterFoodIsChosenAndAllEventsByFilterAreLoaded() {
        waitThatFilterIsChosenAndAllEventsByFilterAreLoaded(chosenFilterFood);
        return this;
    }*/

    public HomePageHelper waitThatFilterLanguageIsChosenAndAllEventsByFilterAreLoaded(String language) {
        waitThatFilterIsChosenAndAllEventsByFilterAreLoaded(By.xpath("//option[@selected][@value = '" + language +"']"));
        return this;
    }

  /*  public HomePageHelper waitThatFilterLanguageIsChosenAndAllEventsByFilterAreLoaded() {
        waitThatFilterIsChosenAndAllEventsByFilterAreLoaded(chosenFilterLanguage);
        return this;
    }*/

    public HomePageHelper verifyTheStatusClearButton() {

        System.out.println("is displayed: " + clearFilterButton.isDisplayed());
        System.out.println("is enabled: " + clearFilterButton.isEnabled());
        return this;
    }
}
