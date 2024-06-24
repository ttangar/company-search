package com.company.search.client.dto;

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
        "etag",
        "inactive_count",
        "links",
        "kind",
        "items_per_page",
        "items",
        "total_results",
        "resigned_count"
})
@Generated("jsonschema2pojo")
@Data
public class OfficerRootDto implements Serializable {

    @JsonProperty("etag")
    public String etag;
    @JsonProperty("inactive_count")
    public Long inactiveCount;
    @JsonProperty("links")
    @Valid
    public LinksDto links;
    @JsonProperty("kind")
    public String kind;
    @JsonProperty("items_per_page")
    public Long itemsPerPage;
    @JsonProperty("items")
    @Valid
    public List<OfficerItemDto> items;
    @JsonProperty("total_results")
    public Long totalResults;
    @JsonProperty("resigned_count")
    public Long resignedCount;
    private final static long serialVersionUID = 8159156484252912242L;

}
