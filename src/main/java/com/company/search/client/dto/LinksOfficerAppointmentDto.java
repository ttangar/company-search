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
        "officer"
})
@Generated("jsonschema2pojo")
@Data
public class LinksOfficerAppointmentDto implements Serializable {

    @JsonProperty("officer")
    @Valid
    public OfficerAppointmentsDto officer;
    private final static long serialVersionUID = 8958344080519759779L;

}
