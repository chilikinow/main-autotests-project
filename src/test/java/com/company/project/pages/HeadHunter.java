package com.company.project.pages;

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
}
