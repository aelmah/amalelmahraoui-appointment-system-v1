package com.example.appointmentservice.repository;

import com.example.appointmentservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorNameAndAppointmentDateTime(String doctorName, LocalDateTime appointmentDateTime);
}
