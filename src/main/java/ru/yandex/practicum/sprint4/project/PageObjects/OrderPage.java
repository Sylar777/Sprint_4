package ru.yandex.practicum.sprint4.project.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import static org.junit.Assert.*;
import java.time.Duration;

public class OrderPage {
    private WebDriver driver;

    private By inputFirstName = By.xpath(".//div[@class='Order_Form__17u6u']/div[1]/input");
    private By inputLastName = By.xpath(".//div[@class='Order_Form__17u6u']/div[2]/input");
    private By inputAddress = By.xpath(".//div[@class='Order_Form__17u6u']/div[3]/input");
    private By inputSubwayStation = By.className("select-search__input");
    //private By listOfSubwayStations = By.xpath(".//div[@class='select-search__options']");
    private By inputPhoneNumber = By.xpath(".//div[@class='Order_Form__17u6u']/div[5]/input");
    private By continueButton = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button");
    private By datePicker = By.xpath(".//div[@class='react-datepicker__input-container']/input");
    private By dropdownRent = By.className("Dropdown-placeholder");
    private By checkboxBlackPearl = By.id("black");
    private By checkboxGrayHopelessness = By.id("grey");
    private By inputComments = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private By orderButtons = By.className("Button_Middle__1CSJM"); //нужно поменять селектор
    private By yesButton = By.xpath(".//button[text()='Да']");
    private By textSuccessOrdering = By.className("Order_ModalHeader__3FDaJ");
    private By closeCookiesButton = By.className("App_CookieButton__3cvqF");

    public OrderPage(WebDriver driver){
        this.driver = driver;
    }

    public void waitTillPageLoaded(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
        .until(ExpectedConditions.elementToBeClickable(driver.findElement(inputFirstName)));
    }

    public void setInputFirstName(String firstName){
        driver.findElement(inputFirstName).sendKeys(firstName);
    }

    public void setInputLastName(String lastName){
        driver.findElement(inputLastName).sendKeys(lastName);
    }

    public void setInputAddress(String address){
        driver.findElement(inputAddress).sendKeys(address);
    }

    public void selectPicklistSubwayStation(String subwayStation){
        try{
            driver.findElement(By.className("select-search__value")).click();
            WebElement we = driver.findElement(By.xpath(String.format(".//div[@class='select-search__select']//div[text()='%s']/parent::button",subwayStation)));

            driver.findElement(inputSubwayStation).sendKeys(subwayStation);

            //((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", we);

            new WebDriverWait(driver, Duration.ofSeconds(3))
            .until(ExpectedConditions.elementToBeClickable(we));

            we.click();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void setInputPhoneNumber(String phoneNumber){
        driver.findElement(inputPhoneNumber).sendKeys(phoneNumber);
    }

    public OrderPage populateFirstPage(String firstName,String lastName,String address,String subwayStation,String phoneNumber){
        waitTillPageLoaded();
        setInputFirstName(firstName);
        setInputLastName(lastName);
        setInputAddress(address);
        selectPicklistSubwayStation(subwayStation);
        setInputPhoneNumber(phoneNumber);
        return this;
    }

    public OrderPage clickContinueButton(){

        WebElement element = driver.findElement(continueButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        new WebDriverWait(driver, Duration.ofSeconds(3))
        .until(ExpectedConditions.visibilityOfElementLocated(continueButton));

        driver.findElement(continueButton).click();
        return this;
    }

    public void waitTillNewFieldsWillBeAvailable(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
        .until(ExpectedConditions.visibilityOf(driver.findElement(datePicker)));
    }

    public void setDatePicker(String date){
        driver.findElement(datePicker).sendKeys(date);
        driver.findElement(datePicker).sendKeys(Keys.ENTER);
    }

    public void setDropdownRent(String dropdownRentValue){
        driver.findElement(closeCookiesButton).click();
        driver.findElement(dropdownRent).click();

        By rentValue = By.xpath(String.format(".//div[text()='%s']",dropdownRentValue));

        WebElement rentElement = driver.findElement(rentValue);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", rentElement );

        new WebDriverWait(driver, Duration.ofSeconds(3))
            .until(ExpectedConditions.visibilityOfElementLocated(rentValue));

        rentElement.click();
    }

    public OrderPage selectBlackPearlCheckbox(){
        assertTrue(driver.findElement(checkboxBlackPearl).isEnabled());
        driver.findElement(checkboxBlackPearl).click();
        return this;
    }

    public OrderPage selectGrayHopelessnessCheckbox(){
        assertTrue(driver.findElement(checkboxGrayHopelessness).isEnabled());
        driver.findElement(checkboxGrayHopelessness).click();
        return this;
    }

    public OrderPage setInputComments(String comments){
        driver.findElement(inputComments).sendKeys(comments);
        return this;
    }

    public OrderPage populateSecondPageWithOutCheckbox(String date,String dropdownRentValue,String comments){
        waitTillNewFieldsWillBeAvailable();
        setDatePicker(date);
        setDropdownRent(dropdownRentValue);
        setInputComments(comments);
        return this;
    }

    public OrderPage clickOrderButtons(){
        assertTrue(driver.findElement(orderButtons).isEnabled());
        driver.findElements(orderButtons).get(1).click();
        return this;
    }

    public OrderPage clickYesButtons(){
        assertTrue(driver.findElement(yesButton).isEnabled());
        driver.findElement(yesButton).click();
        return this;
    }

    public OrderPage checkThatOrderSubmited(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
        .until(ExpectedConditions.visibilityOf(driver.findElement(textSuccessOrdering)));
        return this;
    }

}
