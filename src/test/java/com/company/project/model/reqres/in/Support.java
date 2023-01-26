package com.company.project.model.reqres.in;

import com.google.gson.annotations.Expose;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@SuppressWarnings("unused")
public class Support {

    @Expose
    private String text;
    @Expose
    private String url;

}