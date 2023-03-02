package com.company.project.gitrepo.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.company.project.base.UiTestBase;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@Slf4j
@Tag("gitrepo")
@DisplayName("check git repo https://github.com/eroshenkoam/allure-example")
public class CurrentGitRepoTests extends UiTestBase{

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
    }

    @ValueSource(ints = {76})
    @ParameterizedTest(name = "check issue exist on repo")
    @Feature("JIRAPROJECT-24268")
    @Story("JIRAPROJECT-23076")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    void checkDefiniteExistOnRepo(int issueNumber) {

        final String REPOSITORY = "eroshenkoam/allure-example";

        step("Открываем страницу", () -> {
            open("/");
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
        step("Проверяем наличие задачи с номером " + issueNumber, () -> {
            $(withText("#" + issueNumber)).shouldBe(Condition.visible);
        });
    }
}