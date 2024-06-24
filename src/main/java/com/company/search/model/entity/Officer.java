package com.company.search.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Officer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("officer_role")
    private String officerRole;

    @JsonProperty("appointed_on")
    private String appointedOn;

    @JsonProperty("occupation")
    private String occupation;

    @JsonProperty("country_of_residence")
    private String countryOfResidence;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("resigned_on")
    private String resignedOn;

    @JsonProperty("address")
    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "company_number", nullable = false)
    private Company company;

}

