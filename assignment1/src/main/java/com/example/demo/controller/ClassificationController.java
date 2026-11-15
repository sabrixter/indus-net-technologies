package com.example.demo.controller;

import com.example.demo.dto.ReqDTO;
import com.example.demo.dto.ResDTO;
import com.example.demo.services.ClassificationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classify")
public class ClassificationController {

    private final ClassificationService classificationService;

    public ClassificationController(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @PostMapping
    public ResponseEntity<ResDTO> classify(@RequestBody ReqDTO request) {

        System.out.println("**************************************");
        System.out.println(request.getText());

        if (request.getText() == null || request.getText().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        ResDTO response = classificationService.classifyText(request.getText());

        return ResponseEntity.ok(response);
    }
}