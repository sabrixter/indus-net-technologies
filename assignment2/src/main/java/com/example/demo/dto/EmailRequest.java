package com.example.demo.dto;

import lombok.Data;

@Data
public class EmailRequest {
    private String purpose;
    private String recipient_name;
    private String tone;
}
