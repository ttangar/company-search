package com.company.search.fixtures;

import com.company.search.client.dto.CompanyRootDto;
import com.company.search.client.dto.OfficerRootDto;

import static com.company.search.TestUtils.jsonParserByFile;

public class TruProxySearchFixture {
    private TruProxySearchFixture() {
        throw new UnsupportedOperationException("Cannot instantiate class.");
    }

    public static CompanyRootDto getTruProxySearchResponse() {
        return jsonParserByFile("truproxy", "TRUPROXY_SEARCH_COMPANY_RESPONSE", CompanyRootDto.class);
    }

    public static OfficerRootDto getTruProxyOfficerSearchResponse() {
        return jsonParserByFile("truproxy", "TRUPROXY_SEARCH_OFFICER_RESPONSE", OfficerRootDto.class);
    }
}
