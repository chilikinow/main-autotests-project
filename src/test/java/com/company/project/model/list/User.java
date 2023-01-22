
package com.company.project.model.list;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@SuppressWarnings("unused")
public class User {

    @Expose
    private String avatar;
    @Expose
    private String email;
    @SerializedName("first_name")
    private String firstName;
    @Expose
    private Long id;
    @SerializedName("last_name")
    private String lastName;

}
