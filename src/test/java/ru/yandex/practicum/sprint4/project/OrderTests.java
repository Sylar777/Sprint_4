package ru.yandex.practicum.sprint4.project;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.practicum.sprint4.project.PageObjects.HomePage;
import ru.yandex.practicum.sprint4.project.PageObjects.OrderPage;

import java.io.File;
import java.time.Duration;

@RunWith(Parameterized.class)
public class OrderTests {

    private WebDriver driver;
    private HomePage homePage;
    private OrderPage orderPage;

    private String firstName;
    private String lastName;
    private String address;
    private String subwayStation;
    private String phoneNumber;
    private String date;
    private String dropdownRent;
    private String comments;

    public OrderTests(String firstName,String lastName,String address,
                    String subwayStation,String phoneNumber,String date,String dropdownRent,String comments){
        this.firstName=firstName;
        this.lastName=lastName;
        this.address=address;
        this.subwayStation=subwayStation;
        this.phoneNumber=phoneNumber;
        this.date=date;
        this.dropdownRent=dropdownRent;
        this.comments=comments;
    }

    @Parameterized.Parameters
    public static Object[][] getCities() {
        //Сгенерируй тестовые данные (нам нужно название городов и результат поиска)
        return new Object[][] {
            {"Имятест","Фамилиятест","Адресстест 3","Бульвар Рокоссовского","+7915234564","04.11.2022","сутки","тест коммент"},
            {"Васька","Пупкин","Мой адресс простой 3","Лихоборы","+7916233337","09.11.2021","семеро суток","тест коммент он и в Африке тест коммент"}
        };
    }

    @Before
    public void setUp() {

            // Chrome
        ChromeDriverService chromeService = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("/Users/daniilsosnovsky/Documents/YandexPracticum/Sprint4/final_project_sprint4/finalproject/chromedriver"))
                .build();
        driver = new ChromeDriver(chromeService);
        driver.get("https://qa-scooter.praktikum-services.ru/");

            // Firefox
        // driver = new FirefoxDriver(); //Creating an object of FirefoxDriver
        // driver.manage().window().maximize();
        // driver.manage().deleteAllCookies();
        // driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        // driver.get("https://qa-scooter.praktikum-services.ru/");

        /* FirefoxDriverService firefoxService ... To be continue
        * https://www.programcreek.com/java-api-examples/org.openqa.selenium.firefox.FirefoxDriver
        * Example #2 - looks good


        https://www.browserstack.com/guide/run-selenium-tests-using-firefox-driver
        */

        homePage = new HomePage(driver);
        orderPage = new OrderPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkPositiveFlowThroughUpperButtonAndBlackPearlCheckbox(){
        homePage.clickUpperOrderButton();
        orderPage.waitTillPageLoaded();
        orderPage.setInputFirstName(firstName);
        orderPage.setInputLastName(lastName);
        orderPage.setInputAddress(address);
        orderPage.selectPicklistSubwayStation(subwayStation);
        orderPage.setInputPhoneNumber(phoneNumber);
        orderPage.clickContinueButton();
        orderPage.waitTillNewFieldsWillBeAvailable();
        orderPage.setDatePicker(date);
        orderPage.setDropdownRent(dropdownRent);
        orderPage.selectBlackPearlCheckbox();
        orderPage.setInputComments(comments);
        orderPage.clickOrderButtons();
        orderPage.clickYesButtons();
        orderPage.checkThatOrderSubmited();
    }

    @Test
    public void checkPositiveFlowThroughBottomButtonAndGrayHopelessnessCheckbox(){
        homePage.clickUpperOrderButton();
        orderPage.waitTillPageLoaded();
        orderPage.setInputFirstName(firstName);
        orderPage.setInputLastName(lastName);
        orderPage.setInputAddress(address);
        orderPage.selectPicklistSubwayStation(subwayStation);
        orderPage.setInputPhoneNumber(phoneNumber);
        orderPage.clickContinueButton();
        orderPage.waitTillNewFieldsWillBeAvailable();
        orderPage.setDatePicker(date);
        orderPage.setDropdownRent(dropdownRent);
        orderPage.selectGrayHopelessnessCheckbox();
        orderPage.setInputComments(comments);
        orderPage.clickOrderButtons();
        orderPage.clickYesButtons();
        orderPage.checkThatOrderSubmited();
    }
}
