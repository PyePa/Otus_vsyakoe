import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumWebTest {
    WebDriver driver;
    private final String LOGIN = "jfk84026@zbock.com";
    private final String PASSWORD = "@WSX1qaz";

    @BeforeAll
    public static void webDriverSetup(){
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
    }

    @AfterEach
    public void setDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void someTest(){
//        Открыть https://otus.ru
        driver.get("https://otus.ru");
//        Авторизоваться на сайте
        auth();
//        Войти в личный кабинет
        enterToLK();
//        В разделе "О себе" заполнить все поля "Личные данные" и добавить не менее двух контактов
        cleanAndEnter(By.cssSelector("#id_fname"), "пивет");
        cleanAndEnter(By.cssSelector("#id_fname_latin"), "pivet");
        cleanAndEnter(By.cssSelector("#id_lname"), "андрей");
        cleanAndEnter(By.cssSelector("#id_lname_latin"), "andrey");
//        Нажать сохранить
        driver.findElement(By.cssSelector("body > div.body-wrapper > div > div.js-lk-cv > div.container.container-padding-bottom > div.container__row > div.container__col.container__col_9.container__col_md-8.container__col_sm-12.container__col_border-left.lk-rightbar.print-block.print-wide > div > form > div.container__row.container__row_gutter-24-gt-sm > div > div > button.button.button_md-4.button_blue.lk-cv-action-buttons__button.js-disable-on-submit")).click();
//        Открыть https://otus.ru в “чистом браузере”
        driver.get("https://otus.ru");
        driver.quit();
        driver = new FirefoxDriver();
//        Авторизоваться на сайте
        auth();
//        Войти в личный кабинет
        enterToLK();
//        Проверить, что в разделе "О себе" отображаются указанные ранее данные
        assertPlz(By.cssSelector("#id_fname"), "пивет");
        assertPlz(By.cssSelector("#id_fname_latin"), "pivet");
        assertPlz(By.cssSelector("#id_lname"), "андрей");
        assertPlz(By.cssSelector("#id_lname_latin"), "andrey");
    }

    @Test
    public void cookie() {
        driver.get("https://www.google.com");
//        Добавить Cookie#1 с параметром Otus1 и значением Value1
        driver.manage().addCookie(new Cookie("Cookie 1", "Otus1"));
//        Добавить Cookie#2 с параметром Otus2 и значением Value2
        driver.manage().addCookie(new Cookie("Cookie 2", "Otus2"));
//        Добавить Cookie#3 с параметром Otus3 и значением Value3 (добавлять через переменную, переменная должна быть сохранена)
        Cookie cookie = new Cookie ("Cookie 3", "Otus3");
        driver.manage().addCookie(cookie);
//        Добавить Cookie#4 с параметром Otus4 и значением Value4
        driver.manage().addCookie(new Cookie("Cookie 4", "Otus4"));
//        Вывести на экран все Cookies
        System.out.println(driver.manage().getCookies());
//        Вывести на экран Cookie1
        System.out.println(driver.manage().getCookieNamed("Cookie 1"));
//        Удалить Cookie#2 по имени куки
        driver.manage().deleteCookieNamed("Cookie 2");
//        Удалить Cookie#3 по переменной Cookie
        driver.manage().deleteCookie(cookie);
//        Удалить все куки, убедиться что их нет
        driver.manage().deleteAllCookies();
        Assertions.assertEquals(0, driver.manage().getCookies().size());
//                *Для тех, кому легко: добавить куки авторизации на Otus.ru (без использования getCookies)

    }







    private void auth() {
        driver.findElement(By.cssSelector(".sc-mrx253-0")).click();
        driver.findElement(By.cssSelector("#__PORTAL__ > div > div > div.sc-1alnis6-1.ejcuap > div.sc-1alnis6-4.iVBbVz > div > div.sc-10p60tv-1.eDzhKh > div.sc-10p60tv-2.bQGCmu > div > div.sc-19qj39o-0.iLmCeO > div > div.sc-rq8xzv-1.hGvqzc.sc-11ptd2v-1.liHMCp > div > input")).sendKeys(LOGIN);
        driver.findElement(By.cssSelector("#__PORTAL__ > div > div > div.sc-1alnis6-1.ejcuap > div.sc-1alnis6-4.iVBbVz > div > div.sc-10p60tv-1.eDzhKh > div.sc-10p60tv-2.bQGCmu > div > div.sc-19qj39o-0.iLmCeO > div > div.sc-rq8xzv-1.hGvqzc.sc-11ptd2v-1-Component.ciraFX > div > input")).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector("#__PORTAL__ > div > div > div.sc-1alnis6-1.ejcuap > div.sc-1alnis6-4.iVBbVz > div > div.sc-10p60tv-1.eDzhKh > div.sc-10p60tv-2.bQGCmu > div > button > div")).click();
    }
    private void enterToLK() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.get("https://otus.ru/lk/biography/personal/");
    }
    private void cleanAndEnter(By by, String text) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(text);
    }
    private void assertPlz(By by, String text) {
        Assertions.assertEquals(text, driver.findElement(by).getAttribute("value"));
        driver.findElement(by).sendKeys(text);
    }
}
