import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import objects.AuthPage;
import objects.MainPage;
import objects.SharePage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Smoke Testing")
@Feature("Health Checks")
@Story("3. Проверка работы подразделов")
@DisplayName("Health Check Test Suite")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("Smoke")
@Tag("HealthCheck")
public class HealthCheckTest extends BaseTest {

    private MainPage mainPage;
    private AuthPage authPage;
    private SharePage sharePage;

    // Константы для конфигурации
    private static final int PAGE_LOAD_TIMEOUT = 10;
    private static final int IMPLICIT_WAIT = 5;

    @BeforeEach
    @Step("Инициализация теста")
    public void setUpTest() {
        mainPage = new MainPage(driver);
        authPage = new AuthPage(driver);
        sharePage = new SharePage(driver);
    }

    // ========== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ==========

    @Step("Выполнить авторизацию")
    private void performLogin() {
        authPage.open();
        MainPage loggedInPage = authPage.login();
        assertNotNull(loggedInPage, "Авторизация не выполнена");
        waitForPageLoad();
    }

    @Step("Перейти в раздел '{sectionName}'")
    private void navigateToSection(String sectionName, Runnable navigationAction) {
        try {
            navigationAction.run();
            waitForPageLoad();
            logStep(String.format("Успешно перешли в раздел '%s'", sectionName));
        } catch (TimeoutException e) {
            logError(String.format("Таймаут при переходе в раздел '%s'", sectionName));
            throw e;
        } catch (Exception e) {
            logError(String.format("Ошибка при переходе в раздел '%s': %s", sectionName, e.getMessage()));
            throw e;
        }
    }

    @Step("Проверить подраздел '{subsectionName}'")
    private void checkSubsection(String subsectionName, Runnable navigationAction, boolean fullCheck) {
        navigateToSection(subsectionName, navigationAction);

        if (fullCheck) {
            performExtendedPageCheck(subsectionName);
        } else {
            performBasicPageCheck(subsectionName);
        }
    }

    @Step("Выполнить расширенную проверку страницы '{pageName}'")
    private void performExtendedPageCheck(String pageName) {
        SharePage.PageCheckResult result = sharePage.performFullPageCheck();

        assertAll(
                () -> assertTrue(result.isFilterVisible(),
                        String.format("На странице '%s' фильтр не отображается", pageName)),
                () -> assertEquals(0, result.getErrorCount(),
                        String.format("На странице '%s' найдены ошибки: %d", pageName, result.getErrorCount())),
                () -> assertTrue(result.isFirstPageButtonVisible(),
                        String.format("На странице '%s' кнопка 'В начало' не отображается", pageName)),
                () -> assertTrue(result.isLastPageButtonVisible(),
                        String.format("На странице '%s' кнопка 'В конец' не отображается", pageName)),
                () -> assertTrue(result.isNextPageButtonVisible(),
                        String.format("На странице '%s' кнопка 'Следующая страница' не отображается", pageName)),
                () -> assertTrue(result.isPreviousPageButtonVisible(),
                        String.format("На странице '%s' кнопка 'Предыдущая страница' не отображается", pageName)),
                () -> assertTrue(result.isMenuButtonVisible(),
                        String.format("На странице '%s' кнопка 'Меню' не отображается", pageName))
        );

        logStep(String.format("Расширенная проверка страницы '%s' пройдена успешно", pageName));
    }

    @Step("Выполнить базовую проверку страницы '{pageName}'")
    private void performBasicPageCheck(String pageName) {
        SharePage.PageCheckResult result = sharePage.performBasicPageCheck();

        assertAll(
                () -> assertTrue(result.isFilterVisible(),
                        String.format("На странице '%s' фильтр не отображается", pageName)),
                () -> assertEquals(0, result.getErrorCount(),
                        String.format("На странице '%s' найдены ошибки: %d", pageName, result.getErrorCount())),
                () -> assertTrue(result.isFirstPageButtonVisible(),
                        String.format("На странице '%s' кнопка 'В начало' не отображается", pageName)),
                () -> assertTrue(result.isLastPageButtonVisible(),
                        String.format("На странице '%s' кнопка 'В конец' не отображается", pageName)),
                () -> assertTrue(result.isNextPageButtonVisible(),
                        String.format("На странице '%s' кнопка 'Следующая страница' не отображается", pageName))
        );

        logStep(String.format("Базовая проверка страницы '%s' пройдена успешно", pageName));
    }

