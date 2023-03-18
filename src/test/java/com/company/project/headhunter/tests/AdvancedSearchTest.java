package com.company.project.headhunter.tests;

import com.codeborne.selenide.Configuration;
import com.company.project.base.UiTestBase;
import com.company.project.headhunter.pages.AdvancedSearchPage;
import com.company.project.headhunter.pages.ResultSearchPage;
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
public class AdvancedSearchTest extends UiTestBase {

    String region;
    String tech;
    AdvancedSearchPage advancedSearchPage;
    ResultSearchPage resultSearchPage;

    {
        region = "Москва";
        tech = "Selenide";
        advancedSearchPage = new AdvancedSearchPage();
        resultSearchPage = new ResultSearchPage();
    }

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

        advancedSearchPage.openAdvancedSearchPage()
                .setItCategory()
                .setQaProfile()
                .setRemote()
                .setPartTimeEmployment()
                .setRegion(region)
                .clickToFind();

        resultSearchPage.checkShouldHavefinded()
                .checkShouldBeNotFullDay()
                .checkShouldBeQACategory()
                .checkShouldBeCurrentRegion(region)
                .checkShouldBeRemoteWork()
                .checkShouldBeMoreFourHoure()
                .checkShouldBeOnWeekends()
                .checkShouldBeInTheEvenings();

    }

    @Test
    @DisplayName("full-time QA manual search test")
    @Severity(SeverityLevel.NORMAL)
    void createFullTimeQASearchTest() {

        advancedSearchPage.openAdvancedSearchPage()
                .setItCategory()
                .setQaProfile()
                .setRemote()
                .setFullTimeEmployment()
                .setRegion(region)
                .clickToFind();

        resultSearchPage.checkShouldHavefinded()
                .checkShouldBeFullDay()
                .checkShouldBeQACategory()
                .checkShouldBeCurrentRegion(region)
                .checkShouldBeRemoteWork()
                .checkShouldBeFlexibleWorkingHours();
    }

    @Test
    @DisplayName("temporary QA manual search test")
    @Severity(SeverityLevel.NORMAL)
    void createTemporaryJobQASearchTest() {

        advancedSearchPage.openAdvancedSearchPage()
                .setItCategory()
                .setQaProfile()
                .setRemote()
                .setFullTimeEmployment()
                .setRegion(region)
                .clickToFind();

        resultSearchPage.checkShouldHavefinded()
                .checkShouldBeFullDay()
                .checkShouldBeQACategory()
                .checkShouldBeCurrentRegion(region)
                .checkShouldBeRemoteWork()
                .checkShouldBeFlexibleWorkingHours();
    }

    @Test
    @DisplayName("selenide from header search test")
    @Severity(SeverityLevel.NORMAL)
    void createSelenideFromSearchHeaderTest() {

        advancedSearchPage.openAdvancedSearchPage()
                .setItCategory()
                .setQaProfile()
                .setRemote()
                .setRegion(region)
                .setTechToHeader(tech)
                .setNullExperience()
                .clickToFind();

        resultSearchPage.checkShouldBeQACategory()
                .checkShouldBeCurrentRegion(region)
                .checkShouldBeRemoteWork()
                .checkShouldBeCurrentTechToHeader(tech)
                .checkShouldBeWithoutExperience();
    }

    @ValueSource(strings = {"Selenide", "Java", "Gradle"})
    @ParameterizedTest(name = "check headers parameter {arguments}")
    @Severity(SeverityLevel.NORMAL)
    void headersParameterTest(String arg) {

        advancedSearchPage.openAdvancedSearchPage()
                .setTechToHeader(arg)
                .clickToFind();

        resultSearchPage.checkShouldBeCurrentTechToHeader(arg);

    }

}
