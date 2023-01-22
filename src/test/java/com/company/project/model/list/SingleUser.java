package com.company.project.model.list;

import com.google.gson.annotations.Expose;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SingleUser {

    @Expose
    private User data;
    @Expose
    private Support support;

}
