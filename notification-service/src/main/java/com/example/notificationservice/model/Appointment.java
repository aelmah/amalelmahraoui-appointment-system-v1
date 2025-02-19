package com.example.notificationservice.model;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {

    private Long id;

    private String patientFirstName;
    private String patientLastName;
    private String address;
    private int age;
    private String email;
    private String phoneNumber;
    private String doctorName; // Nom du médecin
    private String service; // Service ou spécialité du médecin
    private LocalDateTime appointmentDateTime;
}
