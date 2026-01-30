import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import objects.AuthPage;
import objects.MainPage;

import org.openqa.selenium.By;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthTest extends BaseTest {
    @Test
    @DisplayName("Успешная авторизация пользователя и проверка наличия элементов главного меню")
    @Story("1. Проверка авторизации")
    public void testAuthSuccess() {
        AuthPage authPage = new AuthPage(driver);
        MainPage mainPage = new MainPage(driver);
        authPage.open();
        authPage.login();
        assertTrue(driver.findElement(mainPage.getAccountIcon()).isDisplayed(),
                "Иконка аккаунта должна отображаться на странице");
        String actualText = driver.findElement(mainPage.getAccess()).getText();
        assertEquals("Доступы", actualText,
                "Текст элемента должен быть 'Доступы'");
        actualText = driver.findElement(mainPage.getLog()).getText();
        assertEquals("Мониторинг", actualText,
                "Текст элемента должен быть 'Мониторинг'");
        actualText = driver.findElement(mainPage.getSetting()).getText();
        assertEquals("Настройки", actualText,
                "Текст элемента должен быть 'Настройки'");
        actualText = driver.findElement(mainPage.getService()).getText();
        assertEquals("Сервисы", actualText,
                "Текст элемента должен быть 'Сервисы'");
        actualText = driver.findElement(mainPage.getDirectory()).getText();
        assertEquals("Справочники", actualText,
                "Текст элемента должен быть 'Справочники'");
    }
}
