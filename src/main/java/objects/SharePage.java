package objects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;

public class SharePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private static class Selectors {
        static final By ERROR_MESSAGE = By.xpath(".//div/div/b[text()='ошибка:']");
        static final By FILTER = By.xpath(".//div/div[text()='Фильтр']");
        static final By BUTTON_FIRST_PAGE = By.xpath(".//button[@aria-label='Первая страница']");
        static final By BUTTON_LAST_PAGE = By.xpath(".//button[@aria-label='Последняя страница']");
        static final By BUTTON_PREVIOUS_PAGE = By.xpath(".//button[@aria-label='Предыдущая страница']");
        static final By BUTTON_NEXT_PAGE = By.xpath(".//button[@aria-label='Следующая страница']");
        static final By BUTTON_MENU = By.cssSelector("div button span.webix_icon.mdi.mdi-menu");

        // Дополнительные селекторы для проверки
        static final By LOADING_INDICATOR = By.cssSelector(".loading-indicator, .spinner");
        static final By SUCCESS_MESSAGE = By.cssSelector(".success-message, .alert-success");
        static final By WARNING_MESSAGE = By.cssSelector(".warning-message, .alert-warning");
    }

    public SharePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Основные проверки
    public boolean hasNoErrorMessages() {
        waitUntilPageLoaded();
        List<WebElement> errorMessages = driver.findElements(Selectors.ERROR_MESSAGE);
        return errorMessages.isEmpty();
    }

    public int getErrorCount() {
        return driver.findElements(Selectors.ERROR_MESSAGE).size();
    }

    public boolean isFilterVisible() {
        return isElementVisible(Selectors.FILTER);
    }

    public boolean isPaginationVisible() {
        return isElementVisible(Selectors.BUTTON_FIRST_PAGE) &&
                isElementVisible(Selectors.BUTTON_LAST_PAGE) &&
                isElementVisible(Selectors.BUTTON_NEXT_PAGE) &&
                isElementVisible(Selectors.BUTTON_PREVIOUS_PAGE);
    }

    public boolean isMenuButtonVisible() {
        return isElementVisible(Selectors.BUTTON_MENU);
    }

    // Проверки для разных типов страниц
    public PageCheckResult performFullPageCheck() {
        waitUntilPageLoaded();

        PageCheckResult result = new PageCheckResult();
        result.setFilterVisible(isFilterVisible());
        result.setErrorCount(getErrorCount());
        result.setFirstPageButtonVisible(isElementVisible(Selectors.BUTTON_FIRST_PAGE));
        result.setLastPageButtonVisible(isElementVisible(Selectors.BUTTON_LAST_PAGE));
        result.setNextPageButtonVisible(isElementVisible(Selectors.BUTTON_NEXT_PAGE));
        result.setPreviousPageButtonVisible(isElementVisible(Selectors.BUTTON_PREVIOUS_PAGE));
        result.setMenuButtonVisible(isMenuButtonVisible());

        return result;
    }

    public PageCheckResult performBasicPageCheck() {
        waitUntilPageLoaded();

        PageCheckResult result = new PageCheckResult();
        result.setFilterVisible(isFilterVisible());
        result.setErrorCount(getErrorCount());
        result.setFirstPageButtonVisible(isElementVisible(Selectors.BUTTON_FIRST_PAGE));
        result.setLastPageButtonVisible(isElementVisible(Selectors.BUTTON_LAST_PAGE));
        result.setNextPageButtonVisible(isElementVisible(Selectors.BUTTON_NEXT_PAGE));

        return result;
    }

    // Вспомогательные методы
    private boolean isElementVisible(By locator) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return driver.findElement(locator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    private void waitUntilPageLoaded() {
        // Ждем завершения загрузки
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));

        // Ждем исчезновения индикатора загрузки, если он есть
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(Selectors.LOADING_INDICATOR));
        } catch (TimeoutException e) {
            // Индикатора загрузки может и не быть - это нормально
        }
    }

    // Класс для хранения результатов проверки
    public static class PageCheckResult {
        private boolean filterVisible;
        private int errorCount;
        private boolean firstPageButtonVisible;
        private boolean lastPageButtonVisible;
        private boolean nextPageButtonVisible;
        private boolean previousPageButtonVisible;
        private boolean menuButtonVisible;

        // Геттеры и сеттеры
        public boolean isFilterVisible() { return filterVisible; }
        public void setFilterVisible(boolean filterVisible) { this.filterVisible = filterVisible; }

        public int getErrorCount() { return errorCount; }
        public void setErrorCount(int errorCount) { this.errorCount = errorCount; }

        public boolean isFirstPageButtonVisible() { return firstPageButtonVisible; }
        public void setFirstPageButtonVisible(boolean firstPageButtonVisible) { this.firstPageButtonVisible = firstPageButtonVisible; }

        public boolean isLastPageButtonVisible() { return lastPageButtonVisible; }
        public void setLastPageButtonVisible(boolean lastPageButtonVisible) { this.lastPageButtonVisible = lastPageButtonVisible; }

        public boolean isNextPageButtonVisible() { return nextPageButtonVisible; }
        public void setNextPageButtonVisible(boolean nextPageButtonVisible) { this.nextPageButtonVisible = nextPageButtonVisible; }

        public boolean isPreviousPageButtonVisible() { return previousPageButtonVisible; }
        public void setPreviousPageButtonVisible(boolean previousPageButtonVisible) { this.previousPageButtonVisible = previousPageButtonVisible; }

        public boolean isMenuButtonVisible() { return menuButtonVisible; }
        public void setMenuButtonVisible(boolean menuButtonVisible) { this.menuButtonVisible = menuButtonVisible; }

        public boolean isFullCheckPassed() {
            return filterVisible && errorCount == 0 && firstPageButtonVisible &&
                    lastPageButtonVisible && nextPageButtonVisible &&
                    previousPageButtonVisible && menuButtonVisible;
        }

        public boolean isBasicCheckPassed() {
            return filterVisible && errorCount == 0 && firstPageButtonVisible &&
                    lastPageButtonVisible && nextPageButtonVisible;
        }
    }

    // Геттеры (для обратной совместимости)
    public By getErrorMessage() { return Selectors.ERROR_MESSAGE; }
    public By getFilter() { return Selectors.FILTER; }
    public By getButtonFirstPage() { return Selectors.BUTTON_FIRST_PAGE; }
    public By getButtonLastPage() { return Selectors.BUTTON_LAST_PAGE; }
    public By getButtonPreviousPage() { return Selectors.BUTTON_PREVIOUS_PAGE; }
    public By getButtonNextPage() { return Selectors.BUTTON_NEXT_PAGE; }
    public By getButtonMenu() { return Selectors.BUTTON_MENU; }
}