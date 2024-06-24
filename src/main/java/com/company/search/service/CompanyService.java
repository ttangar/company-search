package com.company.search.service;

import com.company.search.model.dto.SearchRequestDto;
import com.company.search.model.dto.SearchResponseDto;

public interface CompanyService {
    SearchResponseDto searchCompany(SearchRequestDto searchRequestDto, boolean onlyActiveCompanies);
}
