package com.example.ai.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ai.dto.AiRequest;
import com.example.ai.dto.AiResponse;
import com.example.ai.service.AiService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping("/chat")
    public ResponseEntity<AiResponse> chat(@RequestBody AiRequest request) {
    	AiResponse response = aiService.chat(request.getMessage());
        return ResponseEntity.ok(response);
    }
}