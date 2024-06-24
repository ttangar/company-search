package com.company.search.client.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.annotation.Generated;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "appointments"
})
@Generated("jsonschema2pojo")
@Data
public class OfficerAppointmentsDto implements Serializable {

    @JsonProperty("appointments")
    public String appointments;
    private final static long serialVersionUID = 2415727246830964939L;

}
