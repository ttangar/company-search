package com.company.search.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "locality",
        "postal_code",
        "premises",
        "address_line_1",
        "country"
})
@Generated("jsonschema2pojo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto implements Serializable {

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
    private final static long serialVersionUID = -1018738137766248176L;

}
