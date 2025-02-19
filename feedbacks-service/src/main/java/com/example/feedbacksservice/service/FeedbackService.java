package com.example.feedbacksservice.service;

import com.example.feedbacksservice.entity.Feedback;
import com.example.feedbacksservice.repository.FeedbackRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository repository;

    public Feedback saveFeedback(Feedback feedback) {
        return repository.save(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return repository.findAll();
    }

    public byte[] exportToExcel() throws IOException {
        List<Feedback> feedbacks = repository.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Feedbacks");
        Row header = sheet.createRow(0);
        String[] columns = {"ID", "Name", "Email", "Service", "Message", "Rating"}; // Updated columns
        for (int i = 0; i < columns.length; i++) {
            header.createCell(i).setCellValue(columns[i]);
        }
        int rowNum = 1;
        for (Feedback f : feedbacks) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(f.getId());
            row.createCell(1).setCellValue(f.getName());
            row.createCell(2).setCellValue(f.getEmail());
            row.createCell(3).setCellValue(f.getService()); // Include service in the export
            row.createCell(4).setCellValue(f.getMessage()); // Include message in the export
            row.createCell(5).setCellValue(f.getRating()); // Include rating in the export
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();
    }
}
