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
@Feature("JIRAPROJECT-22010 - add advanced search page")
@Story("JIRAPROJECT-22000 - https://hh.ru")
@Owner("chilikinow@gmail.com")
@Tags({@Tag("headhunter"),@Tag("ui")})
@DisplayName("check advanced search page")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class HeadHunterTests extends UiTestBase {

    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://hh.ru";

    }

    @Test
    @DisplayName("part-time QA manual search test")
    @Severity(SeverityLevel.NORMAL)
    void createPartTimeQASearchTest() {

        new HeadHunter().openAdvancedSearchPage()
                .setItCategory()
                .setQaProfile()
                .setRemote()
                .setPartTimeEmployment()
                .clickToFind();

        step("check checked inputs in result", () -> {

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

    @Test
    @DisplayName("full-time QA manual search test")
    @Severity(SeverityLevel.NORMAL)
    void createFullTimeQASearchTest() {

        new HeadHunter().openAdvancedSearchPage()
                .setItCategory()
                .setQaProfile()
                .setRemote()
                .setFullTimeEmployment()
                .clickToFind();

        step("check checked inputs in result", () -> {

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

    @Test
    @DisplayName("temporary QA manual search test")
    @Severity(SeverityLevel.NORMAL)
    void createTemporaryJobQASearchTest() {

        new HeadHunter().openAdvancedSearchPage()
                .setItCategory()
                .setQaProfile()
                .setRemote()
                .setFullTimeEmployment()
                .setRegion("Москва")
                .clickToFind();

        step("check checked inputs in result", () -> {

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

    @Test
    @DisplayName("selenide from header search test")
    @Severity(SeverityLevel.NORMAL)
    void createSelenideFromSearchHeaderTest() {

        new HeadHunter().openAdvancedSearchPage()
                .setItCategory()
                .setQaProfile()
                .setRemote()
                .setRegion("Москва")
                .setTechToHeader("Selenide")
                .setNullExperience()
                .clickToFind();

        step("check checked inputs in result", () -> {

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

    @ValueSource(strings = {"Selenide", "Java", "Gradle"})
    @ParameterizedTest(name = "check headers parameter {arguments}")
    @Severity(SeverityLevel.NORMAL)
    void headersParameterTest(String arg) {

        new HeadHunter().openAdvancedSearchPage()
                .setTechToHeader(arg)
                .clickToFind();

        step("check headers parameter in result", () -> {

            $(".bloko-header-section-3").shouldHave(text(arg));

        });
    }

}
