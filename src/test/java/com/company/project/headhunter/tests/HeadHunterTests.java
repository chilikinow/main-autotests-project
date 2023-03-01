package com.company.project.headhunter.tests;

import com.codeborne.selenide.Configuration;
import com.company.project.base.UiTestBase;
import com.company.project.headhunter.pages.HeadHunter;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.company.project.headhunter.pages.HeadHunter.*;
import static io.qameta.allure.Allure.step;

@Slf4j
@Tag("headhunter")
@DisplayName("check advanced search page: https://hh.ru/search/vacancy/advanced")
public class HeadHunterTests extends UiTestBase {

    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://hh.ru";
    }

    @Feature("JIRAPROJECT-22010")
    @Story("JIRAPROJECT-22000")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("part-time QA manual search test")
    @Test
    void createPartTimeQASearchTest() {

        new HeadHunter().openAdvancedSearchPage()
                .setItCategory()
                .setQaProfile()
                .setRemote()
                .setPartTimeEmployment();

        step("check checked inputs in result", () -> {

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

    @Feature("JIRAPROJECT-22010")
    @Story("JIRAPROJECT-22000")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("full-time QA manual search test")
    @Test
    void createFullTimeQASearchTest() {

        new HeadHunter().openAdvancedSearchPage()
                .setItCategory()
                .setQaProfile()
                .setRemote()
                .setFullTimeEmployment();

        step("check checked inputs in result", () -> {

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

    @Feature("JIRAPROJECT-22013")
    @Story("JIRAPROJECT-22000")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("temporary QA manual search test")
    @Test
    void createTemporaryJobQASearchTest() {

        new HeadHunter().openAdvancedSearchPage()
                .setItCategory()
                .setQaProfile()
                .setRemote()
                .setFullTimeEmployment()
                .setRegion("Москва");


        step("check checked inputs in result", () -> {

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

    @Feature("JIRAPROJECT-22013")
    @Story("JIRAPROJECT-22000")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("selenide from header search test")
    @Test
    void createSelenideFromSearchHeaderTest() {

        new HeadHunter().openAdvancedSearchPage()
                .setItCategory()
                .setQaProfile()
                .setRemote()
                .setRegion("Москва")
                .setTechToHeader("Selenide")
                .setNullExperience();

        step("check checked inputs in result", () -> {

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

    @Feature("JIRAPROJECT-22015")
    @Story("JIRAPROJECT-22000")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.NORMAL)
    @ValueSource(strings = {"Selenide", "Java", "Gradle"})
    @ParameterizedTest(name = "check headers parameter {arguments}")
    void headersParameterTest(String arg) {

        new HeadHunter().openAdvancedSearchPage()
                .setTechToHeader(arg);

        step("check headers parameter in result", () -> {

            $(".search-submit-wrapper").find(byText("Найти")).click();
            $(".bloko-header-section-3").shouldHave(text(arg));

        });
    }

}
