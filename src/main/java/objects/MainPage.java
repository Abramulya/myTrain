package objects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы организованы по группам для лучшей читаемости
    private static class Selectors {
        // Основные разделы
        static final By ACCOUNT_ICON = By.xpath("/html/body/div[4]/div/div[1]/div/div[4]/div/button/span");
        static final By ACCESS = By.xpath(".//div/span[text()='Доступы']");
        static final By LOG = By.xpath(".//div/span[text()='Мониторинг']");
        static final By SETTING = By.xpath(".//div/span[text()='Настройки']");
        static final By SERVICE = By.xpath(".//div/span[text()='Сервисы']");
        static final By DIRECTORY = By.xpath(".//div/span[text()='Справочники']");

        // Подразделы "Доступы"
        static final By USERS = By.xpath(".//div/span[text()='Пользователи']");
        static final By ROLES = By.xpath(".//div/span[text()='Роли']");
        static final By APPROVE = By.xpath(".//div/span[text()='Разрешения']");
        static final By USERS_GROUPS = By.xpath(".//div/span[text()='Группы пользователей']");

        // Подразделы "Мониторинг"
        static final By MESSAGES = By.xpath(".//div/span[text()='Сообщения']");
        static final By METRICS = By.xpath(".//div/span[text()='Метрики']");
        static final By ALERTS = By.xpath(".//div/span[text()='Уведомления']");
        static final By AUDIT = By.xpath(".//div/span[text()='Аудит']");
        static final By METRICS_DATA = By.xpath(".//div/span[text()='Метрики данные']");
        static final By METRICS_SETTINGS = By.xpath(".//div/span[text()='Метрики настройка']");
        static final By ALERTS_GROUPS = By.xpath(".//div/span[text()='Группы уведомлений']");
        static final By ALERTS_SETTINGS = By.xpath(".//div/span[text()='Настройка уведомлений']");
        static final By ALERTS_ARCHIVE = By.xpath(".//div/span[text()='Архив уведомлений']");
        static final By AUDIT_DO = By.xpath(".//div/span[text()='Аудит действий']");
        static final By AUDIT_DO_UZ = By.xpath(".//div/span[text()='Аудит действий УЗ']");
        static final By AUDIT_ACCESS = By.xpath(".//div/span[text()='Аудит доступов']");
        static final By AUDIT_CHANGES = By.xpath(".//div/span[text()='Аудит изменений']");
        static final By AUDIT_SETTINGS = By.xpath(".//div/span[text()='Аудит настройки']");

        // Подразделы "Настройки"
        static final By SYSTEMS = By.xpath(".//div/span[text()='Системные']");
        static final By MANAGER_TASKS = By.xpath(".//div/span[text()='Менеджер задач']");
        static final By GATEWAYS = By.xpath(".//div/span[text()='Шлюзы']");
        static final By PROVIDERS = By.xpath(".//div/span[text()='Провайдеры ']");
        static final By VENDORS = By.xpath(".//div/span[text()='Вендоры ']");
        static final By VENDORS_GROUPS = By.xpath(".//div/span[text()='Группы вендоров ']");
        static final By REQUEST_CHANNELS = By.xpath(".//div/span[text()='Каналы запросов']");
        static final By SCHEDULER_TASKS = By.xpath(".//div/span[text()='Расписание задач']");
        static final By GROUPS_TASKS = By.xpath(".//div/span[text()='Группы задач']");
        static final By HISTORY_TASKS = By.xpath(".//div/span[text()='История задач']");
        static final By GROUPS_CHANNELS = By.xpath(".//div/span[text()='Группы каналов']");
        static final By CHANNELS = By.xpath(".//div/span[text()='Каналы']");

        // Подразделы "Сервисы"
        static final By MACROS = By.xpath(".//div/span[text()='Макросы']");
        static final By CONSTRUCT_REESTR = By.xpath(".//div/span[text()='Конструктор реестров']");
        static final By CONSTRUCT_REC = By.xpath(".//div/span[text()='Конструктор сверок']");
        static final By TEMPLATES_REEST = By.xpath(".//div/span[text()='Шаблоны реестров']");
        static final By ARCHIVE_REEST = By.xpath(".//div/span[text()='Архив реестров']");
        static final By SETTINGS_REEST = By.xpath(".//div/span[text()='Настройка реестров']");
        static final By SCHEDULER_REEST = By.xpath(".//div/span[text()='Расписание реестров']");
        static final By ARM_REC = By.xpath(".//div/span[text()='АРМ сверки']");
        static final By TEMPLATES_ACTS_REC = By.xpath(".//div/span[text()='Шаблоны актов сверки']");
        static final By TEMPLATES_OTHER_REC = By.xpath(".//div/span[text()='Шаблоны внешних реестров']");
        static final By SCHEDULER_REC = By.xpath(".//div/span[text()='Расписание сверок']");
        static final By SETTINGS_REC = By.xpath(".//div/span[text()='Настройки сверки']");
        static final By ARCHIVE_REC = By.xpath(".//div/span[text()='Архив сверок']");

        // Подразделы "Справочники"
        static final By CURRENCY = By.xpath(".//div/span[text()='Валюты']");
        static final By COMMISSION = By.xpath(".//div/span[text()='Комиссии']");
        static final By WAY_PAY = By.xpath(".//div/span[text()='Способы оплаты']");
        static final By MCC = By.xpath(".//div/span[text()='МСС']");
        static final By OKTMO = By.xpath(".//div/span[text()='Регионы и населенные пункты']");
        static final By CATEGORIES = By.xpath(".//div/span[text()='Категории товаров и услуг']");
        static final By SYMBOLS = By.xpath(".//div/span[text()='Кассовые символы']");
        static final By FIN_SCHEMES = By.xpath(".//div/span[text()='Финансовые схемы']");
        static final By LOG_SCHEMES = By.xpath(".//div/span[text()='Схемы мониторинга']");
        static final By PAYMENT_TYPES = By.xpath(".//div/span[text()='Типы платежных устройств']");
        static final By DATA_TYPES = By.xpath(".//div/span[text()='Тип данных интерфейсов']");
        static final By PEREF_TYPES = By.xpath(".//div/span[text()='Типы периферии']");
        static final By PEREF_DEVICES = By.xpath(".//div/span[text()='Периферийные устройства']");
        static final By DEVICES_GROUPS = By.xpath(".//div/span[text()='Группы устройств']");
        static final By DEVICES = By.xpath(".//div/span[text()='Устройства']");
        static final By MCC_GROUPS = By.xpath(".//div/span[text()='МСС-Группы']");
        static final By MCC_CODES = By.xpath(".//div/span[text()='МСС-Коды']");
    }

    // Карта для быстрого доступа к локаторам по имени
    private final Map<String, By> sectionSelectors = new HashMap<>();

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        initializeSelectors();
    }

    private void initializeSelectors() {
        // Основные разделы
        sectionSelectors.put("access", Selectors.ACCESS);
        sectionSelectors.put("log", Selectors.LOG);
        sectionSelectors.put("setting", Selectors.SETTING);
        sectionSelectors.put("service", Selectors.SERVICE);
        sectionSelectors.put("directory", Selectors.DIRECTORY);

        // Подразделы "Доступы"
        sectionSelectors.put("users", Selectors.USERS);
        sectionSelectors.put("roles", Selectors.ROLES);
        sectionSelectors.put("approve", Selectors.APPROVE);
        sectionSelectors.put("usersGroups", Selectors.USERS_GROUPS);

        // И так далее для всех остальных...
    }

    // Основные методы навигации
    public MainPage navigateToSection(String sectionName) {
        By selector = sectionSelectors.get(sectionName.toLowerCase());
        if (selector != null) {
            clickElement(selector);
            waitForPageLoad();
        } else {
            throw new IllegalArgumentException("Section not found: " + sectionName);
        }
        return this;
    }

    public MainPage navigateToSubsection(String subsectionName) {
        By selector = sectionSelectors.get(subsectionName.toLowerCase());
        if (selector != null) {
            clickElement(selector);
            waitForPageLoad();
        }
        return this;
    }

    // Быстрые методы для часто используемых разделов
    public MainPage openAccessDirectory() {
        return navigateToSection("access");
    }

    public MainPage openLogDirectory() {
        return navigateToSection("log");
    }

    public MainPage openSettingsDirectory() {
        return navigateToSection("setting");
    }

    public MainPage openServiceDirectory() {
        return navigateToSection("service");
    }

    // Методы для проверки видимости элементов
    public boolean isSectionVisible(String sectionName) {
        By selector = sectionSelectors.get(sectionName.toLowerCase());
        return selector != null && isElementVisible(selector);
    }

    public boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    // Вспомогательные методы
    private void clickElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    private void waitForPageLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }

    // Геттеры (оставлены для обратной совместимости с существующими тестами)
    public By getAccountIcon() { return Selectors.ACCOUNT_ICON; }
    public By getAccess() { return Selectors.ACCESS; }
    public By getLog() { return Selectors.LOG; }
    public By getSetting() { return Selectors.SETTING; }
    public By getService() { return Selectors.SERVICE; }
    public By getDirectory() { return Selectors.DIRECTORY; }

    // Подразделы "Доступы"
    public By getUsers() { return Selectors.USERS; }
    public By getRoles() { return Selectors.ROLES; }
    public By getApprove() { return Selectors.APPROVE; }
    public By getUsersGroups() { return Selectors.USERS_GROUPS; }

    // Подразделы "Мониторинг"
    public By getMessages() { return Selectors.MESSAGES; }
    public By getMetrics() { return Selectors.METRICS; }
    public By getAlerts() { return Selectors.ALERTS; }
    public By getAudit() { return Selectors.AUDIT; }
    public By getMetricsData() { return Selectors.METRICS_DATA; }
    public By getMetricsSettings() { return Selectors.METRICS_SETTINGS; }
    public By getAlertsGroups() { return Selectors.ALERTS_GROUPS; }
    public By getAlertsSettings() { return Selectors.ALERTS_SETTINGS; }
    public By getAlertsArchive() { return Selectors.ALERTS_ARCHIVE; }
    public By getAuditDo() { return Selectors.AUDIT_DO; }
    public By getAuditDoUz() { return Selectors.AUDIT_DO_UZ; }
    public By getAuditAccess() { return Selectors.AUDIT_ACCESS; }
    public By getAuditChanges() { return Selectors.AUDIT_CHANGES; }
    public By getAuditSettings() { return Selectors.AUDIT_SETTINGS; }

    // Подразделы "Настройки"
    public By getSystems() { return Selectors.SYSTEMS; }
    public By getManagerTasks() { return Selectors.MANAGER_TASKS; }
    public By getGateways() { return Selectors.GATEWAYS; }
    public By getProviders() { return Selectors.PROVIDERS; }
    public By getVendors() { return Selectors.VENDORS; }
    public By getVendorsGroups() { return Selectors.VENDORS_GROUPS; }
    public By getRequestChannels() { return Selectors.REQUEST_CHANNELS; }
    public By getSchedulerTasks() { return Selectors.SCHEDULER_TASKS; }
    public By getGroupsTasks() { return Selectors.GROUPS_TASKS; }
    public By getHistoryTasks() { return Selectors.HISTORY_TASKS; }
    public By getGroupsChannels() { return Selectors.GROUPS_CHANNELS; }
    public By getChannels() { return Selectors.CHANNELS; }

    // Подразделы "Сервисы"
    public By getMacros() { return Selectors.MACROS; }
    public By getConstructReestr() { return Selectors.CONSTRUCT_REESTR; }
    public By getConstructRec() { return Selectors.CONSTRUCT_REC; }
    public By getTemplatesReest() { return Selectors.TEMPLATES_REEST; }
    public By getArchiveReest() { return Selectors.ARCHIVE_REEST; }
    public By getSettingsReest() { return Selectors.SETTINGS_REEST; }
    public By getSchedulerReest() { return Selectors.SCHEDULER_REEST; }
    public By getArmRec() { return Selectors.ARM_REC; }
    public By getTemplatesActsRec() { return Selectors.TEMPLATES_ACTS_REC; }
    public By getTemplatesOtherRec() { return Selectors.TEMPLATES_OTHER_REC; }
    public By getSchedulerRec() { return Selectors.SCHEDULER_REC; }
    public By getSettingsRec() { return Selectors.SETTINGS_REC; }
    public By getArchiveRec() { return Selectors.ARCHIVE_REC; }

    // Подразделы "Справочники" (если они используются)
    public By getCurrency() { return Selectors.CURRENCY; }
    public By getCommission() { return Selectors.COMMISSION; }
    public By getWayPay() { return Selectors.WAY_PAY; }
    public By getMcc() { return Selectors.MCC; }
    public By getOktmo() { return Selectors.OKTMO; }
    public By getCategories() { return Selectors.CATEGORIES; }
    public By getSymbols() { return Selectors.SYMBOLS; }
    public By getFinSchemes() { return Selectors.FIN_SCHEMES; }
    public By getLogSchemes() { return Selectors.LOG_SCHEMES; }
    public By getPaymentTypes() { return Selectors.PAYMENT_TYPES; }
    public By getDataTypes() { return Selectors.DATA_TYPES; }
    public By getPerefTypes() { return Selectors.PEREF_TYPES; }
    public By getPerefDevices() { return Selectors.PEREF_DEVICES; }
    public By getDevicesGroups() { return Selectors.DEVICES_GROUPS; }
    public By getDevices() { return Selectors.DEVICES; }
    public By getMccGroups() { return Selectors.MCC_GROUPS; }
    public By getMccCodes() { return Selectors.MCC_CODES; }
}