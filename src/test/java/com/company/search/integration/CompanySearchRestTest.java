package com.company.search.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.maciejwalkowiak.wiremock.spring.ConfigureWireMock;
import com.maciejwalkowiak.wiremock.spring.EnableWireMock;
import com.maciejwalkowiak.wiremock.spring.InjectWireMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableWireMock({@ConfigureWireMock(name = "truproxy-service", property = "truproxy.api.url")})
@AutoConfigureMockMvc
class CompanySearchRestTest {

    @InjectWireMock("truproxy-service")
    static private WireMockServer wireMockServer;

    @Value("${truproxy.api.url}")
    private String apiUrl;
    @Autowired
    private MockMvc mockMvc;

//    private WireMockServer wireMockServer;


    @BeforeEach
    void setup() {
//        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8089));
//        wireMockServer.start();
//        WireMock.configureFor(apiUrl, 8089);

//        // Mock Company Search Response
//        wireMockServer.stubFor(get(WireMock.urlMatching("/rest/Companies/v1/Search.*"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withBodyFile("TRUPROXY_SEARCH_COMPANY_RESPONSE.json")
//                        .withHeader("Content-Type", "application/json")));
//
//        // Mock Company Officers Response
//        wireMockServer.stubFor(get(WireMock.urlMatching("/rest/Companies/v1/Officers.*"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withBodyFile("TRUPROXY_SEARCH_OFFICER_RESPONSE.json")
//                        .withHeader("Content-Type", "application/json")));
        wireMockServer.stubFor(any(urlPathEqualTo("/Companies/v1/Search"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("src/test/resources/models/truproxy/TRUPROXY_SEARCH_COMPANY_RESPONSE.json")
                        .withHeader("Content-Type", "application/json")));

        wireMockServer.stubFor(any(urlPathEqualTo("/Companies/v1/Officers"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withBodyFile("src/test/resources/models/truproxy/TRUPROXY_SEARCH_OFFICER_RESPONSE.json")
                        .withHeader("Content-Type", "application/json")));
    }

//    @AfterEach
//    void teardown() {
//        wireMockServer.stop();
//    }

    @Test
    void testGetCompany() throws Exception {
        mockMvc.perform(post("/api/v1/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-api-key", "test-api-key")
                        .param("onlyActiveCompanies", "true")
                        .content("{\"companyName\": \"BBC LIMITED\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.items[0].companyNumber").value("06500244"));
    }


}