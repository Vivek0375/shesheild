package com.steptowardprotection.sheshield.service;

import com.steptowardprotection.sheshield.model.Incident;
import com.steptowardprotection.sheshield.model.enums.IncidentType;
import com.steptowardprotection.sheshield.model.enums.Severity;
import com.steptowardprotection.sheshield.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository repo;

    // ---------------- Existing Methods ---------------- //

    public Incident saveIncident(Incident incident) {
        return repo.save(incident);
    }

    public List<Incident> getAllIncidents() {
        return repo.findAll();
    }

    public List<Incident> getIncidentsByUserId(String userId) {
        return repo.findByUserId(userId);
    }

    public Optional<Incident> getIncidentById(Long id) {
        return repo.findById(id);
    }

    // ---------------- New: Filter Incidents ---------------- //
    public List<Incident> filterIncidents(Severity severity,
                                          IncidentType type,
                                          String status,
                                          LocalDateTime startDate,
                                          LocalDateTime endDate) {
        return repo.findAll().stream()
                .filter(i -> severity == null || i.getSeverity() == severity)
                .filter(i -> type == null || i.getType() == type)
                .filter(i -> status == null || i.getStatus().equalsIgnoreCase(status))
                .filter(i -> startDate == null || !i.getCreatedAt().isBefore(startDate))
                .filter(i -> endDate == null || !i.getCreatedAt().isAfter(endDate))
                .collect(Collectors.toList());
    }

    // ---------------- New: Dashboard/Reporting Methods ---------------- //

    public Map<IncidentType, Long> countByType() {
        return repo.findAll().stream()
                .collect(Collectors.groupingBy(Incident::getType, Collectors.counting()));
    }

    public Map<Severity, Long> countBySeverity() {
        return repo.findAll().stream()
                .collect(Collectors.groupingBy(Incident::getSeverity, Collectors.counting()));
    }

    public Map<String, Long> countByStatus() {
        return repo.findAll().stream()
                .collect(Collectors.groupingBy(Incident::getStatus, Collectors.counting()));
    }

    public List<Map<String, Object>> getMapData() {
        return repo.findAll().stream()
                .map(i -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", i.getId());
                    map.put("lat", i.getLatitude());
                    map.put("lng", i.getLongitude());
                    map.put("type", i.getType());
                    map.put("severity", i.getSeverity());
                    map.put("status", i.getStatus());
                    return map;
                })
                .collect(Collectors.toList());
    }
}
