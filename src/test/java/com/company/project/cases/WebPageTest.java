package com.company.project.cases;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.company.project.DateOfBirth;
import com.company.project.Gender;
import com.company.project.Hobby;
import com.company.project.Student;
import com.company.project.testdata.UITestBase;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;

@Slf4j
@Tag("web_page")
@DisplayName("check web page https://demoqa.com/automation-practice-form")
public class WebPageTest extends UITestBase {

    @BeforeEach
    void beforeEach() {

        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = faker.internet().emailAddress(),
                phoneNumber = faker.phoneNumber().subscriberNumber(10),
                address = faker.address().fullAddress(),
                subject = "Maths",
                state = "NCR",
                city = "Delhi";

        Gender gender = Gender.randomGender();
        Hobby hobby = Hobby.randomHobby();
        DateOfBirth dateOfBirth = new DateOfBirth(sdfBirthdayFaker.format(faker.date().birthday()));
        File picture = new File("src/main/resources/jpg/photo_2022-11-18_20-47-02.jpg");

        student = Student.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                        .phoneNumber(phoneNumber)
                        .gender(gender)
                        .address(address)
                        .subject(subject)
                        .hobby(hobby)
                        .dateOfBirth(dateOfBirth)
                        .picture(picture)
                        .state(state)
                        .city(city)
                        .build();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Owner("chilikinow")
    @DisplayName("check positive registration form")
    void positiveRegistrationFormTest(){

        SelenideLogger.addListener("allure", new AllureSelenide());

        registrationPage.openPage("/automation-practice-form")
            .setFirstName(student.getFirstName())
            .setLastName(student.getLastName())
            .setEmail(student.getEmail())
            .chooseGender(student.getGender())
            .setPhoneNumber(student.getPhoneNumber())
            .setSubject(student.getSubject())
            .chooseHobby(student.getHobby())
            .uploadPicture(student.getPicture())
            .setAddress(student.getAddress())
            .setState(student.getState())
            .setCity(student.getCity())
            .setDateOfBirth(student.getDateOfBirth())
            .submit();

        registrationPage.getModal().getDialog().should(appear);
        registrationPage.getModal().getDialogTitle().shouldHave(text("Thanks for submitting the form"));

        registrationPage.getModal().getContent().shouldBe(Condition.visible);
        registrationPage.getModal().getContent().shouldHave(text(student.getFirstName() + " " + student.getLastName()));
        registrationPage.getModal().getContent().shouldHave(text(student.getEmail()));
        registrationPage.getModal().getContent().shouldHave(text(student.getGender().toString()));
        registrationPage.getModal().getContent().shouldHave(text(student.getPhoneNumber()));
        registrationPage.getModal().getContent().shouldHave(text(

                student.getDateOfBirth().getLocalDate().getDayOfMonth()
                + " "
                + student.getDateOfBirth().getMonthStartToUppercase()
                + ","
                + student.getDateOfBirth().getLocalDate().getYear()

        ));
        registrationPage.getModal().getContent().shouldHave(text(student.getSubject()));
        registrationPage.getModal().getContent().shouldHave(text(student.getHobby().toString()));
        registrationPage.getModal().getContent().shouldHave(text(student.getPicture().getName()));
        registrationPage.getModal().getContent().shouldHave(text(student.getAddress()));
        registrationPage.getModal().getContent().shouldHave(text(student.getState() + " " + student.getCity()));
        registrationPage.getModal().close();

    }
}
