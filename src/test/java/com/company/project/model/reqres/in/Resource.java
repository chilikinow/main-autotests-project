package com.company.project.model.reqres.in;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@SuppressWarnings("unused")
public class Resource {

    @Expose
    private String color;
    @Expose
    private Long id;
    @Expose
    private String name;
    @SerializedName("pantone_value")
    private String pantoneValue;
    @Expose
    private Long year;

}
