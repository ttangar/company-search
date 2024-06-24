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
        "company_status",
        "address_snippet",
        "date_of_creation",
        "matches",
        "description",
        "links",
        "company_number",
        "title",
        "company_type",
        "address",
        "kind",
        "description_identifier",
        "snippet",
        "date_of_cessation"
})
@Generated("jsonschema2pojo")
@Data
@JsonIgnoreProperties
public class CompanyDto implements Serializable {

    @JsonProperty("company_status")
    public String companyStatus;
    @JsonProperty("address_snippet")
    public String addressSnippet;
    @JsonProperty("date_of_creation")
    public String dateOfCreation;
    @JsonProperty("matches")
    @Valid
    public MatchesDto matchesDto;
    @JsonProperty("description")
    public String description;
    @JsonProperty("links")
    @Valid
    public LinksDto linksDto;
    @JsonProperty("company_number")
    public String companyNumber;
    @JsonProperty("title")
    public String title;
    @JsonProperty("company_type")
    public String companyType;
    @JsonProperty("address")
    @Valid
    public AddressDto address;
    @JsonProperty("kind")
    public String kind;
    @JsonProperty("description_identifier")
    @Valid
    public List<String> descriptionIdentifier;
    @JsonProperty("snippet")
    public String snippet;
    @JsonProperty("date_of_cessation")
    public String dateOfCessation;
    private final static long serialVersionUID = 2264732034786827856L;

}
