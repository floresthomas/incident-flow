package com.thomasbuilds.incidentflow;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IncidentRepository {
    private final List<Incident> incidents = new ArrayList<>();

    public Incident save(Incident incident){
        incidents.add(incident);
        return incident;
    }

    public List<Incident> findAll(){
        return new ArrayList<>(incidents);
    }

    public Optional<Incident> findById(int id) {
        for (Incident incident : incidents) {
            if (incident.getId() == id) {
                return Optional.of(incident);
            }
        }

        return Optional.empty();
    }
}
