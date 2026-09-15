package com.thomasbuilds.incidentflow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IncidentServiceTest {

    @Test
    public void closeIncident_givenExistingOpenIncident_shouldCloseAndReturnIt() {
        IncidentRepository repository = new IncidentRepository();

        Incident incident = new Incident(
                1,
                "red",
                IncidentState.OPEN,
                25
        );

        repository.save(incident);

        IncidentService service = new IncidentService(repository);

        Incident result = service.closeIncident(incident.getId());

        assertSame(incident, result);
        assertEquals(IncidentState.CLOSED, result.getState());
    }

    @Test
    public void closeIncident_givenMissingId_shouldThrowIncidentNotFoundException() {
        IncidentRepository repository = new IncidentRepository();
        IncidentService service = new IncidentService(repository);

        IncidentNotFoundException error = assertThrows(
                IncidentNotFoundException.class,
                () -> service.closeIncident(99)
        );

        assertEquals("Incident with ID 99 not found", error.getMessage());
    }
}