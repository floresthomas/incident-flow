package com.thomasbuilds.incidentflow.domain;

public class Incident {
    private int id;
    private String category;
    private IncidentState state;
    private int duration;

    public Incident(int id, String category, IncidentState state, int duration) {
        if (duration < 0) throw new IllegalArgumentException("Duration cannot be negative");
        if (id <= 0) throw new IllegalArgumentException("ID must be greater than zero");
        if (category == null || category.isBlank()) throw new IllegalArgumentException("Category cannot be empty or null");
        if (state == null)
            throw new IllegalArgumentException("State cannot be null");

        this.id = id;
        this.category = category;
        this.state = state;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public IncidentState getState() {
        return state;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isOpen() {
        return state == IncidentState.OPEN;
    }

    public boolean isSlaAtRisk() {
        return isOpen() && getDuration() > 20;
    }

    public void close() {
        if (!isOpen()) throw new IllegalStateException("Incident is already closed");

        state = IncidentState.CLOSED;
    }

    public void reopen() {
        if (isOpen()) throw new IllegalStateException("Incident is already open");

        state = IncidentState.OPEN;
    }
}