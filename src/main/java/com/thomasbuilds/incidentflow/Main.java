package com.thomasbuilds.incidentflow;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PASSWORD");

        IncidentRepository repository =
                new PostgresIncidentRepository(dbUrl, dbUser, dbPassword);

        IncidentService service = new IncidentService(repository);
        List<Incident> incidents = service.getAllIncidents();

        IncidentSummary summary = new IncidentSummary();

        for (Incident incident : incidents) {
            summary.addIncident(incident);

            String slaStatus;
            if (incident.isSlaAtRisk()) {
                slaStatus = "SLA en riesgo";
            } else {
                slaStatus = "SLA controlado";
            }

            System.out.println(
                    "Incident " + incident.getId()
                            + " | " + incident.getCategory()
                            + " | " + incident.getState()
                            + " | " + incident.getDuration() + " minutes"
                            + " | " + slaStatus
            );
        }

        System.out.println(
                "Incidents opened: " + summary.getIncidentsOpen() + "\n"
                        + "Incidents closed: " + summary.getIncidentsClosed() + "\n"
                        + "Duration of opened incidents: "
                        + summary.getDurationIncident() + " minutes"
        );
    }
}