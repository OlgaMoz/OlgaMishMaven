package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PageBase {
    WebDriver driver;
    public static final String LOGIN = "olga_mo_";
    public static final String PASSWORD = "Anna2019";

    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public void tearDown() {
        driver.quit();
    }

    public void openPage(WebElement element1, WebElement element2) throws InterruptedException {
        waitUntilElementIsClickable(element1, 30);
        openElementByLocator(element1);
        waitUntilElementIsVisible(element2, 50);
    }

    public void openPage(String locator1, By locator2) {
        waitUntilElementIsClickable(By.id(locator1), 30);
        openElementById(locator1);
        waitUntilElementIsVisible(locator2, 50);
    }
    public void waitUntilElementIsVisible(WebElement element, int time) throws InterruptedException {
        try {
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            Thread.sleep(5000);
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(By locator, int time){
        try{
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsVisible(List<WebElement> listOptions, int time){
        try{
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.visibilityOfAllElements(listOptions));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsClickable(WebElement element, int time){
        try{
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.elementToBeClickable(element));
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public void waitUntilElementIsClickable(By locator, int time){
        try{
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsPresent(By locator, int time){
        try{
            new WebDriverWait(driver, time)
                    .until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void waitUntilPageIsLoaded(WebElement element, int time){
        waitUntilElementIsClickable(element, time);
    }

  public Boolean correctPageIsLoaded(WebElement element, String loc){
        return element.getText().equalsIgnoreCase(loc);
    }

    public boolean getAllHolidaysValuesForAllEventsChosenByFilter(List<WebElement> list, String name) {

        // --- verify that all holidays values are "name" ----
        int counter = 0;
        for (int i=0; i < list.size(); i++){
            System.out.println("Filter: " + list.get(i).getText());
            if (list.get(i).getText().contains(name)) counter++;
        }
        Boolean allHolidaysChosenByFilter = true;
        allHolidaysChosenByFilter = counter == list.size();
        return allHolidaysChosenByFilter;
    }

    public void waitThatFilterAndAllOptionsAreLoaded(WebElement element, List<WebElement> list) throws InterruptedException {

        Thread.sleep(5000);
        waitUntilElementIsClickable(element,100);
        waitUntilAllElementsVisible(list, 90);
    }

    public void waitThatFilterIsChosenAndAllEventsByFilterAreLoaded(WebElement element) {
        waitUntilElementIsClickable(By.cssSelector("#idbtnclearfilter"),20);
        waitUntilElementIsClickable(element,20);
        // ------ wait that all events by fiter "shabbat" are loaded ----
        waitUntilAllElementsVisible(driver.findElements(By
                .xpath("//div[@class = 'itemEventInsert']")),40);
    }

   public void waitThatFilterIsChosenAndAllEventsByFilterAreLoaded(By locator) {
        waitUntilElementIsClickable(By.cssSelector("#idbtnclearfilter"),20);
        waitUntilElementIsPresent(locator,20);
        // ------ wait that all events by fiter  are loaded ----
        waitUntilAllElementsVisible(driver.findElements(By
                .xpath("//div[@class = 'itemEventInsert']")),40);
    }

    public void getSelectElementFilterBy(WebElement element, String name, List<WebElement> list) throws InterruptedException {
        waitUntilElementIsClickable(element, 90);
        Select selector;
        try{
            selector = new Select(element);
            selector.selectByValue(name);
        }catch(Exception e){
            try {
                Thread.sleep(20000);
                System.out.println("Exception: " + e);
                selector = new Select(element);
                selector.selectByValue(name);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
      /*  Select selector = new Select(holidaysFilter);*/
        waitThatFilterAndAllOptionsAreLoaded(element, list);
       /* selector.selectByValue(name);*/
    }

   public void openElementByLocator(WebElement element) throws InterruptedException {
       Thread.sleep(3000);
       waitUntilElementIsClickable(element, 90);
       element.click();
    // Thread.sleep(i);
   }

    public void openElementById(String id){
        waitUntilElementIsClickable(By.id(id), 30);
        driver.findElement(By.id(id)).click();
        // Thread.sleep(i);
    }

    public String[] saveDataFromTheProfileOrFamilyPage(Boolean sign) {

        String [] profileData;
        profileData = new String[6];
        //---------------Save data from the Profile page-------------
        //profileConfession
        profileData[0] = driver.findElement(By.id("fieldobjconfession")).getText();

        //profileLanguage
        profileData[1] = driver.findElement(By.id("fieldobjlanguages")).getText();

        //profileFoodPreference
        profileData[2] = driver.findElement(By.id("fieldobjfoodPreferences")).getText();

        //profileEmail
        profileData[3] = driver.findElement(By.id("fieldobjelMail")).getText();

        if (sign) {
            //profileFamilyName
            profileData[4] = driver.findElement(By.id("fieldobjfamilyName")).getText();
        }
        else {
            //profileName
            profileData[4]  = driver.findElement(By.id("titleprofile")).getText();
        }

        //profileFamilyDescription
        profileData[5] = driver.findElement(By.id("fieldobjfamilyDescription")).getText();

        return profileData;
    }

    public void scrollPageUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
    }
    public void scrollPageDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript ("window.scrollTo (0, document.body.scrollHeight)");
    }
}
