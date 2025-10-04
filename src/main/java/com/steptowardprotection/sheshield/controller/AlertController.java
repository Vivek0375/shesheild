package com.steptowardprotection.sheshield.controller;

import com.steptowardprotection.sheshield.model.Alert;
import com.steptowardprotection.sheshield.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {
    private final AlertService alertService;

    @PostMapping
        public Alert triggerAlert(@RequestBody Alert alert) {

        System.out.println(alert);
        return alertService.sendAlert(alert);
    }

    @GetMapping("/user/{userId}")
    public List<Alert> getAlerts(@PathVariable Long userId) {
        return alertService.getAlertsByUser(userId);
    }
}
