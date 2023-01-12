package com.company.project.cases;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.company.project.testdata.UITestBase;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@Slf4j
@Tag("main_page")
@DisplayName("main_page")
public class MainPageTests extends UITestBase {

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("main_page")
    void firstTest() {

        final String REPOSITORY = "eroshenkoam/allure-example";

        final int ISSUENUMBER = 76;

        step("Открываем страницу", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").setValue(REPOSITORY).submit();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем список задач", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем наличие задачи с номером " + ISSUENUMBER, () -> {
            $(withText("#" + ISSUENUMBER)).shouldBe(Condition.visible);
        });

    }

}
