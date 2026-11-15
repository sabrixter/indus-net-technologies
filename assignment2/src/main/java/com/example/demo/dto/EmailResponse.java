package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EmailResponse {
    private boolean success;
    private String subject;
    private String body;
    private long responseTimeMs;
}
