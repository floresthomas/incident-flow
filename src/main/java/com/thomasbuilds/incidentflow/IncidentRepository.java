package com.thomasbuilds.incidentflow;

import java.util.*;

public class IncidentRepository {
    private final Map<Integer, Incident> incidents = new HashMap<>();

    public Incident save(Incident incident){
        if(incidents.containsKey(incident.getId())) throw new IllegalArgumentException("Incident with ID 1 already exists");
        incidents.put(incident.getId(), incident);
        return incident;
    }

    public List<Incident> findAll(){
        return new ArrayList<>(incidents.values());
    }

    public Optional<Incident> findById(int id) {
        return Optional.ofNullable(incidents.get(id));
    }
}
