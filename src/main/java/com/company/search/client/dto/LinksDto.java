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
        "self"
})
@Generated("jsonschema2pojo")
@Data
@JsonIgnoreProperties
public class LinksDto implements Serializable {

    @JsonProperty("self")
    public String self;
    private final static long serialVersionUID = -3641850601801092844L;

}
