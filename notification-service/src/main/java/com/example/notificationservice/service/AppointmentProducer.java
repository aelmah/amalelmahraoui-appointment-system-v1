package com.example.notificationservice.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppointmentProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    public AppointmentProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Method to send appointment notification messages to Kafka topic
    public void sendAppointmentNotification(String patientEmail, String appointmentDetails) {
        String message = patientEmail + "," + appointmentDetails;  // Format the message as CSV
        kafkaTemplate.send(topic, message);
    }
}
