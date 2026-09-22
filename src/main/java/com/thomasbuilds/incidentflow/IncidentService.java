package com.thomasbuilds.incidentflow;

import java.util.List;

public class IncidentService {
    private final IncidentRepository repository;

    public IncidentService(IncidentRepository repository) {
        this.repository = repository;
    }

    public Incident closeIncident(int id) {
        Incident incident = findIncidentOrThrow(id);
        incident.close();
        return repository.update(incident);
    }

    public Incident reopenIncident(int id) {
        Incident incident = findIncidentOrThrow(id);
        incident.reopen();
        return repository.update(incident);
    }

    public Incident saveIncident(Incident incident){
        return repository.save(incident);
    }

    public List<Incident> getAllIncidents(){
        return repository.findAll();
    }

    private Incident findIncidentOrThrow(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new IncidentNotFoundException(id));
    }
}

