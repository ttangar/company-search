package com.company.search.controller;

import com.company.search.CompanySearchApplication;
import com.company.search.TestUtils;
import com.company.search.exceptions.BadRequestException;
import com.company.search.exceptions.handler.GlobalExceptionHandler;
import com.company.search.fixtures.SearchResponseFixture;
import com.company.search.model.dto.SearchRequestDto;
import com.company.search.service.CompanyServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CompanySearchApplication.class)
@AutoConfigureMockMvc
class CompanySearchControllerTest {

    @Mock
    private CompanyServiceImpl companyServiceImpl;
    @InjectMocks
    private CompanySearchController classUnderTest;

    private MockMvc mockMvc;
    private SearchRequestDto searchRequest;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(classUnderTest)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @AfterEach
    void tearDown() {
        searchRequest = null;
    }

    @Test
    @DisplayName("Given POST /search is called return 200 SUCCESS")
    void postSearchAPIReturn200() throws Exception {
        givenCorrectInputToSearchAPI();
        whenServiceRespondsWithSuccess();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("onlyActiveCompanies", String.valueOf(false))
                        .content(TestUtils.asJsonString(searchRequest)))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(jsonPath("$.total_results").exists())
                .andExpect(jsonPath("$.total_results").value(20));
    }

    @Test
    @DisplayName("Given POST /search is called return 400 BAD REQUEST")
    void postSearchAPIReturn400() throws Exception {
        givenInCorrectInputToSearchAPI();
        whenServiceRespondsWithValidationFailure();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("onlyActiveCompanies", String.valueOf(false))
                        .content(TestUtils.asJsonString(searchRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Given POST /search is called return 500 BAD REQUEST")
    void postSearchAPIReturn500() throws Exception {
        givenCorrectInputToSearchAPI();
        whenServiceRespondsWithInternalServerError();

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .param("onlyActiveCompanies", String.valueOf(false))
                        .content(TestUtils.asJsonString(searchRequest)))
                .andExpect(status().isInternalServerError());
    }

    private void givenCorrectInputToSearchAPI() {
        searchRequest = new SearchRequestDto("BBC-Limited", null);
    }

    private void givenInCorrectInputToSearchAPI() {
        searchRequest = new SearchRequestDto(null, null);
    }

    private void whenServiceRespondsWithSuccess() {
        when(companyServiceImpl.searchCompany(searchRequest, false)).thenReturn(SearchResponseFixture.getSearchResponseForAllCompanies());
    }

    private void whenServiceRespondsWithValidationFailure() {
        when(companyServiceImpl.searchCompany(searchRequest, false)).thenThrow(new BadRequestException("ERROR"));
    }

    private void whenServiceRespondsWithInternalServerError() {
        when(companyServiceImpl.searchCompany(searchRequest, false)).thenThrow(new RuntimeException("ERROR"));
    }

}