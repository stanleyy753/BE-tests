package org.example.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.post.UserRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestDataUtils {
    public static List<UserRequest> loadUsersFromJson(String resourcePath) {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream is = TestDataUtils.class.getResourceAsStream(resourcePath)) {
            return mapper.readValue(is, new TypeReference<List<UserRequest>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data from JSON", e);
        }
    }
}
