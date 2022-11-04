package ru.yandex.practicum.sprint4.project.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;

    private By orderButtons = By.xpath(".//button[text()='Заказать']");
    private By accordionItems = By.className("accordion__button");
    private By accordionItemsText = By.xpath(".//div[@data-accordion-component = 'AccordionItemPanel']");

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void clickUpperOrderButton(){
        driver.findElements(orderButtons).get(0).click();
    }

    public void clickBottomOrderButton(){

        WebElement element = driver.findElements(orderButtons).get(1);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        driver.findElements(orderButtons).get(1).click();
    }

    public HomePage clickOnAccordionItem(int number){
        driver.findElements(accordionItems).get(number).click();
        return this;
    }

    public HomePage scrollToAccordionItem(int number){
        WebElement element = driver.findElements(accordionItems).get(number);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    public HomePage checkTextForAccordionItem(int number,String text){
        WebElement element = driver.findElements(accordionItemsText).get(number);

        new WebDriverWait(driver, Duration.ofSeconds(3))
        .until(ExpectedConditions.textToBePresentInElement(element,text));
        return this;
    }
}
