package com.company.search.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.annotation.Generated;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "premises",
        "postal_code",
        "country",
        "locality",
        "address_line_1",
        "region",
        "address_line_2"
})
@Generated("jsonschema2pojo")
@Data
@JsonIgnoreProperties
public class AddressDto implements Serializable {

    @JsonProperty("premises")
    public String premises;
    @JsonProperty("postal_code")
    public String postalCode;
    @JsonProperty("country")
    public String country;
    @JsonProperty("locality")
    public String locality;
    @JsonProperty("address_line_1")
    public String addressLine1;
    @JsonProperty("region")
    public String region;
    @JsonProperty("address_line_2")
    public String addressLine2;
    private final static long serialVersionUID = 817126862981477653L;

}
