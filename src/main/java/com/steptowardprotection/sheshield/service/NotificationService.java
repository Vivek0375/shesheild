package com.steptowardprotection.sheshield.service;

import com.steptowardprotection.sheshield.model.EmergencyContact;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final JavaMailSender mailSender;

    /**
     * Notify contacts via email and optionally via SMS
     */
    public void notifyContacts(List<EmergencyContact> contacts, String message, boolean sendSMS) {
        for (EmergencyContact contact : contacts) {
            String email = contact.getEmail();
            if (email != null && !email.trim().isEmpty()) {
                try {
                    sendEmail(email, "Emergency Alert!", message);
                    System.out.println("📩 Email sent to: " + email);
                } catch (Exception e) {
                    System.err.println("❌ Failed to send email to " + email + ": " + e.getMessage());
                }
            }

            if (sendSMS) {
                String phone = contact.getPhone();
                if (phone != null && !phone.trim().isEmpty()) {
                    // SMS logic here (or mock)
                    System.out.println("📱 SMS sent to: " + phone + " | Message: " + message);
                }
            }
        }
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailSender.send(mailMessage);
    }
}
