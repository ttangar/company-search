package com.company.search.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {

    @JsonProperty("locality")
    private String locality;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("premises")
    private String premises;

    @JsonProperty("address_line_1")
    private String addressLine1;

    @JsonProperty("country")
    private String country;

}
