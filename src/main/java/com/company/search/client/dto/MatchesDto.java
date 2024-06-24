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
        "title",
        "snippet"
})
@Generated("jsonschema2pojo")
@Data
@JsonIgnoreProperties
public class MatchesDto implements Serializable {

    @JsonProperty("title")
    @Valid
    public List<Long> title;
    @JsonProperty("snippet")
    @Valid
    public List<Long> snippet;
    private final static long serialVersionUID = 8771788856473253634L;

}
