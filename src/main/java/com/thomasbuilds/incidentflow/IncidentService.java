package com.thomasbuilds.incidentflow;

public class IncidentService {
    private final IncidentRepository repository;

    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }

    public Incident closeIncident(int id) {
        Incident incident = repository.findById(id)
                .orElseThrow(() -> new IncidentNotFoundException(id));

        incident.close();

        return incident;
    }
}