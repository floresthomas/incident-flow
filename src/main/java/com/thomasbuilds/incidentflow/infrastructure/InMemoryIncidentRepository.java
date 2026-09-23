package com.thomasbuilds.incidentflow.infrastructure;

import com.thomasbuilds.incidentflow.application.IncidentNotFoundException;
import com.thomasbuilds.incidentflow.application.IncidentRepository;
import com.thomasbuilds.incidentflow.domain.Incident;

import java.util.*;

public class InMemoryIncidentRepository implements IncidentRepository {
    private final Map<Integer, Incident> incidents = new HashMap<>();

    @Override
    public Incident save(Incident incident){
        if(incidents.containsKey(incident.getId())) throw new IllegalArgumentException("Incident with ID 1 already exists");

        incidents.put(incident.getId(), incident);
        return incident;
    }

    @Override
    public List<Incident> findAll(){
        return new ArrayList<>(incidents.values());
    }

    @Override
    public Optional<Incident> findById(int id) {
        return Optional.ofNullable(incidents.get(id));
    }

    @Override
    public Incident update(Incident incident) {
        if (!incidents.containsKey(incident.getId())) {
            throw new IncidentNotFoundException(incident.getId());
        }

        incidents.put(incident.getId(), incident);
        return incident;
    }
}
