package com.company.project.headhunter.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.company.project.base.UiTestBase;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Slf4j
@Feature("JIRAPROJECT-22000 - https://hh.ru")
@Story("JIRAPROJECT-22010 - add advanced search page")
@Owner("chilikinow@gmail.com")
@Tags({@Tag("headhunter"),@Tag("ui")})
@DisplayName("check advanced search page")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class HeadHunterTests extends UiTestBase {

    String advancedSearchPageUrl = "/search/vacancy/advanced";
    private static SelenideElement searchingForm = $("[data-qa='advanced-vacancy-search__form']");
    public static SelenideElement submitButton = $(".bloko-modal-footer").find(byText("Выбрать"));
    public static SelenideElement employmentFindDiv = $(".novafilters");
    private static String region = "Москва";
    private static String tech = "Selenide";


    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://hh.ru";

    }

    @Test
    @DisplayName("part-time QA manual search test")
    @Severity(SeverityLevel.NORMAL)
    void createPartTimeQASearchTest() {

        step("Open Advanced Search page", () -> {

            open(advancedSearchPageUrl);

        });

        step("Set IT category", () -> {

            searchingForm.find(byText("Указать специализации")).scrollTo().click();
            $(".bloko-tree-selector-popup-content").find(byText("Информационные технологии"))
                    .ancestor("div").$(".bloko-tree-selector-item-spacer").scrollTo().click();

        });

        step("Set QA profile", () -> {

            $(".bloko-tree-selector__items").find(byText("Тестировщик")).scrollTo().click();
            submitButton.scrollTo().click();

        });

        step("Set remote", () -> {

            searchingForm.find(byText("Удаленная работа")).scrollTo().click();

        });

        step("Set part-time employment", () -> {

            searchingForm.find(byText("Неполный день")).scrollTo().click();
            searchingForm.find(byText("От 4 часов в день")).scrollTo().click();
            searchingForm.find(byText("По выходным")).scrollTo().click();
            searchingForm.find(byText("По вечерам")).scrollTo().click();

        });

        step("Click tp find", () -> {

            $(".search-submit-wrapper").find(byText("Найти")).scrollTo().click();

        });

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

        step("Open Advanced Search page", () -> {

            open(advancedSearchPageUrl);

        });

        step("Set IT category", () -> {

            searchingForm.find(byText("Указать специализации")).scrollTo().click();
            $(".bloko-tree-selector-popup-content").find(byText("Информационные технологии"))
                    .ancestor("div").$(".bloko-tree-selector-item-spacer").scrollTo().click();

        });

        step("Set QA profile", () -> {

            $(".bloko-tree-selector__items").find(byText("Тестировщик")).scrollTo().click();
            submitButton.scrollTo().click();

        });

        step("Set remote", () -> {

            searchingForm.find(byText("Удаленная работа")).scrollTo().click();

        });

        step("Set full-time employment", () -> {

            searchingForm.find(byText("Полный день")).scrollTo().click();
            searchingForm.find(byText("Гибкий график")).scrollTo().click();

        });

        step("Click tp find", () -> {

            $(".search-submit-wrapper").find(byText("Найти")).scrollTo().click();

        });

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

        step("Open Advanced Search page", () -> {

            open(advancedSearchPageUrl);

        });

        step("Set IT category", () -> {

            searchingForm.find(byText("Указать специализации")).scrollTo().click();
            $(".bloko-tree-selector-popup-content").find(byText("Информационные технологии"))
                    .ancestor("div").$(".bloko-tree-selector-item-spacer").scrollTo().click();

        });

        step("Set QA profile", () -> {

            $(".bloko-tree-selector__items").find(byText("Тестировщик")).scrollTo().click();
            submitButton.scrollTo().click();

        });

        step("Set remote", () -> {

            searchingForm.find(byText("Удаленная работа")).scrollTo().click();

        });

        step("Set full-time employment", () -> {

            searchingForm.find(byText("Полный день")).scrollTo().click();
            searchingForm.find(byText("Гибкий график")).scrollTo().click();

        });

        step("Set region", () -> {

            searchingForm.find(byText(region)).ancestor(".bloko-tag-list")
                    .sibling(0).$(".bloko-input-text").val(region);

        });

        step("Click tp find", () -> {

            $(".search-submit-wrapper").find(byText("Найти")).scrollTo().click();

        });

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

        step("Open Advanced Search page", () -> {

            open(advancedSearchPageUrl);

        });

        step("Set IT category", () -> {

            searchingForm.find(byText("Указать специализации")).scrollTo().click();
            $(".bloko-tree-selector-popup-content").find(byText("Информационные технологии"))
                    .ancestor("div").$(".bloko-tree-selector-item-spacer").scrollTo().click();

        });

        step("Set QA profile", () -> {

            $(".bloko-tree-selector__items").find(byText("Тестировщик")).scrollTo().click();
            submitButton.scrollTo().click();

        });

        step("Set remote", () -> {

            searchingForm.find(byText("Удаленная работа")).scrollTo().click();

        });

        step("Set region", () -> {

            searchingForm.find(byText(region)).ancestor(".bloko-tag-list")
                    .sibling(0).$(".bloko-input-text").val(region);

        });

        step("Set tech to header", () -> {

            searchingForm.$$(".bloko-form-row").first().$(".bloko-input-text").val(tech);

        });

        step("Set null experience", () -> {

            searchingForm.find(byText("Нет опыта")).scrollTo().click();

        });

        step("Click tp find", () -> {

            $(".search-submit-wrapper").find(byText("Найти")).scrollTo().click();

        });

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

        step("Open Advanced Search page", () -> {

            open(advancedSearchPageUrl);

        });

        step("Set tech to header", () -> {

            searchingForm.$$(".bloko-form-row").first().$(".bloko-input-text").val(arg);

        });

        step("Click tp find", () -> {

            $(".search-submit-wrapper").find(byText("Найти")).scrollTo().click();

        });

        step("check headers parameter in result", () -> {

            $(".bloko-header-section-3").shouldHave(text(arg));

        });
    }

}
