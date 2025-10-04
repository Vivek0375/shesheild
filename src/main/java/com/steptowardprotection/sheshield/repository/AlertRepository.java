package com.steptowardprotection.sheshield.repository;

import com.steptowardprotection.sheshield.model.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByUserId(Long userId); // <-- Must exist exactly like this
}
