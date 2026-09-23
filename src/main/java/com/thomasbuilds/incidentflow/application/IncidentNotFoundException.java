package com.thomasbuilds.incidentflow.application;

public class IncidentNotFoundException extends RuntimeException {

    public IncidentNotFoundException(int id) {
        super("Incident with ID " + id + " not found");
    }
}