package com.thomasbuilds.incidentflow;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Incident incident1 = new Incident(1, "red", IncidentState.OPEN, 25);
        Incident incident2 = new Incident(2, "red", IncidentState.CLOSED, 5);
        Incident incident3 = new Incident(3, "access", IncidentState.OPEN, 21);
        Incident incident4 = new Incident(4, "access", IncidentState.CLOSED, 10);

        IncidentRepository repository = new IncidentRepository();
        IncidentService service = new IncidentService(repository);

        repository.save(incident1);
        repository.save(incident2);
        repository.save(incident3);
        repository.save(incident4);

        List<Incident> incidents = repository.findAll();

        String slaStatus;
        IncidentSummary incSummary = new IncidentSummary();

        for(Incident inc : incidents){
            incSummary.addIncident(inc);
            if(inc.isSlaAtRisk()){
                slaStatus = "SLA en riesgo";
            } else {
                slaStatus = "SLA controlado";
            }
            System.out.println("Incident " + inc.getId() + " | " + inc.getCategory() + " | " + inc.getState() + " | " + inc.getDuration() + " minutes" + " | " + slaStatus);
        }
        System.out.println("Incidents opened: " + incSummary.getIncidentsOpen() + "\n" +
                "Incidents closed: " + incSummary.getIncidentsClosed() + "\n" +
                "Duration of opened incidents: " + incSummary.getDurationIncident() + " minutes");
    }
}
