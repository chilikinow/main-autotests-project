package com.company.project.tests;

import com.codeborne.selenide.Configuration;
import com.company.project.base.UiTestBase;
import io.qameta.allure.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.*;

@Slf4j
@Tag("ui")
@DisplayName("check git repo eroshenkoam/allure-example")
public class YandexPageTests extends UiTestBase {

    @BeforeAll
    static void beforeAll() {

        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;

        Configuration.baseUrl = "https://ya.ru";

    }

    @Feature("JIRAPROJECT-23011")
    @Story("JIRAPROJECT-23010")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void checkTaskYandexPageTest() {

        open("/");
        $("[title=\"Все сервисы\"]").click();
        $("div.scrollbar__inner a[aria-label=Маркет]").click();

        $(linkText("Смартфоны")).click();


    }

}
