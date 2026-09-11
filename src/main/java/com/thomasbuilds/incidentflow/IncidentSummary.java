package com.thomasbuilds.incidentflow;

public class IncidentSummary {
    private int incidentsOpen = 0;
    private int incidentsClosed = 0;
    private int durationIncident = 0;

    public int getIncidentsOpen() {
        return incidentsOpen;
    }

    public int getIncidentsClosed() {
        return incidentsClosed;
    }

    public int getDurationIncident() {
        return durationIncident;
    }

    public void addIncident(Incident incident){
        if(incident.isOpen()){
            incidentsOpen += 1;
            durationIncident += incident.getDuration();
        } else{
            incidentsClosed += 1;
        }
    }
}
