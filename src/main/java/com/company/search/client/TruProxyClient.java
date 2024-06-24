package com.company.search.client;

import com.company.search.client.dto.CompanyDto;
import com.company.search.client.dto.CompanyRootDto;
import com.company.search.client.dto.OfficerItemDto;
import com.company.search.client.dto.OfficerRootDto;
import com.company.search.exceptions.TruProxyServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class TruProxyClient {

    public static final String COMPANY_SEARCH_API_URL = "/Companies/v1/Search?Query=";
    public static final String OFFICERS_SEARCH_API_URL = "/Companies/v1/Officers?CompanyNumber=";
    public static final String API_KEY_FIELD_NAME = "x-api-key";

    @Value("${truproxy.api.url}")
    private String apiUrl;

    @Value("${truproxy.api.key}")
    private String apiKey;


    private final WebClient webClient;

    @Autowired
    public TruProxyClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<CompanyDto> getCompanySearch(String query) {
        CompanyRootDto companyRootDto = webClient.get().uri(apiUrl + COMPANY_SEARCH_API_URL + query).headers(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add(API_KEY_FIELD_NAME, apiKey);
                }).retrieve()
                .bodyToMono(CompanyRootDto.class)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2L))
                        .filter(this::is5xxServerError)
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                            throw new TruProxyServiceException("External Service failed to process after max retries", retrySignal.failure());
                        })).block();
        if (companyRootDto != null && companyRootDto.getTotalResults() != null && companyRootDto.getTotalResults() > 0 && companyRootDto.getItems() != null && !companyRootDto.getItems().isEmpty()) {
            return companyRootDto.getItems();
        } else {
            return new ArrayList<>();
        }
    }

    private boolean is5xxServerError(Throwable throwable) {
        return throwable instanceof WebClientResponseException && ((WebClientResponseException) throwable).getStatusCode().is5xxServerError();
    }

    public List<OfficerItemDto> getCompanyOfficersSearch(String companyNumber) {
        OfficerRootDto officerRootDto = webClient.get().uri(apiUrl + OFFICERS_SEARCH_API_URL + companyNumber).headers(httpHeaders -> {
                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
                    httpHeaders.add(API_KEY_FIELD_NAME, apiKey);
                }).retrieve()
                .bodyToMono(OfficerRootDto.class)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2L))
                        .filter(this::is5xxServerError)
                        .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) -> {
                            throw new TruProxyServiceException("External Service failed to process after max retries", retrySignal.failure());
                        })).block();
        if (officerRootDto != null && officerRootDto.getTotalResults() != null && officerRootDto.getTotalResults() > 0 && officerRootDto.getItems() != null && !officerRootDto.getItems().isEmpty()) {
            return officerRootDto.getItems();
        } else {
            return new ArrayList<>();
        }
    }

}

