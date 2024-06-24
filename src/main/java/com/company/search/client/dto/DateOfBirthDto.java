package com.company.search.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.annotation.Generated;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "month",
        "year"
})
@Generated("jsonschema2pojo")
@Data
public class DateOfBirthDto implements Serializable {

    @JsonProperty("month")
    public Long month;
    @JsonProperty("year")
    public Long year;
    private final static long serialVersionUID = 7225637993801686111L;

}
