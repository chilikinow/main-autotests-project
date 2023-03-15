package com.company.project.headhunter.tests;

import com.codeborne.selenide.Configuration;
import com.company.project.base.UiTestBase;
import com.company.project.headhunter.pages.HeadHunter;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@Slf4j
@Feature("JIRAPROJECT-22000 - https://hh.ru")
@Story("JIRAPROJECT-22010 - add advanced search page")
@Owner("chilikinow@gmail.com")
@Tags({@Tag("headhunter"),@Tag("ui")})
@DisplayName("check advanced search page")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class HeadHunterTests extends UiTestBase {

    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://hh.ru";
        Configuration.holdBrowserOpen = true;

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
                .clickToFind()

                .checkShouldHavefinded()
                .checkShouldBeNotFullDay()
                .checkShouldBeQACategory()
                .checkShouldBeCurrentRegion()
                .checkShouldBeRemoteWork()
                .checkShouldBeMoreFourHoure()
                .checkShouldBeOnWeekends()
                .checkShouldBeInTheEvenings();

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
                .clickToFind()

                .checkShouldHavefinded()
                .checkShouldBeFullDay()
                .checkShouldBeQACategory()
                .checkShouldBeCurrentRegion()
                .checkShouldBeRemoteWork()
                .checkShouldBeFlexibleWorkingHours();
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
                .clickToFind()

                .checkShouldHavefinded()
                .checkShouldBeFullDay()
                .checkShouldBeQACategory()
                .checkShouldBeCurrentRegion()
                .checkShouldBeRemoteWork()
                .checkShouldBeFlexibleWorkingHours();
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
                .clickToFind()

                .checkShouldBeQACategory()
                .checkShouldBeCurrentRegion()
                .checkShouldBeRemoteWork()
                .checkShouldBeCurrentTechToHeader()
                .checkShouldBeWithoutExperience();

    }

    @ValueSource(strings = {"Selenide", "Java", "Gradle"})
    @ParameterizedTest(name = "check headers parameter {arguments}")
    @Severity(SeverityLevel.NORMAL)
    void headersParameterTest(String arg) {

        new HeadHunter().openAdvancedSearchPage()
                .setTechToHeader(arg)
                .clickToFind()

                .checkShouldBeCurrentTechToHeader();

    }

}
