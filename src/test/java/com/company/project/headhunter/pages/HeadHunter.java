package com.company.project.headhunter.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HeadHunter {

    private String advancedSearchPageUrl = "/search/vacancy/advanced";
    private SelenideElement searchingForm = $("[data-qa='advanced-vacancy-search__form']");
    private SelenideElement submitButton = $(".bloko-modal-footer").find(byText("Выбрать"));
    private SelenideElement employmentFindDiv = $(".novafilters");

    private String region;
    private String tech;

    @Step("Open Advanced Search page")
    public HeadHunter openAdvancedSearchPage(){

        open(advancedSearchPageUrl);
        return this;

    }

    @Step("Set IT category")
    public HeadHunter setItCategory() {

        searchingForm.find(byText("Указать специализации")).scrollTo().click();
        $(".bloko-tree-selector-popup-content").find(byText("Информационные технологии"))
                .ancestor("div").find(".bloko-tree-selector-item-spacer").scrollTo().click();

        return this;
    }

    @Step("Set QA profile")
    public HeadHunter setQaProfile() {

        $(".bloko-tree-selector__items").find(byText("Тестировщик")).scrollTo().click();
        submitButton.scrollTo().click();

        return this;
    }

    @Step("Set remote")
    public HeadHunter setRemote() {

        searchingForm.find(byText("Удаленная работа")).scrollTo().click();

        return this;
    }

    @Step("Set part-time employment")
    public HeadHunter setPartTimeEmployment() {

        searchingForm.find(byText("Неполный день")).scrollTo().click();
        searchingForm.find(byText("От 4 часов в день")).scrollTo().click();
        searchingForm.find(byText("По выходным")).scrollTo().click();
        searchingForm.find(byText("По вечерам")).scrollTo().click();

        return this;
    }

    @Step("Set full-time employment")
    public HeadHunter setFullTimeEmployment() {

        searchingForm.find(byText("Полный день")).scrollTo().click();
        searchingForm.find(byText("Гибкий график")).scrollTo().click();

        return this;
    }

    @Step("Set region")
    public HeadHunter setRegion(String region) {

        this.region = region;

        searchingForm.find(byText(this.region)).ancestor(".bloko-tag-list")
                .sibling(0).find(".bloko-input-text").val(this.region);

        return this;
    }

    @Step("Input tech to header")
    public HeadHunter setTechToHeader(String tech) {

        this.tech = tech;

        searchingForm.$$(".bloko-form-row").first().$(".bloko-input-text").val(this.tech);

        return this;
    }

    @Step("Set null experience")
    public HeadHunter setNullExperience() {

        searchingForm.find(byText("Нет опыта")).scrollTo().click();

        return this;
    }



    @Step("Click to find")
    public HeadHunter clickToFind() {

        $(".search-submit-wrapper").find(byText("Найти")).scrollTo().click();

        return this;
    }

    //checks

    @Step("Check that finded")
    public HeadHunter checkShouldHavefinded() {

        $(".bloko-header-section-3").shouldHave(text("Найден"));

        return this;
    }

    @Step("Check should be not full day")
    public HeadHunter checkShouldBeNotFullDay() {

        employmentFindDiv.find(byText("Неполный день")).ancestor("label")
                .find(".bloko-checkbox__input").shouldBe(checked);

        return this;
    }

    @Step("Check should be full day")
    public HeadHunter checkShouldBeFullDay() {

        employmentFindDiv.find(byText("Полный день")).ancestor("label")
                .find(".bloko-checkbox__input").shouldBe(checked);

        return this;
    }

    @Step("Check should be QA category")
    public HeadHunter checkShouldBeQACategory() {

        employmentFindDiv.find(byText("Тестировщик")).ancestor("label")
                .find(".bloko-checkbox__input").shouldBe(checked);

        return this;
    }

    @Step("Check should be current region")
    public HeadHunter checkShouldBeCurrentRegion() {

        employmentFindDiv.scrollTo().find(byText(this.region)).ancestor("label")
                .find(".bloko-checkbox__input").shouldBe(checked);

        return this;
    }

    @Step("Check should be remote work")
    public HeadHunter checkShouldBeRemoteWork() {

        employmentFindDiv.find(byText("Удаленная работа")).ancestor("label")
                .find(".bloko-checkbox__input").shouldBe(checked);

        return this;
    }


    @Step("Check should be flexible working hours")
    public HeadHunter checkShouldBeFlexibleWorkingHours() {

        employmentFindDiv.find(byText("Гибкий график")).ancestor("label")
                .find(".bloko-checkbox__input").shouldBe(checked);

        return this;
    }

    @Step("Check should be current tech to header")
    public HeadHunter checkShouldBeCurrentTechToHeader() {

        $(".bloko-header-section-3").shouldHave(text(this.tech));

        return this;
    }

    @Step("Check should be without experience")
    public HeadHunter checkShouldBeWithoutExperience() {

        employmentFindDiv.find(byText("Нет опыта")).ancestor("label")
                .find(".bloko-radio__input").shouldBe(checked);

        return this;
    }

    @Step("Check should be more four houre")
    public HeadHunter checkShouldBeMoreFourHoure() {

        employmentFindDiv.find(byText("От 4 часов в день")).ancestor("label")
                .find(".bloko-checkbox__input").shouldBe(checked);

        return this;
    }

    @Step("Check should be on weekends")
    public HeadHunter checkShouldBeOnWeekends() {

        employmentFindDiv.find(byText("По выходным")).ancestor("label")
                .find(".bloko-checkbox__input").shouldBe(checked);

        return this;
    }

    @Step("Check should be in the evenings")
    public HeadHunter checkShouldBeInTheEvenings() {

        employmentFindDiv.find(byText("По вечерам")).ancestor("label")
                .find(".bloko-checkbox__input").shouldBe(checked);

        return this;
    }

}
