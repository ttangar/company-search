package com.company.search.controller;

import com.company.search.model.dto.SearchRequestDto;
import com.company.search.model.dto.SearchResponseDto;
import com.company.search.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "api/v1", consumes = "application/json", produces = "application/json")
@Tag(name = "Company Search API", description = "API for searching companies and their officers")
public class CompanySearchController {

    private final CompanyService companyService;

    @Autowired
    public CompanySearchController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/search")
    @Operation(summary = "Search for a company", description = "Search for a company by name or registration number")
    public ResponseEntity<SearchResponseDto> searchCompany(@RequestParam Boolean onlyActiveCompanies, @Valid @RequestBody SearchRequestDto searchRequest) {
        log.debug("searchCompany:::start");
        SearchResponseDto responseDto = companyService.searchCompany(searchRequest, onlyActiveCompanies);
        log.debug("searchCompany:::end");
        return ResponseEntity.ok().body(responseDto);
    }
}
