package com.company.project.headhunter.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Getter
public class HeadHunter {

    String advancedSearchPageUrl = "/search/vacancy/advanced";
    public static SelenideElement searchingForm = $("[data-qa='advanced-vacancy-search__form']");
    public static SelenideElement submitButton = $(".bloko-modal-footer").find(byText("Выбрать"));
    public static SelenideElement employmentFindDiv = $(".novafilters");

    @Step("Open Advanced Search page")
    public HeadHunter openAdvancedSearchPage(){

        open(advancedSearchPageUrl);
        return this;

    }

    @Step("Set IT category")
    public HeadHunter setItCategory() {

        searchingForm.find(byText("Указать специализации")).click();
        $(".bloko-tree-selector-popup-content").find(byText("Информационные технологии"))
                .ancestor("div").$(".bloko-tree-selector-item-spacer").click();

        return this;
    }

    @Step("Set QA profile")
    public HeadHunter setQaProfile() {

        $(".bloko-tree-selector__items").find(byText("Тестировщик")).click();
        submitButton.click();

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
        searchingForm.find(byText("От 4 часов в день")).click();
        searchingForm.find(byText("По выходным")).click();
        searchingForm.find(byText("По вечерам")).click();

        return this;
    }

    @Step("Set full-time employment")
    public HeadHunter setFullTimeEmployment() {

        searchingForm.find(byText("Полный день")).scrollTo().click();
        searchingForm.find(byText("Гибкий график")).click();

        return this;
    }

    @Step("Set region")
    public HeadHunter setRegion(String region) {

        searchingForm.find(byText(region)).ancestor(".bloko-tag-list")
                .sibling(0).$(".bloko-input-text").val(region);

        return this;
    }

    @Step("Input tech to header")
    public HeadHunter setTechToHeader(String tech) {

        searchingForm.$$(".bloko-form-row").first().$(".bloko-input-text").val(tech);

        return this;
    }

    @Step("Set null experience")
    public HeadHunter setNullExperience() {

        searchingForm.find(byText("Нет опыта")).scrollTo().click();

        return this;
    }

}
