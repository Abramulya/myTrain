import io.qameta.allure.Step;
import io.qameta.allure.Story;
import objects.SharePage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import objects.AuthPage;
import objects.MainPage;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HealthCheckTest extends BaseTest {
    @Test
    @DisplayName("Проверка работы подразделов справочника 'Доступы'")
    @Story("3. Проверка работы подразделов")
    public void testAccessDir() {
        MainPage mainPage = new MainPage(driver);
        AuthPage authPage = new AuthPage(driver);
        authPage.open();
        authPage.login();
        driver.findElement(mainPage.getAccess()).click();
        users();
        roles();
        approve();
        usersGroups();
    }

    @Step("Проверка подраздела 'Пользователи'")
    public void users() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getUsers()).click();
        healthCheck();
    }

    @Step("Проверка подраздела 'Роли'")
    public void roles() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getRoles()).click();
        healthCheck();
    }

    @Step("Проверка подраздела 'Разрешения'")
    public void approve() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getApprove()).click();
        healthCheck();
    }

    @Step("Проверка подраздела 'Группы пользователей'")
    public void usersGroups() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getUsersGroups()).click();
        healthCheck();
    }

    @Test
    @DisplayName("Проверка работы подразделов справочника 'Мониторинг'")
    @Story("3. Проверка работы подразделов")
    public void testLogDir() {
        MainPage mainPage = new MainPage(driver);
        AuthPage authPage = new AuthPage(driver);
        authPage.open();
        authPage.login();
        driver.findElement(mainPage.getLog()).click();
        driver.findElement(mainPage.getMetrics()).click();
        driver.findElement(mainPage.getAlerts()).click();
        driver.findElement(mainPage.getAudit()).click();
        messages();
        metricsData();
        metricsSettings();
        alertsGroups();
        alertsSettings();
        alertsArchive();
        auditDo();
        auditDoUz();
        auditAccess();
        auditChanges();
        auditSettings();
    }

    @Step("Проверка подраздела 'Сообщения'")
    public void messages() {
        MainPage mainPage = new MainPage(driver);
        SharePage sharePage = new SharePage(driver);
        driver.findElement(mainPage.getMessages()).click();
        assertTrue(driver.findElement(sharePage.getFilter()).isDisplayed(),
                "Фильтр должен отображаться на странице");
        List<WebElement> errorMessages = driver.findElements(sharePage.getErrorMessage());
        assertTrue(errorMessages.isEmpty(),
                "На странице не должно быть сообщений об ошибках. Найдено: " + errorMessages.size());
    }

    @Step("Проверка подраздела 'Метрики данные'")
    public void metricsData() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getMetricsData()).click();
    }

    @Step("Проверка подраздела 'Метрики настройка'")
    public void metricsSettings() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getMetricsSettings()).click();
        healthCheck();
    }

    @Step("Проверка подраздела 'Группы уведомлений'")
    public void alertsGroups() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getAlertsGroups()).click();
        healthCheck();
    }

    @Step("Проверка подраздела 'Настройка уведомлений'")
    public void alertsSettings() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getAlertsSettings()).click();
        healthCheck();
    }

    @Step("Проверка подраздела 'Архив уведомлений'")
    public void alertsArchive() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getAlertsArchive()).click();
       // healthCheck();
    }

    @Step("Проверка подраздела 'Аудит действий'")
    public void auditDo() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getAuditDo()).click();
       // healthCheck();
    }

    @Step("Проверка подраздела 'Аудит действий УЗ'")
    public void auditDoUz() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getAuditDoUz()).click();
        //healthCheck();
    }

    @Step("Проверка подраздела 'Аудит доступов'")
    public void auditAccess() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getAuditAccess()).click();
        //healthCheck();
    }

    @Step("Проверка подраздела 'Аудит изменений'")
    public void auditChanges() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getAuditChanges()).click();
        //healthCheck();
    }

    @Step("Проверка подраздела 'Аудит настройки'")
    public void auditSettings() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getAuditSettings()).click();
        //healthCheck();
    }

    @Test
    @DisplayName("Проверка работы подразделов справочника 'Мониторинг'")
    @Story("3. Проверка работы подразделов")
    public void testSettingsDir() {
        MainPage mainPage = new MainPage(driver);
        AuthPage authPage = new AuthPage(driver);
        authPage.open();
        authPage.login();
        driver.findElement(mainPage.getSetting()).click();
        driver.findElement(mainPage.getManagerTasks()).click();
        driver.findElement(mainPage.getRequestChannels()).click();
        systems();
        schedulerTasks();
        groupsTasks();
        historyTasks();
        gateways();
        providers();
        vendors();
        groupsVendors();
        channelsGroups();
        channels();
    }

    @Step("Проверка подраздела 'Системные'")
    public void systems() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getSystems()).click();
        //healthCheck();
    }

    @Step("Проверка подраздела 'Расписание задач'")
    public void schedulerTasks() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getSchedulerTasks()).click();
        healthCheck();
    }

    @Step("Проверка подраздела 'Группы задач'")
    public void groupsTasks() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getGroupsTasks()).click();
        healthCheck();
    }

    @Step("Проверка подраздела 'История задач'")
    public void historyTasks() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getHistoryTasks()).click();
        healthCheck();
    }

    @Step("Проверка подраздела 'Шлюзы'")
    public void gateways() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getGateways()).click();
        //healthCheck();
    }

    @Step("Проверка подраздела 'Провайдеры'")
    public void providers() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getProviders()).click();
        healthCheck();
    }

    @Step("Проверка подраздела 'Вендоры'")
    public void vendors() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getVendors()).click();
        healthCheck();
    }

    @Step("Проверка подраздела 'Группы вендоров'")
    public void groupsVendors() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getVendorsGroups()).click();
        healthCheck();
    }

    @Step("Проверка подраздела 'Группы каналов'")
    public void channelsGroups() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getGroupsChannels()).click();
        healthCheck();
    }

    @Step("Проверка подраздела 'Каналы'")
    public void channels() {
        MainPage mainPage = new MainPage(driver);
        driver.findElement(mainPage.getChannels()).click();
        healthCheck();
    }

    public void healthCheck() {
        SharePage sharePage = new SharePage(driver);
        assertTrue(driver.findElement(sharePage.getFilter()).isDisplayed(),
                "Фильтр должен отображаться на странице");
        List<WebElement> errorMessages = driver.findElements(sharePage.getErrorMessage());
        assertTrue(errorMessages.isEmpty(),
                "На странице не должно быть сообщений об ошибках. Найдено: " + errorMessages.size());
        assertTrue(driver.findElement(sharePage.getButtonFirstPage()).isDisplayed(),
                "Кнопка 'В начало' должна отображаться на странице");
        assertTrue(driver.findElement(sharePage.getButtonLastPage()).isDisplayed(),
                "Кнопка 'В конец' должна отображаться на странице");
        assertTrue(driver.findElement(sharePage.getButtonNextPage()).isDisplayed(),
                "Кнопка 'Следующая страница' должна отображаться на странице");
        assertTrue(driver.findElement(sharePage.getButtonPreviousPage()).isDisplayed(),
                "Кнопка 'Последняя страница' должна отображаться на странице");
        assertTrue(driver.findElement(sharePage.getButtonMenu()).isDisplayed(),
                "Кнопка 'Меню' должна отображаться на странице");
    }
}
