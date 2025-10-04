package com.steptowardprotection.sheshield.dto;

import com.steptowardprotection.sheshield.model.enums.IncidentType;
import com.steptowardprotection.sheshield.model.enums.Severity;

public class IncidentRequest {

    private Long userId;
    private Double latitude;
    private Double longitude;
    private IncidentType type;
    private Severity severity;
    private String description;

    // Getters & Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public IncidentType getType() { return type; }
    public void setType(IncidentType type) { this.type = type; }

    public Severity getSeverity() { return severity; }
    public void setSeverity(Severity severity) { this.severity = severity; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
