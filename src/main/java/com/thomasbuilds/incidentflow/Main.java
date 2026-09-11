package com.thomasbuilds.incidentflow;

public class Main {
    public static void main(String[] args) {
        Incident incident1 = new Incident(1, "red", "open", 25);
        Incident incident2 = new Incident(2, "red", "closed", 5);
        Incident incident3 = new Incident(3, "access", "open", 21);
        Incident incident4 = new Incident(4, "access", "closed", 10);

        Incident[] incidents = {incident1, incident2, incident3, incident4};

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
