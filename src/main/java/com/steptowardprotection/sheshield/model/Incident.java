package com.steptowardprotection.sheshield.model;

import com.steptowardprotection.sheshield.model.enums.IncidentType;
import com.steptowardprotection.sheshield.model.enums.Severity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "incidents")
@Getter
@Setter
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    private Double latitude;
    private Double longitude;

    @Enumerated(EnumType.STRING)
    private IncidentType type;

    @Enumerated(EnumType.STRING)
    private Severity severity;

    @Column(length = 1000)
    private String description;

    @Column(name = "status")
    private String status = "PENDING"; // PENDING, IN_PROGRESS, RESOLVED

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
