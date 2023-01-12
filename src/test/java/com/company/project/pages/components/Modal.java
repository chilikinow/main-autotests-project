package com.company.project.pages.components;

import com.codeborne.selenide.SelenideElement;
import lombok.Builder;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$;

@Getter
@Builder
public class Modal {

    private SelenideElement dialog;
    private SelenideElement dialogTitle;
    private SelenideElement content;

    public void close(){
        $("#closeLargeModal").click();
    }

}
