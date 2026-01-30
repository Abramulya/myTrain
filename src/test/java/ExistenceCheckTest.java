import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import objects.AuthPage;
import objects.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExistenceCheckTest extends BaseTest {
    @Test
    @DisplayName("Проверка наличия элементов справочника 'Доступы'")
    @Story("2. Проверка наличия разделов и подразделов в меню")
    public void testAccessDir() {
        AuthPage authPage = new AuthPage(driver);
        MainPage mainPage = new MainPage(driver);
        authPage.open();
        authPage.login();
        driver.findElement(mainPage.getAccess()).click();
        String actualText = driver.findElement(mainPage.getUsers()).getText();
        assertEquals("Пользователи", actualText,
                "Текст элемента должен быть 'Пользователи'");
        actualText = driver.findElement(mainPage.getRoles()).getText();
        assertEquals("Роли", actualText,
                "Текст элемента должен быть 'Роли'");
        actualText = driver.findElement(mainPage.getApprove()).getText();
        assertEquals("Разрешения", actualText,
                "Текст элемента должен быть 'Разрешения'");
        actualText = driver.findElement(mainPage.getUsersGroups()).getText();
        assertEquals("Группы пользователей", actualText,
                "Текст элемента должен быть 'Группы пользователей'");
    }

    @Test
    @DisplayName("Проверка наличия элементов справочника 'Мониторинг'")
    @Story("2. Проверка наличия разделов и подразделов в меню")
    public void testLogDir() {
        AuthPage authPage = new AuthPage(driver);
        MainPage mainPage = new MainPage(driver);
        authPage.open();
        authPage.login();

        driver.findElement(mainPage.getLog()).click();
        String actualText = driver.findElement(mainPage.getMessages()).getText();
        assertEquals("Сообщения", actualText,
                "Текст элемента должен быть 'Сообщения'");
        actualText = driver.findElement(mainPage.getMetrics()).getText();
        assertEquals("Метрики", actualText,
                "Текст элемента должен быть 'Метрики'");
        actualText = driver.findElement(mainPage.getAlerts()).getText();
        assertEquals("Уведомления", actualText,
                "Текст элемента должен быть 'Уведомления'");
        actualText = driver.findElement(mainPage.getAudit()).getText();
        assertEquals("Аудит", actualText,
                "Текст элемента должен быть 'Аудит'");

        driver.findElement(mainPage.getMetrics()).click();
        actualText = driver.findElement(mainPage.getMetricsData()).getText();
        assertEquals("Метрики данные", actualText,
                "Текст элемента должен быть 'Метрики данные'");
        actualText = driver.findElement(mainPage.getMetricsSettings()).getText();
        assertEquals("Метрики настройка", actualText,
                "Текст элемента должен быть 'Метрики настройка'");

        driver.findElement(mainPage.getAlerts()).click();
        actualText = driver.findElement(mainPage.getAlertsGroups()).getText();
        assertEquals("Группы уведомлений", actualText,
                "Текст элемента должен быть 'Группы уведомлений'");
        actualText = driver.findElement(mainPage.getAlertsSettings()).getText();
        assertEquals("Настройка уведомлений", actualText,
                "Текст элемента должен быть 'Настройка уведомлений'");
        actualText = driver.findElement(mainPage.getAlertsArchive()).getText();
        assertEquals("Архив уведомлений", actualText,
                "Текст элемента должен быть 'Архив уведомлений'");

        driver.findElement(mainPage.getAudit()).click();
        actualText = driver.findElement(mainPage.getAuditDo()).getText();
        assertEquals("Аудит действий", actualText,
                "Текст элемента должен быть 'Аудит действий'");
        actualText = driver.findElement(mainPage.getAuditDoUz()).getText();
        assertEquals("Аудит действий УЗ", actualText,
                "Текст элемента должен быть 'Аудит действий УЗ'");
        actualText = driver.findElement(mainPage.getAuditAccess()).getText();
        assertEquals("Аудит доступов", actualText,
                "Текст элемента должен быть 'Аудит доступов'");
        actualText = driver.findElement(mainPage.getAuditChanges()).getText();
        assertEquals("Аудит изменений", actualText,
                "Текст элемента должен быть 'Аудит изменений'");
        actualText = driver.findElement(mainPage.getAuditSettings()).getText();
        assertEquals("Аудит настройки", actualText,
                "Текст элемента должен быть 'Аудит настройки'");
    }

    @Test
    @DisplayName("Проверка наличия элементов справочника 'Настройки'")
    @Story("2. Проверка наличия разделов и подразделов в меню")
    public void testSettingsDir() {
        AuthPage authPage = new AuthPage(driver);
        MainPage mainPage = new MainPage(driver);
        authPage.open();
        authPage.login();

        driver.findElement(mainPage.getSetting()).click();
        String actualText = driver.findElement(mainPage.getSystems()).getText();
        assertEquals("Системные", actualText,
                "Текст элемента должен быть 'Системные'");
        actualText = driver.findElement(mainPage.getManagerTasks()).getText();
        assertEquals("Менеджер задач", actualText,
                "Текст элемента должен быть 'Менеджер задач'");
        actualText = driver.findElement(mainPage.getGateways()).getText();
        assertEquals("Шлюзы", actualText,
                "Текст элемента должен быть 'Шлюзы'");
        actualText = driver.findElement(mainPage.getProviders()).getText();
        assertEquals("Провайдеры", actualText,
                "Текст элемента должен быть 'Провайдеры'");
        actualText = driver.findElement(mainPage.getVendors()).getText();
        assertEquals("Вендоры", actualText,
                "Текст элемента должен быть 'Вендоры'");
        actualText = driver.findElement(mainPage.getVendorsGroups()).getText();
        assertEquals("Группы вендоров", actualText,
                "Текст элемента должен быть 'Группы вендоров'");
        actualText = driver.findElement(mainPage.getRequestChannels()).getText();
        assertEquals("Каналы запросов", actualText,
                "Текст элемента должен быть 'Каналы запросов'");

        driver.findElement(mainPage.getManagerTasks()).click();
        actualText = driver.findElement(mainPage.getSchedulerTasks()).getText();
        assertEquals("Расписание задач", actualText,
                "Текст элемента должен быть 'Расписание задач'");
        actualText = driver.findElement(mainPage.getGroupsTasks()).getText();
        assertEquals("Группы задач", actualText,
                "Текст элемента должен быть 'Группы задач'");
        actualText = driver.findElement(mainPage.getHistoryTasks()).getText();
        assertEquals("История задач", actualText,
                "Текст элемента должен быть 'История задач'");

        driver.findElement(mainPage.getRequestChannels()).click();
        actualText = driver.findElement(mainPage.getGroupsChannels()).getText();
        assertEquals("Группы каналов", actualText,
                "Текст элемента должен быть 'Группы каналов'");
        actualText = driver.findElement(mainPage.getChannels()).getText();
        assertEquals("Каналы", actualText,
                "Текст элемента должен быть 'Каналы'");
    }

    @Test
    @DisplayName("Проверка наличия элементов справочника 'Сервисы'")
    @Story("2. Проверка наличия разделов и подразделов в меню")
    public void testServiceDir() {
        AuthPage authPage = new AuthPage(driver);
        MainPage mainPage = new MainPage(driver);
        authPage.open();
        authPage.login();
        driver.findElement(mainPage.getService()).click();
        String actualText = driver.findElement(mainPage.getMacros()).getText();
        assertEquals("Макросы", actualText,
                "Текст элемента должен быть 'Макросы'");
        actualText = driver.findElement(mainPage.getConstructReestr()).getText();
        assertEquals("Конструктор реестров", actualText,
                "Текст элемента должен быть 'Конструктор реестров'");
        actualText = driver.findElement(mainPage.getConstructRec()).getText();
        assertEquals("Конструктор сверок", actualText,
                "Текст элемента должен быть 'Конструктор сверок'");

        driver.findElement(mainPage.getConstructReestr()).click();
        actualText = driver.findElement(mainPage.getTemplatesReest()).getText();
        assertEquals("Шаблоны реестров", actualText,
                "Текст элемента должен быть 'Шаблоны реестров'");
        actualText = driver.findElement(mainPage.getArchiveReest()).getText();
        assertEquals("Архив реестров", actualText,
                "Текст элемента должен быть 'Архив реестров'");
        actualText = driver.findElement(mainPage.getSettingsReest()).getText();
        assertEquals("Настройка реестров", actualText,
                "Текст элемента должен быть 'Настройка реестров'");
        actualText = driver.findElement(mainPage.getSchedulerReest()).getText();
        assertEquals("Расписание реестров", actualText,
                "Текст элемента должен быть 'Расписание реестров'");

        driver.findElement(mainPage.getConstructRec()).click();
        actualText = driver.findElement(mainPage.getArmRec()).getText();
        assertEquals("АРМ сверки", actualText,
                "Текст элемента должен быть 'АРМ сверки'");
        actualText = driver.findElement(mainPage.getTemplatesActsRec()).getText();
        assertEquals("Шаблоны актов сверки", actualText,
                "Текст элемента должен быть 'Шаблоны актов сверки'");
        actualText = driver.findElement(mainPage.getTemplatesOtherRec()).getText();
        assertEquals("Шаблоны внешних реестров", actualText,
                "Текст элемента должен быть 'Шаблоны внешних реестров'");
        actualText = driver.findElement(mainPage.getSchedulerRec()).getText();
        assertEquals("Расписание сверок", actualText,
                "Текст элемента должен быть 'Расписание сверок'");
        actualText = driver.findElement(mainPage.getSettingsRec()).getText();
        assertEquals("Настройки сверки", actualText,
                "Текст элемента должен быть 'Настройки сверки'");
        actualText = driver.findElement(mainPage.getArchiveRec()).getText();
        assertEquals("Архив сверок", actualText,
                "Текст элемента должен быть 'Архив сверок'");
    }

    @Test
    @DisplayName("Проверка наличия элементов справочника 'Справочники'")
    @Story("2. Проверка наличия разделов и подразделов в меню")
    public void testDirectoryDir() {
        AuthPage authPage = new AuthPage(driver);
        MainPage mainPage = new MainPage(driver);
        authPage.open();
        authPage.login();
        driver.findElement(mainPage.getDirectory()).click();
        String actualText = driver.findElement(mainPage.getCurrency()).getText();
        assertEquals("Валюты", actualText,
                "Текст элемента должен быть 'Валюты'");
        actualText = driver.findElement(mainPage.getCommission()).getText();
        assertEquals("Комиссии", actualText,
                "Текст элемента должен быть 'Комиссии'");
        actualText = driver.findElement(mainPage.getWayPay()).getText();
        assertEquals("Способы оплаты", actualText,
                "Текст элемента должен быть 'Способы оплаты'");
        actualText = driver.findElement(mainPage.getMcc()).getText();
        assertEquals("МСС", actualText,
                "Текст элемента должен быть 'МСС'");
        actualText = driver.findElement(mainPage.getOktmo()).getText();
        assertEquals("Регионы и населенные пункты", actualText,
                "Текст элемента должен быть 'Регионы и населенные пункты'");
        actualText = driver.findElement(mainPage.getCategories()).getText();
        assertEquals("Категории товаров и услуг", actualText,
                "Текст элемента должен быть 'Категории товаров и услуг'");
        actualText = driver.findElement(mainPage.getSymbols()).getText();
        assertEquals("Кассовые символы", actualText,
                "Текст элемента должен быть 'Кассовые символы'");
        actualText = driver.findElement(mainPage.getFinSchemes()).getText();
        assertEquals("Финансовые схемы", actualText,
                "Текст элемента должен быть 'Финансовые схемы'");
        actualText = driver.findElement(mainPage.getLogSchemes()).getText();
        assertEquals("Схемы мониторинга", actualText,
                "Текст элемента должен быть 'Схемы мониторинга'");
        actualText = driver.findElement(mainPage.getPaymentTypes()).getText();
        assertEquals("Типы платежных устройств", actualText,
                "Текст элемента должен быть 'Типы платежных устройств'");
        actualText = driver.findElement(mainPage.getDataTypes()).getText();
        assertEquals("Тип данных интерфейсов", actualText,
                "Текст элемента должен быть 'Тип данных интерфейсов'");
        actualText = driver.findElement(mainPage.getPerefTypes()).getText();
        assertEquals("Типы периферии", actualText,
                "Текст элемента должен быть 'Типы периферии'");
        actualText = driver.findElement(mainPage.getPerefDevices()).getText();
        assertEquals("Периферийные устройства", actualText,
                "Текст элемента должен быть 'Периферийные устройства'");
        actualText = driver.findElement(mainPage.getDevicesGroups()).getText();
        assertEquals("Группы устройств", actualText,
                "Текст элемента должен быть 'Группы устройств'");
        actualText = driver.findElement(mainPage.getDevices()).getText();
        assertEquals("Устройства", actualText,
                "Текст элемента должен быть 'Устройства'");

        driver.findElement(mainPage.getMcc()).click();
        actualText = driver.findElement(mainPage.getMccGroups()).getText();
        assertEquals("МСС-Группы", actualText,
                "Текст элемента должен быть 'МСС-Группы'");
        actualText = driver.findElement(mainPage.getMccCodes()).getText();
        assertEquals("МСС-Коды", actualText,
                "Текст элемента должен быть 'МСС-Коды'");
    }
}
