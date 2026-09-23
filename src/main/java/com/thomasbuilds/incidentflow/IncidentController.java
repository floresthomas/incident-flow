package com.thomasbuilds.incidentflow;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IncidentController {
    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService){
        this.incidentService = incidentService;
    }

    @GetMapping("/incidents")
    public List<Incident> getIncidents(){
        return incidentService.getAllIncidents();
    }
}

