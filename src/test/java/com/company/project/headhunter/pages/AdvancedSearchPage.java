package com.company.project.headhunter.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AdvancedSearchPage {

    private String advancedSearchPageUrl = "/search/vacancy/advanced";
    private SelenideElement searchingForm = $("[data-qa='advanced-vacancy-search__form']");
    private SelenideElement submitButton = $(".bloko-modal-footer").find(byText("Выбрать"));

    @Step("Open Advanced Search page")
    public AdvancedSearchPage openAdvancedSearchPage(){

        open(advancedSearchPageUrl);
        return this;

    }

    @Step("Set IT category")
    public AdvancedSearchPage setItCategory() {

        searchingForm.find(byText("Указать специализации")).scrollTo().click();
        $(".bloko-tree-selector-popup-content").find(byText("Информационные технологии"))
                .ancestor("div").find(".bloko-tree-selector-item-spacer").scrollTo().click();

        return this;
    }

    @Step("Set QA profile")
    public AdvancedSearchPage setQaProfile() {

        $(".bloko-tree-selector__items").find(byText("Тестировщик")).scrollTo().click();
        submitButton.scrollTo().click();

        return this;
    }

    @Step("Set remote")
    public AdvancedSearchPage setRemote() {

        clickElementOnSearchingForm("Удаленная работа");

        return this;
    }

    @Step("Set part-time employment")
    public AdvancedSearchPage setPartTimeEmployment() {

        clickElementOnSearchingForm("Неполный день");
        clickElementOnSearchingForm("От 4 часов в день");
        clickElementOnSearchingForm("По выходным");
        clickElementOnSearchingForm("По вечерам");

        return this;
    }

    @Step("Set full-time employment")
    public AdvancedSearchPage setFullTimeEmployment() {

        clickElementOnSearchingForm("Полный день");
        clickElementOnSearchingForm("Гибкий график");

        return this;
    }

    @Step("Set region")
    public AdvancedSearchPage setRegion(String region) {

        searchingForm.find(byText(region)).ancestor(".bloko-tag-list")
                .sibling(0).find(".bloko-input-text").val(region);

        return this;
    }

    @Step("Input tech to header")
    public AdvancedSearchPage setTechToHeader(String tech) {

        searchingForm.$$(".bloko-form-row").first().$(".bloko-input-text").val(tech);

        return this;
    }

    @Step("Set null experience")
    public AdvancedSearchPage setNullExperience() {

        clickElementOnSearchingForm("Нет опыта");

        return this;
    }



    @Step("Click to find")
    public AdvancedSearchPage clickToFind() {

        $(".search-submit-wrapper").find(byText("Найти")).scrollTo().click();

        return this;
    }

    private void clickElementOnSearchingForm(String elementText){
        searchingForm.find(byText(elementText)).scrollTo().click();
    }

}
