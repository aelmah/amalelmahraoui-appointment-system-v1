package com.example.notificationservice.model;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "appointment-service")
public interface AppointmentFeignClient {

    @GetMapping("/{id}")
    Appointment getAppointmentById(@PathVariable("id") Long id);
}
