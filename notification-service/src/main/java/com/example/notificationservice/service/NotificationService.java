package com.example.notificationservice.service;

import com.example.notificationservice.model.Appointment;
import com.example.notificationservice.model.AppointmentFeignClient;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private AppointmentFeignClient appointmentFeignClient;

    public void sendConfirmationEmail(Long appointmentId) {
        // Fetch appointment details from the appointment service
        Appointment appointment = appointmentFeignClient.getAppointmentById(appointmentId);

        if (appointment != null) {
            // Generate the appointment details string
            String appointmentDetails = generateAppointmentDetails(appointment);

            // Send confirmation email to the patient's email
            sendEmail(appointment.getEmail(), appointmentDetails);
        } else {
            System.err.println("No appointment found for ID: " + appointmentId);
        }
    }

    private void sendEmail(String patientEmail, String emailContent) {
        try {
            // Create a MimeMessage
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            // Set email details
            helper.setFrom("aa.elmahraoui@gmail.com");  // Replace with your sender email
            helper.setTo(patientEmail);
            helper.setSubject("Appointment Confirmation");
            helper.setText(emailContent, true);  // true means HTML content

            // Send the email
            javaMailSender.send(message);

            System.out.println("Email sent to: " + patientEmail);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error sending email: " + e.getMessage());
        }
    }

    private String generateAppointmentDetails(Appointment appointment) {
        return "<html><body>" +
                "<h3>Your Dental Appointment Confirmation</h3>" +
                "<p>Dear " + appointment.getPatientFirstName() + " " + appointment.getPatientLastName() + ",</p>" +
                "<p>We are pleased to confirm your dental appointment. Below are the details of your upcoming visit:</p>" +
                "<table>" +
                "<tr><th>Appointment Details</th><th></th></tr>" +
                "<tr><td><strong>Doctor:</strong></td><td>" + appointment.getDoctorName() + "</td></tr>" +
                "<tr><td><strong>Service:</strong></td><td>" + appointment.getService() + "</td></tr>" +
                "<tr><td><strong>Appointment Date & Time:</strong></td><td>" + appointment.getAppointmentDateTime() + "</td></tr>" +
                "<tr><td><strong>Location:</strong></td><td>Our Dental Clinic, 123 Main St, City</td></tr>" +
                "</table>" +
                "<p>Please ensure that you arrive 15 minutes before your scheduled time. If you have any questions, feel free to contact us.</p>" +
                "<p>Looking forward to seeing you!</p>" +
                "<p>Best regards,</p>" +
                "<p>Your Dental Clinic Team</p>" +
                "</body></html>";
    }
}
