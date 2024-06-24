package com.company.search.fixtures;

import com.company.search.model.dto.SearchResponseDto;

import static com.company.search.TestUtils.jsonParserByFile;

public class SearchResponseFixture {
    private SearchResponseFixture() {
        throw new UnsupportedOperationException("Cannot instantiate class.");
    }

    public static SearchResponseDto getSearchResponseForAllCompanies() {
        return jsonParserByFile("responses", "SERVICE_RESPONSE_SUCCESS_ALL_COMPANIES", SearchResponseDto.class);
    }
}
