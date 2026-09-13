package com.thomasbuilds.incidentflow;

public class Main {
    public static void main(String[] args) {
        Incident incident1 = new Incident(1, "red", IncidentState.OPEN, 25);
        Incident incident2 = new Incident(2, "red", IncidentState.CLOSED, 5);
        Incident incident3 = new Incident(3, "access", IncidentState.OPEN, 21);
        Incident incident4 = new Incident(4, "access", IncidentState.CLOSED, 10);

        incident1.close();

        try {
            incident1.close();
        } catch (IllegalStateException error) {
            System.out.println(
                    "Could not close incident: " + error.getMessage()
            );
        }

        incident1.reopen();

        System.out.println(incident1.getState());

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
