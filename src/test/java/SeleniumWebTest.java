import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumWebTest {
    WebDriver driver;
    private final String LOGIN = "kipohe9581@mahmul.com";
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
//        Нажать сохранить
//        Открыть https://otus.ru в “чистом браузере”
        driver.get("https://otus.ru");
        driver.quit();
        driver = new ChromeDriver();
//        Авторизоваться на сайте
        auth();
//        Войти в личный кабинет
        enterToLK();
//        Проверить, что в разделе "О себе" отображаются указанные ранее данные
    }
    private void auth() {
        driver.findElement(By.cssSelector(".sc-mrx253-0")).click();
        driver.findElement(By.cssSelector(".sc-rq8xzv-3")).sendKeys(LOGIN);
        driver.findElement(By.cssSelector(".sc-rq8xzv-5")).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector(".sc-9a4spb-2")).submit();
    }
    private void enterToLK() {

    }
}
