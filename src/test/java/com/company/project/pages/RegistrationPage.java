package com.company.project.pages;

import com.codeborne.selenide.SelenideElement;
import com.company.project.DateOfBirth;
import com.company.project.Gender;
import com.company.project.Hobby;
import com.company.project.pages.components.Modal;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Slf4j
public class RegistrationPage {

    @Getter
    private Modal modal;

    {
        modal = Modal.builder()
                    .dialog($(".modal-dialog"))
                    .dialogTitle($("#example-modal-sizes-title-lg"))
                    .content($(".modal-content"))
                    .build();
    }

    private final SelenideElement firstNameInput = $("#firstName"),
                                    lastNameInput = $("#lastName"),
                                    emailInput = $("#userEmail"),
                                    phoneNumberInput = $("#userNumber"),
                                    subjectsInput = $("#subjectsInput"),
                                    uploadPicture = $("#uploadPicture"),
                                    addressInput = $("#currentAddress"),
                                    submitButton = $("#submit");

    @Step("Открываем страницу")
    public RegistrationPage openPage(String url){
        open(url);
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    @Step("Вводим Имя")
    public RegistrationPage setFirstName(String value){
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Вводим Фамилию")
    public RegistrationPage setLastName(String value){
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Вводим электронную почту")
    public RegistrationPage setEmail(String value){
        emailInput.setValue(value);
        return this;
    }

    @Step("Выбираем пол")
    public RegistrationPage chooseGender(Gender gender){
        switch (gender){
            case Male:
                $("#gender-radio-1").doubleClick();
                break;
            case Female:
                $("#gender-radio-2").doubleClick();
                break;
            case Other:
                $("#gender-radio-3").doubleClick();
                break;
        }

        return this;
    }

    @Step("Вводим номер телефона")
    public RegistrationPage setPhoneNumber(String value){
        phoneNumberInput.setValue(value);
        return this;
    }

    @Step("Вводим субъект")
    public RegistrationPage setSubject(String value){
        subjectsInput.click();
        subjectsInput.setValue(value);
        subjectsInput.pressEnter();
        subjectsInput.pressTab();
        return this;
    }

    @Step("Выбираем хобби")
    public RegistrationPage chooseHobby(Hobby hobby){
        switch (hobby){
            case Sports:
                $("#hobbies-checkbox-1").parent().click();
                break;
            case Reading:
                $("#hobbies-checkbox-2").parent().click();
                break;
            case Music:
                $("#hobbies-checkbox-3").parent().click();
                break;
        }

        return this;
    }

    @Step("Загружаем фото")
    public RegistrationPage uploadPicture(File file){
        uploadPicture.uploadFile(file);
        return this;
    }

    @Step("Вводим адрес")
    public RegistrationPage setAddress(String value){
        addressInput.setValue(value);
        return this;
    }

    @Step("Выбираем штат")
    public RegistrationPage setState(String state){
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
        return this;
    }

    @Step("Выбираем город")
    public RegistrationPage setCity(String city){
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        return this;
    }

    @Step("Вводим дату рождения")
    public RegistrationPage setDateOfBirth(DateOfBirth dateOfBirth){

        String day = dateOfBirth.getDayByTwoCharString();
        String month = dateOfBirth.getMonthStartToUppercase();
        String year = String.valueOf(dateOfBirth.getLocalDate().getYear());

        log.info("day: {}, month: {}, year: {}", day, month, year);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
        return this;
    }

    public RegistrationPage submit(){
        submitButton.click();
        return this;
    }

}
