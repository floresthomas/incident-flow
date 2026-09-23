package com.thomasbuilds.incidentflow.application;

import com.thomasbuilds.incidentflow.domain.Incident;

import java.util.List;
import java.util.Optional;

public interface IncidentRepository {
    Incident save(Incident incident);
    List<Incident> findAll();
    Optional<Incident> findById(int id);
    Incident update(Incident incident);
}
