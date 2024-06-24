package com.company.search.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "page_number",
        "kind",
        "total_results",
        "items"
})
@Generated("jsonschema2pojo")
@Data
@JsonIgnoreProperties
public class CompanyRootDto implements Serializable {

    @JsonProperty("page_number")
    public Long pageNumber;
    @JsonProperty("kind")
    public String kind;
    @JsonProperty("total_results")
    public Long totalResults;
    @JsonProperty("items")
    @Valid
    public List<CompanyDto> items;
    private final static long serialVersionUID = -2628297166523304486L;

}
