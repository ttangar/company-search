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
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "company_number",
        "company_type",
        "title",
        "company_status",
        "date_of_creation",
        "address",
        "officers"
})
@Generated("jsonschema2pojo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto implements Serializable {

    @JsonProperty("company_number")
    private String companyNumber;
    @JsonProperty("company_type")
    private String companyType;
    @JsonProperty("title")
    private String title;
    @JsonProperty("company_status")
    private String companyStatus;
    @JsonProperty("date_of_creation")
    private String dateOfCreation;
    @JsonProperty("address")
    @Valid
    private AddressDto address;
    @JsonProperty("officers")
    @Valid
    private List<OfficerDto> officers;
    private final static long serialVersionUID = 4363320807942731598L;

}
