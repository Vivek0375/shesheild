package com.steptowardprotection.sheshield.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location; // Could be GPS coordinates
    private String message;

    private LocalDateTime alertTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
