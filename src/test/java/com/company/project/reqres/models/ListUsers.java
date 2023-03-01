
package com.company.project.reqres.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@SuppressWarnings("unused")
public class ListUsers {

    @Expose
    @SerializedName("data")
    private List<User> usersList;
    @Expose
    private Long page;
    @Expose
    @SerializedName("per_page")
    private Long perPage;
    @Expose
    private Support support;
    @Expose
    private Long total;
    @SerializedName("total_pages")
    private Long totalPages;

}
