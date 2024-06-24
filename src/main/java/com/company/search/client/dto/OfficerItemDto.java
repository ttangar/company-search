package com.company.search.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "address",
        "name",
        "appointed_on",
        "officer_role",
        "links",
        "date_of_birth",
        "occupation",
        "country_of_residence",
        "nationality",
        "resigned_on"
})
@Generated("jsonschema2pojo")
@Data
public class OfficerItemDto implements Serializable {

    @JsonProperty("address")
    @Valid
    public AddressDto address;
    @JsonProperty("name")
    public String name;
    @JsonProperty("appointed_on")
    public String appointedOn;
    @JsonProperty("officer_role")
    public String officerRole;
    @JsonProperty("links")
    @Valid
    public LinksOfficerAppointmentDto links;
    @JsonProperty("date_of_birth")
    @Valid
    public DateOfBirthDto dateOfBirthDto;
    @JsonProperty("occupation")
    public String occupation;
    @JsonProperty("country_of_residence")
    public String countryOfResidence;
    @JsonProperty("nationality")
    public String nationality;
    @JsonProperty("resigned_on")
    public String resignedOn;
    private final static long serialVersionUID = -986039957098223927L;

}
