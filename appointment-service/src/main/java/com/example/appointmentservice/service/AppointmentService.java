package com.example.appointmentservice.service;

import com.example.appointmentservice.entity.Appointment;
import com.example.appointmentservice.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;


    // Create an appointment and send confirmation email
    public Appointment createAppointment(Appointment appointment) {
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return savedAppointment;
    }

    // Get an appointment by ID
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Update an appointment
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        return appointmentRepository.findById(id).map(appointment -> {
            appointment.setPatientFirstName(updatedAppointment.getPatientFirstName());
            appointment.setPatientLastName(updatedAppointment.getPatientLastName());
            appointment.setAddress(updatedAppointment.getAddress());
            appointment.setAge(updatedAppointment.getAge());
            appointment.setEmail(updatedAppointment.getEmail());
            appointment.setPhoneNumber(updatedAppointment.getPhoneNumber());
            appointment.setDoctorName(updatedAppointment.getDoctorName());
            appointment.setService(updatedAppointment.getService());
            appointment.setAppointmentDateTime(updatedAppointment.getAppointmentDateTime());
            return appointmentRepository.save(appointment);
        }).orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));
    }

    // Delete an appointment
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Appointment not found with id: " + id);
        }
        appointmentRepository.deleteById(id);
    }
}
