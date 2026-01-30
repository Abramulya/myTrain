package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AuthPage {
    private static final String BASE_URL = "http://192.168.1.190:8081/";
    private static final String USERNAME = "TestProgon";
    private static final String PASSWORD = "123456789";

    private final By loginField = By.xpath(".//input[@placeholder='Имя пользователя']");
    private final By passwordField = By.xpath(".//input[@placeholder='Пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public AuthPage open() {
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.urlToBe(BASE_URL));
        return this;
    }

    public MainPage login() {
        wait.until(ExpectedConditions.elementToBeClickable(loginField));
        driver.findElement(loginField).sendKeys(USERNAME);

        driver.findElement(passwordField).sendKeys(PASSWORD);
        driver.findElement(loginButton).click();

        // Ожидаем загрузку главной страницы после авторизации
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginButton));
        return new MainPage(driver);
    }

    public boolean isLoginPageDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}