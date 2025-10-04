package com.steptowardprotection.sheshield.repository;

import com.steptowardprotection.sheshield.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    // Incident.userId is stored as String - search by String
    List<Incident> findByUserId(String userId);
}
