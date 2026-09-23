package com.thomasbuilds.incidentflow;

import com.thomasbuilds.incidentflow.application.IncidentNotFoundException;
import com.thomasbuilds.incidentflow.application.IncidentRepository;
import com.thomasbuilds.incidentflow.application.IncidentService;
import com.thomasbuilds.incidentflow.domain.Incident;
import com.thomasbuilds.incidentflow.domain.IncidentState;
import com.thomasbuilds.incidentflow.infrastructure.InMemoryIncidentRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IncidentServiceTest {

    @Test
    public void closeIncident_givenExistingOpenIncident_shouldCloseAndReturnIt() {
        InMemoryIncidentRepository repository = new InMemoryIncidentRepository();

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
        InMemoryIncidentRepository repository = new InMemoryIncidentRepository();
        IncidentService service = new IncidentService(repository);

        IncidentNotFoundException error = assertThrows(
                IncidentNotFoundException.class,
                () -> service.closeIncident(99)
        );

        assertEquals("Incident with ID 99 not found", error.getMessage());
    }

    @Test
    public void reopenIncident_givenId_shouldReturnOpen(){
        IncidentRepository repository = new InMemoryIncidentRepository();;

        Incident incident = new Incident(
                1,
                "red",
                IncidentState.CLOSED,
                25
        );

        repository.save(incident);

        IncidentService service = new IncidentService(repository);

        Incident result = service.reopenIncident(incident.getId());
        assertSame(incident, result);
        assertEquals(IncidentState.OPEN, result.getState());
    }
}