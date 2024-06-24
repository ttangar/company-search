package com.company.search.service;

import com.company.search.client.TruProxyClient;
import com.company.search.client.dto.CompanyDto;
import com.company.search.exceptions.BadRequestException;
import com.company.search.mapper.CompanyMapper;
import com.company.search.mapper.OfficerMapper;
import com.company.search.model.dto.SearchRequestDto;
import com.company.search.model.dto.SearchResponseDto;
import com.company.search.model.entity.Company;
import com.company.search.model.entity.Officer;
import com.company.search.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    private final TruProxyClient truProxyClient;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, TruProxyClient truProxyClient) {
        this.companyRepository = companyRepository;
        this.truProxyClient = truProxyClient;
    }

    @Override
    public SearchResponseDto searchCompany(SearchRequestDto searchRequestDto, boolean onlyActiveCompanies) {
        if ((searchRequestDto.getCompanyName() == null || searchRequestDto.getCompanyName().isEmpty()) &&
                (searchRequestDto.getCompanyNumber() == null || searchRequestDto.getCompanyNumber().isEmpty())) {
            throw new BadRequestException("All fields in SearchRequest cannot be empty");
        }

        List<Company> companies = new ArrayList<>();
        if (searchRequestDto.getCompanyNumber() != null && !searchRequestDto.getCompanyNumber().isEmpty()) {
            Optional<Company> companyOptional = getCompany(searchRequestDto);
            if (companyOptional.isPresent()) {
                if (onlyActiveCompanies && companyOptional.get().getCompanyStatus().equalsIgnoreCase("active")) {
                    companies.add(companyOptional.get());
                } else {
                    companies.add(companyOptional.get());
                }
            }
        } else {
            List<CompanyDto> companiesDtoList = truProxyClient.getCompanySearch(searchRequestDto.getCompanyName());
            companies = companiesDtoList
                    .stream()
                    .filter(onlyActiveCompanies ? companyDto -> companyDto.getCompanyStatus().equalsIgnoreCase("active") : companyDto -> true)
                    .map(companyDto -> CompanyMapper.INSTANCE.companyClientDtoToEntity(companyDto, getOfficers(companyDto.getCompanyNumber())))
                    .toList();

            if (!companies.isEmpty()) {
                companyRepository.saveAll(companies);
            }
        }
        return SearchResponseDto.builder().totalResults((long) companies.size()).items(CompanyMapper.INSTANCE.companyEntityListtoDtoList(companies)).build();
    }


    private Optional<Company> getCompany(SearchRequestDto searchRequest) {
        return companyRepository.findByCompanyNumber(searchRequest.getCompanyNumber());
    }

    private List<Officer> getOfficers(String companyNumber) {
        return truProxyClient.getCompanyOfficersSearch(companyNumber)
                .stream()
                .filter(officerItemDto -> officerItemDto.getResignedOn() == null)
                .map(OfficerMapper.INSTANCE::officerClientDtoToEntity)
                .toList();
    }

}