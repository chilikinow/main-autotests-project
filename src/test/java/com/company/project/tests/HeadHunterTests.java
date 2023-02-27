package com.company.project.tests;

import com.codeborne.selenide.Configuration;
import com.company.project.base.UiTestBase;
import com.company.project.pages.HeadHunter;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.company.project.pages.HeadHunter.*;
import static io.qameta.allure.Allure.step;

@Slf4j
@Tag("ui")
@DisplayName("check advanced search page: https://hh.ru/search/vacancy/advanced")
public class HeadHunterTests extends UiTestBase {

    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://hh.ru";
    }

    @BeforeEach
    void setUp() {
        new HeadHunter().openAdvancedSearchPage();
    }

    @Feature("JIRAPROJECT-23011")
    @Story("JIRAPROJECT-23010")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Part-time QA manual search test")
    @Test
    void createPartTimeQASearchTest() {

        step("Set IT category", () -> {
            searchingForm.find(byText("Указать специализации")).click();
            $(".bloko-tree-selector-popup-content").find(byText("Информационные технологии"))
                    .ancestor("div").$(".bloko-tree-selector-item-spacer").click();
        });

        step("Set QA profile", () -> {
            $(".bloko-tree-selector__items").find(byText("Тестировщик")).click();
            submitButton.click();
        });

        step("Set remote", () -> {
            searchingForm.find(byText("Удаленная работа")).scrollTo().click();
        });

        step("Set part-time employment", () -> {
            searchingForm.find(byText("Неполный день")).scrollTo().click();
            searchingForm.find(byText("От 4 часов в день")).click();
            searchingForm.find(byText("По выходным")).click();
            searchingForm.find(byText("По вечерам")).click();
        });

        step("Check checked inputs in result", () -> {
            $(".search-submit-wrapper").find(byText("Найти")).click();
            $(".bloko-header-section-3").shouldHave(text("Найден"));

            employmentFindDiv.find(byText("Неполный день")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);

            employmentFindDiv.find(byText("От 4 часов в день")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);

            employmentFindDiv.find(byText("По выходным")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);

            employmentFindDiv.find(byText("По вечерам")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);

            employmentFindDiv.find(byText("Москва")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);

            employmentFindDiv.find(byText("Тестировщик")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);

            employmentFindDiv.find(byText("Удаленная работа")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
        });
    }

    @Feature("JIRAPROJECT-23011")
    @Story("JIRAPROJECT-23010")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Full-time QA manual search test")
    @Test
    void createFullTimeQASearchTest() {

        step("Set IT category", () -> {
            searchingForm.find(byText("Указать специализации")).click();
            $(".bloko-tree-selector-popup-content").find(byText("Информационные технологии"))
                    .ancestor("div").$(".bloko-tree-selector-item-spacer").click();
        });

        step("Set QA profile", () -> {
            $(".bloko-tree-selector__items").find(byText("Тестировщик")).click();
            submitButton.click();
        });

        step("Set remote", () -> {
            searchingForm.find(byText("Удаленная работа")).scrollTo().click();
        });

        step("Set full-time employment", () -> {
            searchingForm.find(byText("Полный день")).scrollTo().click();
            searchingForm.find(byText("Гибкий график")).click();
        });

        step("Check checked inputs in result", () -> {
            $(".search-submit-wrapper").find(byText("Найти")).click();
            $(".bloko-header-section-3").shouldHave(text("Найден"));

            employmentFindDiv.find(byText("Москва")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("Тестировщик")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("Полный день")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("Гибкий график")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("Удаленная работа")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
        });
    }

    @Feature("JIRAPROJECT-23011")
    @Story("JIRAPROJECT-23010")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Temporary QA manual search test")
    @Test
    void createTemporaryJobQASearchTest() {

        step("Set IT category", () -> {
            searchingForm.find(byText("Указать специализации")).click();
            $(".bloko-tree-selector-popup-content").find(byText("Информационные технологии"))
                    .ancestor("div").$(".bloko-tree-selector-item-spacer").click();
        });
        step("Set QA profile", () -> {
            $(".bloko-tree-selector__items").find(byText("Тестировщик")).click();
            submitButton.click();
        });
        step("Set region", () -> {
            searchingForm.find(byText("Москва")).ancestor(".bloko-tag-list")
                    .sibling(0).$(".bloko-input-text").val("Россия");
        });
        step("Set remote", () -> {
            searchingForm.find(byText("Удаленная работа")).scrollTo().click();
        });
        step("Set temporary employment", () -> {
            searchingForm.find(byText("Полный день")).scrollTo().click();
            searchingForm.find(byText("Гибкий график")).click();
        });
        step("Check checked inputs in result", () -> {
            $(".search-submit-wrapper").find(byText("Найти")).click();
            $(".bloko-header-section-3").shouldHave(text("Найден"));

            employmentFindDiv.find(byText("Россия")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("Тестировщик")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("Полный день")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("Гибкий график")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("Удаленная работа")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
        });
    }

    @Feature("JIRAPROJECT-23011")
    @Story("JIRAPROJECT-23010")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Selenide from header search test")
    @Test
    void createSelenideFromSearchHeaderTest() {

        step("Input Selenide to header", () -> {
            searchingForm.$$(".bloko-form-row").first().$(".bloko-input-text").val("Selenide");
        });

        step("Set IT category", () -> {
            searchingForm.find(byText("Указать специализации")).click();
            $(".bloko-tree-selector-popup-content").find(byText("Информационные технологии"))
                    .ancestor("div").$(".bloko-tree-selector-item-spacer").click();
        });

        step("Set QA profile", () -> {
            $(".bloko-tree-selector__items").find(byText("Тестировщик")).click();
            submitButton.click();
        });

        step("Set null experience", () -> {
            searchingForm.find(byText("Нет опыта")).scrollTo().click();
        });

        step("Set remote", () -> {
            searchingForm.find(byText("Удаленная работа")).scrollTo().click();
        });

        step("Check checked inputs in result", () -> {

            $(".search-submit-wrapper").find(byText("Найти")).click();
            $(".bloko-header-section-3").shouldHave(text("Selenide"));

            employmentFindDiv.find(byText("Москва")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("Тестировщик")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("Нет опыта")).ancestor("label")
                    .$(".bloko-radio__input").shouldBe(checked);
            employmentFindDiv.find(byText("Удаленная работа")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("В названии вакансии")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("В названии компании")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
            employmentFindDiv.find(byText("В описании вакансии")).ancestor("label")
                    .$(".bloko-checkbox__input").shouldBe(checked);
        });
    }

    @Feature("JIRAPROJECT-23011")
    @Story("JIRAPROJECT-23010")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @ValueSource(strings = {"Selenide", "Java", "Gradle"})
    @ParameterizedTest(name = "check headers parameter {arguments}")
    void headersParameterTest(String arg) {

        step("Input Selenide to header", () -> {

            searchingForm.$$(".bloko-form-row").first().$(".bloko-input-text").val(arg);

        });

        step("Check headers parameter in result", () -> {

            $(".search-submit-wrapper").find(byText("Найти")).click();
            $(".bloko-header-section-3").shouldHave(text(arg));

        });
    }

}
