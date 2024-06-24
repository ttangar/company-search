package com.company.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;


@Slf4j
public class TestUtils {
    private TestUtils() {
    }

    public static ObjectMapper getMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }

    public static <T> T jsonParserByFile(String fileBlock, String payload, Class<T> model) {
        ObjectMapper mapper = TestUtils.getMapper();
        try {
            File file = new File("src" + File.separator + "test" + File.separator + "resources" + File.separator + "models" + File.separator + fileBlock + File.separator + payload + ".json");
            return mapper.readValue(file, model);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String asJsonString(final Object obj) {
        try {
            return getMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
