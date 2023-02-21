package com.company.project.tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.*;


public class YandexPageTests extends UITestBase{

    @Feature("JIRAPROJECT-23011")
    @Story("JIRAPROJECT-23010")
    @Owner("chilikinow@gmail.com")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void cTest() {

        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;

        open("https://ya.ru/");
        $("[title=\"Все сервисы\"]").click();
        $("div.scrollbar__inner a[aria-label=Маркет]").click();

        $(linkText("Смартфоны")).click();


    }

}
