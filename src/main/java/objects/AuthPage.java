package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class AuthPage {
    private final By loginField = By.xpath(".//input[@placeholder = 'Имя пользователя']");
    private final By passwordField = By.xpath(".//input[@placeholder = 'Пароль']");
    private final By loginButton = By.xpath("/html/body/div[4]/div/div[2]/div[2]/div/div[4]/div/div[2]/div/div/button");

    private final WebDriver driver;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("http://192.168.1.190:8081/");
    }

    public void login() {
        driver.findElement(loginField).sendKeys("TestProgon");
        driver.findElement(passwordField).sendKeys("123456789");
        driver.findElement(loginButton).click();
    }

}
