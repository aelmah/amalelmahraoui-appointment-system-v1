package com.example.appointmentservice.entity;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientFirstName;
    private String patientLastName;
    private String address;
    private int age;
    private String email;
    private String phoneNumber;
    private String doctorName;
    private String service;
    private LocalDateTime appointmentDateTime;

    // Constructeurs, getters et setters

    public Appointment() {
    }

    public Appointment(String patientFirstName, String patientLastName, String address, int age, String email, String phoneNumber, String doctorName, String service, LocalDateTime appointmentDateTime) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.address = address;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.doctorName = doctorName;
        this.service = service;
        this.appointmentDateTime = appointmentDateTime;
    }

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }
}