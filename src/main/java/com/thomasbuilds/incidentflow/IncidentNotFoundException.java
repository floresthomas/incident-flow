package com.thomasbuilds.incidentflow;

public class IncidentNotFoundException extends RuntimeException {

    public IncidentNotFoundException(int id) {
        super("Incident with ID " + id + " not found");
    }
}