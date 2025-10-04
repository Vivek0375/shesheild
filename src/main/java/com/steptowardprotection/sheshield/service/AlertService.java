package com.steptowardprotection.sheshield.service;

import com.steptowardprotection.sheshield.model.Alert;
import com.steptowardprotection.sheshield.model.EmergencyContact;
import com.steptowardprotection.sheshield.model.User;
import com.steptowardprotection.sheshield.repository.AlertRepository;
import com.steptowardprotection.sheshield.repository.EmergencyContactRepository;
import com.steptowardprotection.sheshield.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertService {

    private final AlertRepository alertRepository;
    private final EmergencyContactRepository contactRepository;
    private final UserRepository userRepository;
    private final NotificationService notificationService;

    // Method to send alert and notify contacts
    public Alert sendAlert(Alert alert) {
        alert.setAlertTime(LocalDateTime.now());

        // 🔹 Load full User from DB using the given userId
        User fullUser = userRepository.findById(alert.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id " + alert.getUser().getId()));
        alert.setUser(fullUser);

        System.out.println("📌 Alert before save: " + alert);

        Alert savedAlert = alertRepository.save(alert);

        // Fetch user contacts
        List<EmergencyContact> contacts = contactRepository.findByUserId(fullUser.getId());
        System.out.println("📌 Contacts found: " + contacts.size());

        // Create proper message with user's name
        String message = "ALERT! " + fullUser.getName() + " is in danger at location: " + alert.getLocation();
        System.out.println("📩 Message: " + message);

        // Notify contacts via email
        notificationService.notifyContacts(contacts, message, false); // or true if you want SMS


        return savedAlert;
    }

    // Method to fetch alerts by user
    public List<Alert> getAlertsByUser(Long userId) {
        return alertRepository.findByUserId(userId);
    }
}
