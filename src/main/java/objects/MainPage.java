package objects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final By accountIcon = By.xpath("/html/body/div[4]/div/div[1]/div/div[4]/div/button/span");
    private final By access = By.xpath(".//div/span[text()='Доступы']");
    private final By log = By.xpath(".//div/span[text()='Мониторинг']");
    private final By setting = By.xpath(".//div/span[text()='Настройки']");
    private final By service = By.xpath(".//div/span[text()='Сервисы']");
    private final By directory = By.xpath(".//div/span[text()='Справочники']");
    private final By users = By.xpath(".//div/span[text()='Пользователи']");
    private final By roles = By.xpath(".//div/span[text()='Роли']");
    private final By approve = By.xpath(".//div/span[text()='Разрешения']");
    private final By usersGroups = By.xpath(".//div/span[text()='Группы пользователей']");
    private final By messages = By.xpath(".//div/span[text()='Сообщения']");
    private final By metrics = By.xpath(".//div/span[text()='Метрики']");
    private final By alerts = By.xpath(".//div/span[text()='Уведомления']");
    private final By audit = By.xpath(".//div/span[text()='Аудит']");
    private final By metricsData = By.xpath(".//div/span[text()='Метрики данные']");
    private final By metricsSettings = By.xpath(".//div/span[text()='Метрики настройка']");
    private final By alertsGroups = By.xpath(".//div/span[text()='Группы уведомлений']");
    private final By alertsSettings = By.xpath(".//div/span[text()='Настройка уведомлений']");
    private final By alertsArchive = By.xpath(".//div/span[text()='Архив уведомлений']");
    private final By auditDo = By.xpath(".//div/span[text()='Аудит действий']");
    private final By auditDoUz = By.xpath(".//div/span[text()='Аудит действий УЗ']");
    private final By auditAccess = By.xpath(".//div/span[text()='Аудит доступов']");
    private final By auditChanges = By.xpath(".//div/span[text()='Аудит изменений']");
    private final By auditSettings = By.xpath(".//div/span[text()='Аудит настройки']");
    private final By systems =  By.xpath(".//div/span[text()='Системные']");
    private final By managerTasks =  By.xpath(".//div/span[text()='Менеджер задач']");
    private final By gateways =  By.xpath(".//div/span[text()='Шлюзы']");
    private final By providers =  By.xpath(".//div/span[text()='Провайдеры ']");
    private final By vendors =  By.xpath(".//div/span[text()='Вендоры ']");
    private final By vendorsGroups =  By.xpath(".//div/span[text()='Группы вендоров ']");
    private final By requestChannels = By.xpath(".//div/span[text()='Каналы запросов']");
    private final By schedulerTasks =  By.xpath(".//div/span[text()='Расписание задач']");
    private final By groupsTasks =  By.xpath(".//div/span[text()='Группы задач']");
    private final By historyTasks =  By.xpath(".//div/span[text()='История задач']");
    private final By groupsChannels = By.xpath(".//div/span[text()='Группы каналов']");
    private final By channels = By.xpath(".//div/span[text()='Каналы']");
    private final By macros = By.xpath(".//div/span[text()='Макросы']");
    private final By constructReestr = By.xpath(".//div/span[text()='Конструктор реестров']");
    private final By constructRec = By.xpath(".//div/span[text()='Конструктор сверок']");
    private final By templatesReest = By.xpath(".//div/span[text()='Шаблоны реестров']");
    private final By archiveReest = By.xpath(".//div/span[text()='Архив реестров']");
    private final By settingsReest = By.xpath(".//div/span[text()='Настройка реестров']");
    private final By schedulerReest = By.xpath(".//div/span[text()='Расписание реестров']");
    private final By armRec = By.xpath(".//div/span[text()='АРМ сверки']");
    private final By templatesActsRec = By.xpath(".//div/span[text()='Шаблоны актов сверки']");
    private final By templatesOtherRec = By.xpath(".//div/span[text()='Шаблоны внешних реестров']");
    private final By schedulerRec = By.xpath(".//div/span[text()='Расписание сверок']");
    private final By settingsRec = By.xpath(".//div/span[text()='Настройки сверки']");
    private final By archiveRec = By.xpath(".//div/span[text()='Архив сверок']");
    private final By currency = By.xpath(".//div/span[text()='Валюты']");
    private final By commission = By.xpath(".//div/span[text()='Комиссии']");
    private final By wayPay = By.xpath(".//div/span[text()='Способы оплаты']");
    private final By mcc = By.xpath(".//div/span[text()='МСС']");
    private final By oktmo = By.xpath(".//div/span[text()='Регионы и населенные пункты']");
    private final By categories = By.xpath(".//div/span[text()='Категории товаров и услуг']");
    private final By symbols = By.xpath(".//div/span[text()='Кассовые символы']");
    private final By finSchemes = By.xpath(".//div/span[text()='Финансовые схемы']");
    private final By logSchemes = By.xpath(".//div/span[text()='Схемы мониторинга']");
    private final By paymentTypes = By.xpath(".//div/span[text()='Типы платежных устройств']");
    private final By dataTypes = By.xpath(".//div/span[text()='Тип данных интерфейсов']");
    private final By perefTypes = By.xpath(".//div/span[text()='Типы периферии']");
    private final By perefDevices = By.xpath(".//div/span[text()='Периферийные устройства']");
    private final By devicesGroups = By.xpath(".//div/span[text()='Группы устройств']");
    private final By devices = By.xpath(".//div/span[text()='Устройства']");
    private final By mccGroups = By.xpath(".//div/span[text()='МСС-Группы']");
    private final By mccCodes = By.xpath(".//div/span[text()='МСС-Коды']");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public By getAccountIcon() {
        return accountIcon;
    }

    public By getAccess() {
        return access;
    }

    public By getLog() {
        return log;
    }

    public By getSetting() {
        return setting;
    }

    public By getService() {
        return service;
    }

    public By getDirectory() {
        return directory;
    }

    public By getRoles() {
        return roles;
    }

    public By getUsers() {
        return users;
    }

    public By getApprove() {
        return approve;
    }

    public By getUsersGroups() {
        return usersGroups;
    }

    public By getMessages() {
        return messages;
    }

    public By getMetrics() {
        return metrics;
    }

    public By getAlerts() {
        return alerts;
    }

    public By getAudit() {
        return audit;
    }

    public By getMetricsData() {
        return metricsData;
    }

    public By getMetricsSettings() {
        return metricsSettings;
    }

    public By getAlertsGroups() {
        return alertsGroups;
    }

    public By getAlertsSettings() {
        return alertsSettings;
    }

    public By getAlertsArchive() {
        return alertsArchive;
    }

    public By getAuditDo() {
        return auditDo;
    }

    public By getAuditDoUz() {
        return auditDoUz;
    }

    public By getAuditAccess() {
        return auditAccess;
    }

    public By getAuditChanges() {
        return auditChanges;
    }

    public By getAuditSettings() {
        return auditSettings;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public By getSystems() {
        return systems;
    }

    public By getManagerTasks() {
        return managerTasks;
    }

    public By getGateways() {
        return gateways;
    }

    public By getProviders() {
        return providers;
    }

    public By getVendors() {
        return vendors;
    }

    public By getVendorsGroups() {
        return vendorsGroups;
    }

    public By getRequestChannels() {
        return requestChannels;
    }

    public By getSchedulerTasks() {
        return schedulerTasks;
    }

    public By getGroupsTasks() {
        return groupsTasks;
    }

    public By getHistoryTasks() {
        return historyTasks;
    }

    public By getGroupsChannels() {
        return groupsChannels;
    }

    public By getChannels() {
        return channels;
    }

    public By getMacros() {
        return macros;
    }

    public By getConstructReestr() {
        return constructReestr;
    }

    public By getConstructRec() {
        return constructRec;
    }

    public By getTemplatesReest() {
        return templatesReest;
    }

    public By getArchiveReest() {
        return archiveReest;
    }

    public By getSettingsReest() {
        return settingsReest;
    }

    public By getSchedulerReest() {
        return schedulerReest;
    }

    public By getArmRec() {
        return armRec;
    }

    public By getTemplatesActsRec() {
        return templatesActsRec;
    }

    public By getTemplatesOtherRec() {
        return templatesOtherRec;
    }

    public By getSchedulerRec() {
        return schedulerRec;
    }

    public By getSettingsRec() {
        return settingsRec;
    }

    public By getArchiveRec() {
        return archiveRec;
    }

    public By getCurrency() {
        return currency;
    }

    public By getCommission() {
        return commission;
    }

    public By getWayPay() {
        return wayPay;
    }

    public By getMcc() {
        return mcc;
    }

    public By getOktmo() {
        return oktmo;
    }

    public By getCategories() {
        return categories;
    }

    public By getSymbols() {
        return symbols;
    }

    public By getFinSchemes() {
        return finSchemes;
    }

    public By getLogSchemes() {
        return logSchemes;
    }

    public By getPaymentTypes() {
        return paymentTypes;
    }

    public By getDataTypes() {
        return dataTypes;
    }

    public By getPerefTypes() {
        return perefTypes;
    }

    public By getPerefDevices() {
        return perefDevices;
    }

    public By getDevicesGroups() {
        return devicesGroups;
    }

    public By getDevices() {
        return devices;
    }

    public By getMccGroups() {
        return mccGroups;
    }

    public By getMccCodes() {
        return mccCodes;
    }
}
