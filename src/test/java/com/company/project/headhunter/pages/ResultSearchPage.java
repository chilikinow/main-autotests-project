package com.company.project.headhunter.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultSearchPage {

    private SelenideElement employmentFindDiv = $(".novafilters");

    @Step("Check that finded")
    public ResultSearchPage checkShouldHavefinded() {

        $(".bloko-header-section-3").shouldHave(text("Найден"));

        return this;
    }

    @Step("Check should be not full day")
    public ResultSearchPage checkShouldBeNotFullDay() {

        checkBlokoCheckboxInput("Неполный день");

        return this;
    }

    @Step("Check should be full day")
    public ResultSearchPage checkShouldBeFullDay() {

        checkBlokoCheckboxInput("Полный день");

        return this;
    }

    @Step("Check should be QA category")
    public ResultSearchPage checkShouldBeQACategory() {

        checkBlokoCheckboxInput("Тестировщик");

        return this;
    }

    @Step("Check should be current region")
    public ResultSearchPage checkShouldBeCurrentRegion(String region) {

        checkBlokoCheckboxInput(region);

        return this;
    }

    @Step("Check should be remote work")
    public ResultSearchPage checkShouldBeRemoteWork() {

        checkBlokoCheckboxInput("Удаленная работа");

        return this;
    }


    @Step("Check should be flexible working hours")
    public ResultSearchPage checkShouldBeFlexibleWorkingHours() {

        checkBlokoCheckboxInput("Гибкий график");

        return this;
    }

    @Step("Check should be current tech to header")
    public ResultSearchPage checkShouldBeCurrentTechToHeader(String tech) {

        $(".bloko-header-section-3").shouldHave(text(tech));

        return this;
    }

    @Step("Check should be without experience")
    public ResultSearchPage checkShouldBeWithoutExperience() {

        employmentFindDiv.find(byText("Нет опыта")).ancestor("label")
                .find(".bloko-radio__input").shouldBe(checked);

        return this;
    }

    @Step("Check should be more four houre")
    public ResultSearchPage checkShouldBeMoreFourHoure() {

        checkBlokoCheckboxInput("От 4 часов в день");

        return this;
    }

    @Step("Check should be on weekends")
    public ResultSearchPage checkShouldBeOnWeekends() {

        checkBlokoCheckboxInput("По выходным");

        return this;
    }

    @Step("Check should be in the evenings")
    public ResultSearchPage checkShouldBeInTheEvenings() {

        checkBlokoCheckboxInput("По вечерам");

        return this;
    }

    private void checkBlokoCheckboxInput(String elementText){
        employmentFindDiv.find(byText(elementText)).ancestor("label")
                .find(".bloko-checkbox__input").shouldBe(checked);
    }
}
