package com.company.project.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SingleResource {

    @SerializedName("data")
    private Resource resource;
    @Expose
    private Support support;

}
