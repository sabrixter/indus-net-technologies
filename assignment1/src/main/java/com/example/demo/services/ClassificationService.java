package com.example.demo.services;

import com.example.demo.dto.ResDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

@Service
public class ClassificationService {

    @Value("${gemini.base-url}")
    private String baseUrl;

    @Value("${gemini.model}")
    private String model;

    @Value("${gemini.api-key}")
    private String apiKey;

    private final RestClient restClient = RestClient.create();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResDTO classifyText(String text) {
        System.out.println("service reached");
        String prompt = """
                Classify the following text into exactly one of these categories:
                
                1. Complaint
                2. Query
                3. Feedback
                4. Other
                
                Return the result in JSON format:
                {
                    "category": "Complaint",
                    "confidence": 0.95
                }
                
                Confidence must be between 0 and 1.
                
                Text: 
                """ + text;

        String apiUrl = baseUrl + "/models/" + model
                + ":generateContent";

        //Gemini request body
        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of(
                                "parts", List.of(
                                        Map.of("text", prompt)
                                )
                        )
                ),
                "generationConfig", Map.of(
                        "responseMimeType", "application/json"
                )
        );


        Map response = restClient.post()
                .uri(apiUrl)
                .header("x-goog-api-key", apiKey)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .body(Map.class);

        if (response == null) {
            throw new IllegalStateException("Empty response received from Gemini");
        }

        // Extract candidates
        List<Map> candidates = (List<Map>) response.get("candidates");

        if (candidates == null || candidates.isEmpty()) {
            throw new IllegalStateException("Gemini did not generate any content");
        }

        // Extract content
        Map content = (Map) candidates.get(0).get("content");

        if (content == null) {
            throw new IllegalStateException("Gemini response content is empty");
        }

        // Extract parts
        List<Map> parts = (List<Map>) content.get("parts");

        if (parts == null || parts.isEmpty()) {
            throw new IllegalStateException("Gemini response parts are empty");
        }

        String generatedText = (String) parts.get(0).get("text");

        if (generatedText == null || generatedText.isBlank()) {
            throw new IllegalStateException("Gemini generated empty text");
        }

        // Parsing JSON for text
        Map<String, Object> result = objectMapper.readValue(generatedText, Map.class);

        // Extract classification result
        String category = (String) result.get("category");

        Object confidenceValue = result.get("confidence");

        if (category == null || !(confidenceValue instanceof Number)) {
            throw new IllegalStateException("Invalid classification response from Gemini");
        }

        double confidence = ((Number) confidenceValue).doubleValue();

        if (!List.of("Complaint", "Query", "Feedback", "Other").contains(category) || confidence < 0 || confidence > 1) {
            throw new IllegalStateException("Invalid category or confidence returned by Gemini");
        }

        ResDTO res = new ResDTO();
        res.setCategory(category);
        res.setConfidence(confidence);
        return res;
    }
}