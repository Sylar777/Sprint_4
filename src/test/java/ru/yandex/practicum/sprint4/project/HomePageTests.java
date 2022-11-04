package ru.yandex.practicum.sprint4.project;

import org.junit.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.sprint4.project.pageobjects.HomePage;

import java.io.File;
import java.time.Duration;

@RunWith(Parameterized.class)
public class HomePageTests {

    private WebDriver driver;
    private HomePage homePage;
    private int number;

    public HomePageTests(int number) {
        this.number = number;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}")
    public static Object[][] getCities() {
        // Сгенерируй тестовые данные (нам нужно название городов и результат поиска)
        return new Object[][] {
                { 0 },
                { 1 },
                { 2 },
                { 3 },
                { 4 },
                { 5 },
                { 6 },
                { 7 }
        };
    }

    @Before
    public void setUp() {

        // Chrome
        ChromeDriverService chromeService = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(
                        "/Users/daniilsosnovsky/Documents/YandexPracticum/Sprint4/final_project_sprint4/finalproject/chromedriver"))
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

        /*
         * FirefoxDriverService firefoxService ... To be continue
         * https://www.programcreek.com/java-api-examples/org.openqa.selenium.firefox.
         * FirefoxDriver
         * Example #2 - looks good
         *
         *
         * https://www.browserstack.com/guide/run-selenium-tests-using-firefox-driver
         */

        homePage = new HomePage(driver);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkTextValues() {
        homePage.scrollToAccordionItem(number)
                .clickOnAccordionItem(number)
                .checkTextForAccordionItem(number, СonstantValues.questionsTextList[number]);
    }
}
