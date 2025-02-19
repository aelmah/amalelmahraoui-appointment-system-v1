package com.example.feedbacksservice.controller;

import com.example.feedbacksservice.entity.Feedback;
import com.example.feedbacksservice.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/feedbacks")
class FeedbackController {
    @Autowired
    private FeedbackService service;

    @PostMapping
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        return service.saveFeedback(feedback);
    }

    @GetMapping
    public List<Feedback> getAllFeedbacks() {
        return service.getAllFeedbacks();
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportToExcel() throws IOException {
        byte[] excelData = service.exportToExcel();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=feedbacks.xlsx")
                .body(excelData);
    }
}
