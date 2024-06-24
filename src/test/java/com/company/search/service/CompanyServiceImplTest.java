package com.company.search.service;

import com.company.search.client.TruProxyClient;
import com.company.search.client.dto.CompanyRootDto;
import com.company.search.client.dto.OfficerRootDto;
import com.company.search.exceptions.BadRequestException;
import com.company.search.fixtures.TruProxySearchFixture;
import com.company.search.model.dto.SearchRequestDto;
import com.company.search.model.dto.SearchResponseDto;
import com.company.search.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CompanyServiceImplTest {

    @Mock
    private TruProxyClient truProxyClient;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyServiceImpl classUnderTest;

    private SearchRequestDto searchRequest;
    private CompanyRootDto companyAPIResponse;
    private OfficerRootDto officerAPIResponse;
    private SearchResponseDto searchResponse;

    private Throwable exception;

    @AfterEach
    void tearDown() {
        searchRequest = null;
        companyAPIResponse = null;
        officerAPIResponse = null;
        exception = null;
    }

    @Test
    void searchCompanySuccessOnlyActive() {
        givenCorrectInputWithOnlyNameToSearchAPI();
        whenCompanyNoExistInDB();
        whenCompanyAPIRespondSuccess();
        whenOfficerAPIRespondSuccess();
        whenCompanySearchServiceCalledExpectOnlyActiveCompanies();
        thenSearchResponseContainsOnlyActiveCompanies();
    }

    @Test
    void searchCompanySuccessOnlyActiveWithOneParam() {
        givenCorrectInputWithOnlyNameToSearchAPI();
        whenCompanyAPIRespondSuccess();
        whenOfficerAPIRespondSuccess();
        whenCompanySearchServiceCalledExpectOnlyActiveCompanies();
        thenSearchResponseContainsOnlyActiveCompanies();
    }


    @Test
    void searchCompanySuccessAll() {
        givenCorrectInputWithOnlyNameToSearchAPI();
        whenCompanyNoExistInDB();
        whenCompanyAPIRespondSuccess();
        whenOfficerAPIRespondSuccess();
        whenCompanySearchServiceCalledExpectSuccess();
        thenSearchResponseContainsAllCompanies();
    }

//    @Test
//    void searchCompanyWithCompanyNameSuccessAll() {
//        givenCorrectInputWithBothParamToSearchAPI();
//        whenCompanyExistInDB();
//        whenCompanySearchServiceCalledExpectSuccess();
//        thenSearchResponseContainsAllCompanies();
//    }


    @Test
    void searchCompanyBadRequest() {
        givenInCorrectInputToSearchAPI();
        whenCompanySearchServiceCalledExpectException();
    }

    private void givenCorrectInputWithBothParamToSearchAPI() {
        searchRequest = new SearchRequestDto("BBC-Limited", "06500244");
    }

    private void givenCorrectInputWithOnlyNameToSearchAPI() {
        searchRequest = new SearchRequestDto("BBC-Limited", null);
    }

    private void givenInCorrectInputToSearchAPI() {
        searchRequest = new SearchRequestDto(null, null);
    }

    private void whenCompanyNoExistInDB() {
        when(companyRepository.findByCompanyNumber(any())).thenReturn(Optional.empty());
    }

    private void whenCompanyExistInDB() {
        when(companyRepository.findByCompanyNumber(any())).thenReturn(Optional.empty());
    }

    private void whenCompanyAPIRespondSuccess() {
        companyAPIResponse = TruProxySearchFixture.getTruProxySearchResponse();
        when(truProxyClient.getCompanySearch(any())).thenReturn(companyAPIResponse.getItems());
    }

    private void whenOfficerAPIRespondSuccess() {
        officerAPIResponse = TruProxySearchFixture.getTruProxyOfficerSearchResponse();
        when(truProxyClient.getCompanyOfficersSearch(any())).thenReturn(officerAPIResponse.getItems());
    }

    private void whenCompanySearchServiceCalledExpectOnlyActiveCompanies() {
        searchResponse = classUnderTest.searchCompany(searchRequest, true);
    }

    private void whenCompanySearchServiceCalledExpectSuccess() {
        searchResponse = classUnderTest.searchCompany(searchRequest, false);
    }

    private void whenCompanySearchServiceCalledExpectException() {
        exception = assertThrows(BadRequestException.class, () -> {
            classUnderTest.searchCompany(searchRequest, false);
        });
        assertEquals("All fields in SearchRequest cannot be empty", exception.getMessage());
    }

    private void thenSearchResponseContainsAllCompanies() {
        assertNotNull(searchResponse);
        assertThat(searchResponse.getTotalResults()).isEqualTo(20L);
        assertThat(searchResponse.getItems().stream().filter(item -> !item.getCompanyStatus().equalsIgnoreCase("active")).count()).isNotZero();

    }

    private void thenSearchResponseContainsOnlyActiveCompanies() {
        assertNotNull(searchResponse);
        assertThat(searchResponse.getTotalResults()).isEqualTo(15L);
        assertThat(searchResponse.getItems().stream().filter(item -> !item.getCompanyStatus().equalsIgnoreCase("active")).count()).isZero();
    }

}