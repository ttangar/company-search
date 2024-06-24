package com.company.search.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "total_results",
        "items"
})
@Generated("jsonschema2pojo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponseDto implements Serializable {

    @JsonProperty("total_results")
    private Long totalResults;
    @JsonProperty("items")
    @Valid
    private List<CompanyDto> items;
    private final static long serialVersionUID = 2586294499703281687L;

}
