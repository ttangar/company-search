package com.company.search.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "officer_role",
        "appointed_on",
        "address"
})
@Generated("jsonschema2pojo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfficerDto implements Serializable {

    @JsonProperty("name")
    private String name;
    @JsonProperty("officer_role")
    public String officerRole;
    @JsonProperty("appointed_on")
    private String appointedOn;
    @JsonProperty("address")
    @Valid
    private AddressDto address;
    private final static long serialVersionUID = -3519383781089347460L;

}
