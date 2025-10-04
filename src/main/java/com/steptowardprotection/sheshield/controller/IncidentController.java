package com.steptowardprotection.sheshield.controller;

import com.steptowardprotection.sheshield.dto.IncidentRequest;
import com.steptowardprotection.sheshield.model.EmergencyContact;
import com.steptowardprotection.sheshield.model.Incident;
import com.steptowardprotection.sheshield.model.User;
import com.steptowardprotection.sheshield.model.enums.IncidentType;
import com.steptowardprotection.sheshield.model.enums.Severity;
import com.steptowardprotection.sheshield.service.IncidentService;
import com.steptowardprotection.sheshield.service.NotificationService;
import com.steptowardprotection.sheshield.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;
    private final UserService userService;
    private final NotificationService notificationService;

    // Create a new incident
    @PostMapping
    public ResponseEntity<?> createIncident(@RequestBody IncidentRequest req,
                                            @RequestParam(defaultValue = "false") boolean sendSMS) {

        User user = userService.getUserById(req.getUserId());
        if (user == null) return ResponseEntity.status(404)
                .body("User not found with id: " + req.getUserId());

        Incident inc = new Incident();
        inc.setUserId(String.valueOf(req.getUserId()));
        inc.setLatitude(req.getLatitude());
        inc.setLongitude(req.getLongitude());
        inc.setType(req.getType());
        inc.setSeverity(req.getSeverity());
        inc.setDescription(req.getDescription());
        inc.setCreatedAt(LocalDateTime.now());
        inc.setUpdatedAt(LocalDateTime.now());
        inc.setStatus("PENDING");

        Incident saved = incidentService.saveIncident(inc);

        // Notify contacts with Google Maps link
        List<EmergencyContact> contacts = user.getContacts();
        if (contacts != null && !contacts.isEmpty()) {
            String mapsLink = "https://www.google.com/maps/search/?api=1&query="
                    + inc.getLatitude() + "," + inc.getLongitude();

            String message = "🚨 ALERT! " + user.getName() + " is in danger!\n"
                    + "Location: " + inc.getLatitude() + ", " + inc.getLongitude()
                    + "\nGoogle Maps: " + mapsLink
                    + "\nType: " + inc.getType()
                    + "\nSeverity: " + inc.getSeverity()
                    + "\nDescription: " + inc.getDescription();

            // Correctly pass sendSMS parameter
            if (req.getSeverity() == null || req.getSeverity() == Severity.HIGH) {
                notificationService.notifyContacts(contacts, message, sendSMS);
            } else if (req.getSeverity() == Severity.MEDIUM) {
                notificationService.notifyContacts(
                        contacts.subList(0, Math.min(2, contacts.size())),
                        message,
                        sendSMS
                );
            }
        }

        return ResponseEntity.status(201).body(saved);
    }

    // List all incidents
    @GetMapping
    public ResponseEntity<List<Incident>> listIncidents() {
        return ResponseEntity.ok(incidentService.getAllIncidents());
    }

    // Filter incidents
    @GetMapping("/filter")
    public ResponseEntity<List<Incident>> filterIncidents(
            @RequestParam(required = false) Severity severity,
            @RequestParam(required = false) IncidentType type,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        List<Incident> filtered = incidentService.filterIncidents(severity, type, status, startDate, endDate);
        return ResponseEntity.ok(filtered);
    }

    // Get incidents by user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Incident>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(incidentService.getIncidentsByUserId(String.valueOf(userId)));
    }

    // Get incident by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        return incidentService.getIncidentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