    @Step("Ожидание загрузки страницы")
    private void waitForPageLoad() {
        try {
            Thread.sleep(500); // Краткая пауза для стабильности AJAX-приложений
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void logStep(String message) {
        System.out.println("[STEP] " + message);
    }

    private void logError(String message) {
        System.err.println("[ERROR] " + message);
    }

    // ========== ТЕСТЫ ДЛЯ СПРАВОЧНИКА "ДОСТУПЫ" ==========

    @Nested
    @DisplayName("Справочник 'Доступы'")
    @Feature("Access Directory")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class AccessDirectoryTests {

        @BeforeEach
        @Step("Подготовка: переход в справочник 'Доступы'")
        public void setUpAccessDirectory() {
            performLogin();
            navigateToSection("Доступы", () -> driver.findElement(mainPage.getAccess()).click());
        }

        @Test
        @Order(1)
        @DisplayName("TC-001: Проверка подраздела 'Пользователи'")
        @TmsLink("ACCESS-001")
        public void testUsersSubsection() {
            checkSubsection("Пользователи",
                    () -> driver.findElement(mainPage.getUsers()).click(),
                    true);
        }

        @Test
        @Order(2)
        @DisplayName("TC-002: Проверка подраздела 'Роли'")
        @TmsLink("ACCESS-002")
        public void testRolesSubsection() {
            checkSubsection("Роли",
                    () -> driver.findElement(mainPage.getRoles()).click(),
                    true);
        }

        @Test
        @Order(3)
        @DisplayName("TC-003: Проверка подраздела 'Разрешения'")
        @TmsLink("ACCESS-003")
        public void testApproveSubsection() {
            checkSubsection("Разрешения",
                    () -> driver.findElement(mainPage.getApprove()).click(),
                    true);
        }

        @Test
        @Order(4)
        @DisplayName("TC-004: Проверка подраздела 'Группы пользователей'")
        @TmsLink("ACCESS-004")
        public void testUsersGroupsSubsection() {
            checkSubsection("Группы пользователей",
                    () -> driver.findElement(mainPage.getUsersGroups()).click(),
                    true);
        }
    }

    // ========== ТЕСТЫ ДЛЯ СПРАВОЧНИКА "МОНИТОРИНГ" ==========

    @Nested
    @DisplayName("Справочник 'Мониторинг'")
    @Feature("Monitoring Directory")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class LogDirectoryTests {

        @BeforeEach
        @Step("Подготовка: переход в справочник 'Мониторинг'")
        public void setUpLogDirectory() {
            performLogin();
            navigateToSection("Мониторинг", () -> driver.findElement(mainPage.getLog()).click());
        }

        @Test
        @Order(1)
        @DisplayName("TC-101: Проверка подраздела 'Сообщения'")
        @TmsLink("LOG-001")
        public void testMessagesSubsection() {
            checkSubsection("Сообщения",
                    () -> driver.findElement(mainPage.getMessages()).click(),
                    true);
        }

        @Test
        @Order(2)
        @DisplayName("TC-102: Проверка подраздела 'Метрики данные'")
        @TmsLink("LOG-002")
        public void testMetricsDataSubsection() {
            driver.findElement(mainPage.getMetrics()).click();
            checkSubsection("Метрики данные",
                    () -> driver.findElement(mainPage.getMetricsData()).click(),
                    true);
        }

        @Test
        @Order(3)
        @DisplayName("TC-103: Проверка подраздела 'Метрики настройка'")
        @TmsLink("LOG-003")
        public void testMetricsSettingsSubsection() {
            driver.findElement(mainPage.getMetrics()).click();
            checkSubsection("Метрики настройка",
                    () -> driver.findElement(mainPage.getMetricsSettings()).click(),
                    true);
        }

        @Test
        @Order(4)
        @DisplayName("TC-104: Проверка подраздела 'Группы уведомлений'")
        @TmsLink("LOG-004")
        public void testAlertsGroupsSubsection() {
            driver.findElement(mainPage.getAlerts()).click();
            checkSubsection("Группы уведомлений",
                    () -> driver.findElement(mainPage.getAlertsGroups()).click(),
                    true);
        }

        @Test
        @Order(5)
        @DisplayName("TC-105: Проверка подраздела 'Настройка уведомлений'")
        @TmsLink("LOG-005")
        public void testAlertsSettingsSubsection() {
            driver.findElement(mainPage.getAlerts()).click();
            checkSubsection("Настройка уведомлений",
                    () -> driver.findElement(mainPage.getAlertsSettings()).click(),
                    true);
        }

        @Test
        @Order(6)
        @DisplayName("TC-106: Проверка подраздела 'Архив уведомлений'")
        @TmsLink("LOG-006")
        public void testAlertsArchiveSubsection() {
            driver.findElement(mainPage.getAlerts()).click();
            checkSubsection("Архив уведомлений",
                    () -> driver.findElement(mainPage.getAlertsArchive()).click(),
                    false);
        }

        @Test
        @Order(7)
        @DisplayName("TC-107: Проверка подраздела 'Аудит действий'")
        @TmsLink("LOG-007")
        public void testAuditDoSubsection() {
            driver.findElement(mainPage.getAudit()).click();
            checkSubsection("Аудит действий",
                    () -> driver.findElement(mainPage.getAuditDo()).click(),
                    false);
        }

        @Test
        @Order(8)
        @DisplayName("TC-108: Проверка подраздела 'Аудит действий УЗ'")
        @TmsLink("LOG-008")
        public void testAuditDoUzSubsection() {
            driver.findElement(mainPage.getAudit()).click();
            checkSubsection("Аудит действий УЗ",
                    () -> driver.findElement(mainPage.getAuditDoUz()).click(),
                    false);
        }

        @Test
        @Order(9)
        @DisplayName("TC-109: Проверка подраздела 'Аудит доступов'")
        @TmsLink("LOG-009")
        public void testAuditAccessSubsection() {
            driver.findElement(mainPage.getAudit()).click();
            checkSubsection("Аудит доступов",
                    () -> driver.findElement(mainPage.getAuditAccess()).click(),
                    false);
        }

        @Test
        @Order(10)
        @DisplayName("TC-110: Проверка подраздела 'Аудит изменений'")
        @TmsLink("LOG-010")
        public void testAuditChangesSubsection() {
            driver.findElement(mainPage.getAudit()).click();
            checkSubsection("Аудит изменений",
                    () -> driver.findElement(mainPage.getAuditChanges()).click(),
                    false);
        }

        @Test
        @Order(11)
        @DisplayName("TC-111: Проверка подраздела 'Аудит настройки'")
        @TmsLink("LOG-011")
        public void testAuditSettingsSubsection() {
            driver.findElement(mainPage.getAudit()).click();
            checkSubsection("Аудит настройки",
                    () -> driver.findElement(mainPage.getAuditSettings()).click(),
                    false);
        }
    }

    // ========== ТЕСТЫ ДЛЯ СПРАВОЧНИКА "НАСТРОЙКИ" ==========

    @Nested
    @DisplayName("Справочник 'Настройки'")
    @Feature("Settings Directory")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class SettingsDirectoryTests {

        @BeforeEach
        @Step("Подготовка: переход в справочник 'Настройки'")
        public void setUpSettingsDirectory() {
            performLogin();
            navigateToSection("Настройки", () -> driver.findElement(mainPage.getSetting()).click());
        }

        @Test
        @Order(1)
        @DisplayName("TC-201: Проверка подраздела 'Системные'")
        @TmsLink("SETTINGS-001")
        public void testSystemsSubsection() {
            checkSubsection("Системные",
                    () -> driver.findElement(mainPage.getSystems()).click(),
                    false);
        }

        @Test
        @Order(2)
        @DisplayName("TC-202: Проверка подраздела 'Расписание задач'")
        @TmsLink("SETTINGS-002")
        public void testSchedulerTasksSubsection() {
            driver.findElement(mainPage.getManagerTasks()).click();
            checkSubsection("Расписание задач",
                    () -> driver.findElement(mainPage.getSchedulerTasks()).click(),
                    true);
        }

        @Test
        @Order(3)
        @DisplayName("TC-203: Проверка подраздела 'Группы задач'")
        @TmsLink("SETTINGS-003")
        public void testGroupsTasksSubsection() {
            driver.findElement(mainPage.getManagerTasks()).click();
            checkSubsection("Группы задач",
                    () -> driver.findElement(mainPage.getGroupsTasks()).click(),
                    true);
        }

        @Test
        @Order(4)
        @DisplayName("TC-204: Проверка подраздела 'История задач'")
        @TmsLink("SETTINGS-004")
        public void testHistoryTasksSubsection() {
            driver.findElement(mainPage.getManagerTasks()).click();
            checkSubsection("История задач",
                    () -> driver.findElement(mainPage.getHistoryTasks()).click(),
                    true);
        }

        @Test
        @Order(5)
        @DisplayName("TC-205: Проверка подраздела 'Шлюзы'")
        @TmsLink("SETTINGS-005")
        public void testGatewaysSubsection() {
            checkSubsection("Шлюзы",
                    () -> driver.findElement(mainPage.getGateways()).click(),
                    false);
        }

        @Test
        @Order(6)
        @DisplayName("TC-206: Проверка подраздела 'Провайдеры'")
        @TmsLink("SETTINGS-006")
        public void testProvidersSubsection() {
            checkSubsection("Провайдеры",
                    () -> driver.findElement(mainPage.getProviders()).click(),
                    true);
        }

        @Test
        @Order(7)
        @DisplayName("TC-207: Проверка подраздела 'Вендоры'")
        @TmsLink("SETTINGS-007")
        public void testVendorsSubsection() {
            checkSubsection("Вендоры",
                    () -> driver.findElement(mainPage.getVendors()).click(),
                    true);
        }

        @Test
        @Order(8)
        @DisplayName("TC-208: Проверка подраздела 'Группы вендоров'")
        @TmsLink("SETTINGS-008")
        public void testGroupsVendorsSubsection() {
            checkSubsection("Группы вендоров",
                    () -> driver.findElement(mainPage.getVendorsGroups()).click(),
                    true);
        }

        @Test
        @Order(9)
        @DisplayName("TC-209: Проверка подраздела 'Группы каналов'")
        @TmsLink("SETTINGS-009")
        public void testChannelsGroupsSubsection() {
            driver.findElement(mainPage.getRequestChannels()).click();
            checkSubsection("Группы каналов",
                    () -> driver.findElement(mainPage.getGroupsChannels()).click(),
                    true);
        }

        @Test
        @Order(10)
        @DisplayName("TC-210: Проверка подраздела 'Каналы'")
        @TmsLink("SETTINGS-010")
        public void testChannelsSubsection() {
            driver.findElement(mainPage.getRequestChannels()).click();
            checkSubsection("Каналы",
                    () -> driver.findElement(mainPage.getChannels()).click(),
                    true);
        }
    }

    // ========== ТЕСТЫ ДЛЯ СПРАВОЧНИКА "СЕРВИСЫ" ==========

    @Nested
    @DisplayName("Справочник 'Сервисы'")
    @Feature("Service Directory")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class ServiceDirectoryTests {

        @BeforeEach
        @Step("Подготовка: переход в справочник 'Сервисы'")
        public void setUpServiceDirectory() {
            performLogin();
            navigateToSection("Сервисы", () -> driver.findElement(mainPage.getService()).click());
        }

        @Test
        @Order(1)
        @DisplayName("TC-301: Проверка подраздела 'Макросы'")
        @TmsLink("SERVICE-001")
        public void testMacrosSubsection() {
            checkSubsection("Макросы",
                    () -> driver.findElement(mainPage.getMacros()).click(),
                    false);
        }

        @Test
        @Order(2)
        @DisplayName("TC-302: Проверка подраздела 'Шаблоны реестров'")
        @TmsLink("SERVICE-002")
        public void testTemplatesReestrSubsection() {
            driver.findElement(mainPage.getConstructReestr()).click();
            checkSubsection("Шаблоны реестров",
                    () -> driver.findElement(mainPage.getTemplatesReest()).click(),
                    true);
        }

        @Test
        @Order(3)
        @DisplayName("TC-303: Проверка подраздела 'Архив реестров'")
        @TmsLink("SERVICE-003")
        public void testArchiveReestrSubsection() {
            driver.findElement(mainPage.getConstructReestr()).click();
            checkSubsection("Архив реестров",
                    () -> driver.findElement(mainPage.getArchiveReest()).click(),
                    false);
        }

        @Test
        @Order(4)
        @DisplayName("TC-304: Проверка подраздела 'Настройка реестров'")
        @TmsLink("SERVICE-004")
        public void testSettingsReestrSubsection() {
            driver.findElement(mainPage.getConstructReestr()).click();
            checkSubsection("Настройка реестров",
                    () -> driver.findElement(mainPage.getSettingsReest()).click(),
                    true);
        }

        @Test
        @Order(5)
        @DisplayName("TC-305: Проверка подраздела 'Расписание реестров'")
        @TmsLink("SERVICE-005")
        public void testSchedulerReestrSubsection() {
            driver.findElement(mainPage.getConstructReestr()).click();
            checkSubsection("Расписание реестров",
                    () -> driver.findElement(mainPage.getSchedulerReest()).click(),
                    true);
        }

        @Test
        @Order(6)
        @DisplayName("TC-306: Проверка подраздела 'АРМ сверки'")
        @TmsLink("SERVICE-006")
        public void testArmRecSubsection() {
            driver.findElement(mainPage.getConstructRec()).click();
            checkSubsection("АРМ сверки",
                    () -> driver.findElement(mainPage.getArmRec()).click(),
                    true);
        }

        @Test
        @Order(7)
        @DisplayName("TC-307: Проверка подраздела 'Шаблоны актов сверки'")
        @TmsLink("SERVICE-007")
        public void testTemplatesActsRecSubsection() {
            driver.findElement(mainPage.getConstructRec()).click();
            checkSubsection("Шаблоны актов сверки",
                    () -> driver.findElement(mainPage.getTemplatesActsRec()).click(),
                    true);
        }

        @Test
        @Order(8)
        @DisplayName("TC-308: Проверка подраздела 'Шаблоны внешних реестров'")
        @TmsLink("SERVICE-008")
        public void testTemplatesOtherRecSubsection() {
            driver.findElement(mainPage.getConstructRec()).click();
            checkSubsection("Шаблоны внешних реестров",
                    () -> driver.findElement(mainPage.getTemplatesOtherRec()).click(),
                    true);
        }

        @Test
        @Order(9)
        @DisplayName("TC-309: Проверка подраздела 'Расписание сверок'")
        @TmsLink("SERVICE-009")
        public void testSchedulerRecSubsection() {
            driver.findElement(mainPage.getConstructRec()).click();
            checkSubsection("Расписание сверок",
                    () -> driver.findElement(mainPage.getSchedulerRec()).click(),
                    true);
        }

        @Test
        @Order(10)
        @DisplayName("TC-310: Проверка подраздела 'Настройки сверки'")
        @TmsLink("SERVICE-010")
        public void testSettingsRecSubsection() {
            driver.findElement(mainPage.getConstructRec()).click();
            checkSubsection("Настройки сверки",
                    () -> driver.findElement(mainPage.getSettingsRec()).click(),
                    true);
        }

        @Test
        @Order(11)
        @DisplayName("TC-311: Проверка подраздела 'Архив сверок'")
        @TmsLink("SERVICE-011")
        public void testArchiveRecSubsection() {
            driver.findElement(mainPage.getConstructRec()).click();
            checkSubsection("Архив сверок",
                    () -> driver.findElement(mainPage.getArchiveRec()).click(),
                    false);
        }
    }

    // ========== ДОПОЛНИТЕЛЬНЫЕ ПРОВЕРКИ ==========

    @Nested
    @DisplayName("Дополнительные проверки")
    @Tag("Additional")
    class AdditionalChecks {

        @Test
        @DisplayName("Проверка наличия всех основных разделов")
        @Tag("Smoke")
        public void testMainSectionsAvailability() {
            performLogin();

            assertAll(
                    () -> assertTrue(driver.findElement(mainPage.getAccess()).isDisplayed(),
                            "Раздел 'Доступы' не отображается"),
                    () -> assertTrue(driver.findElement(mainPage.getLog()).isDisplayed(),
                            "Раздел 'Мониторинг' не отображается"),
                    () -> assertTrue(driver.findElement(mainPage.getSetting()).isDisplayed(),
                            "Раздел 'Настройки' не отображается"),
                    () -> assertTrue(driver.findElement(mainPage.getService()).isDisplayed(),
                            "Раздел 'Сервисы' не отображается")
            );
        }

        @Test
        @DisplayName("Проверка отображения иконки аккаунта")
        public void testAccountIconDisplayed() {
            performLogin();
            assertTrue(driver.findElement(mainPage.getAccountIcon()).isDisplayed(),
                    "Иконка аккаунта не отображается");
        }
    }
}