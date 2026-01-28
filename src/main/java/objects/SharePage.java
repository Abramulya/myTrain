package objects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SharePage {
    private final WebDriver driver;
    private final By errorMessage = By.xpath(".//div/div/b[text()='ошибка:']");
    private final By filter = By.xpath(".//div/div[text()='Фильтр']");
    private final By buttonFirstPage = By.xpath(".//div/div/button[@aria-label='Первая страница']");
    private final By buttonLastPage = By.xpath(".//div/div/button[@aria-label='Последняя страница']");
    private final By buttonPreviousPage = By.xpath(".//div/div/button[@aria-label='Предыдущая страница']");
    private final By buttonNextPage = By.xpath(".//div/div/button[@aria-label='Следующая страница']");
    private final By buttonMenu = By.cssSelector("div button span.webix_icon.mdi.mdi-menu");

    public SharePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public By getErrorMessage() {
        return errorMessage;
    }

    public By getFilter() {
        return filter;
    }

    public By getButtonFirstPage() {
        return buttonFirstPage;
    }

    public By getButtonLastPage() {
        return buttonLastPage;
    }

    public By getButtonPreviousPage() {
        return buttonPreviousPage;
    }

    public By getButtonNextPage() {
        return buttonNextPage;
    }

    public By getButtonMenu() {
        return buttonMenu;
    }
}
//ошибка:
       // /html/body/div[6]/div[1]/div/b
//html/body/div[6]/div[1]/div/text()
//html/body/div[5]/div/div[2]/div[3]/div/div/div[1]/div[1]/div[2]
//html/body/div[5]/div/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[1]/div[1]/button[2]
//html/body/div[5]/div/div[2]/div[3]/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/button/span