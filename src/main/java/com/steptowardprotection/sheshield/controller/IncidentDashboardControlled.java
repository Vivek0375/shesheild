package com.steptowardprotection.sheshield.controller;

import com.steptowardprotection.sheshield.model.Incident;
import com.steptowardprotection.sheshield.model.enums.IncidentType;
import com.steptowardprotection.sheshield.model.enums.Severity;
import com.steptowardprotection.sheshield.service.IncidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class IncidentDashboardControlled{

    private final IncidentService incidentService;

    // Show dashboard page with all incidents
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        List<Incident> incidents = incidentService.getAllIncidents();
        model.addAttribute("incidents", incidents);
        return "dashboard";
    }

    // Endpoint: count incidents by type (JSON)
    @GetMapping("/dashboard/stats/type")
    @ResponseBody
    public Map<IncidentType, Long> getStatsByType() {
        return incidentService.countByType();
    }

    // Endpoint: count incidents by severity (JSON)
    @GetMapping("/dashboard/stats/severity")
    @ResponseBody
    public Map<Severity, Long> getStatsBySeverity() {
        return incidentService.countBySeverity();
    }

    // Endpoint: count incidents by status (JSON)
    @GetMapping("/dashboard/stats/status")
    @ResponseBody
    public Map<String, Long> getStatsByStatus() {
        return incidentService.countByStatus();
    }

    // Endpoint: get map-ready incident data (JSON)
    @GetMapping("/dashboard/map")
    @ResponseBody
    public List<Map<String, Object>> getMapData() {
        return incidentService.getMapData();
    }
}
