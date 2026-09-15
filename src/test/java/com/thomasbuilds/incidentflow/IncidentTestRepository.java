package com.thomasbuilds.incidentflow;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
public class IncidentTestRepository {
    @Test
    public void save_givenIncident_shouldStoreIt(){
        Incident incident = new Incident(
                1,
                "red",
                IncidentState.OPEN,
                25
        );
        IncidentRepository repository = new IncidentRepository();

        repository.save(incident);
        List<Incident> result = repository.findAll();

        assertEquals(1, result.size());
        assertSame(incident, result.getFirst());
    }
    @Test
    public void findAll_givenCreatedRepository_shouldReturnEmptyList(){
        IncidentRepository repository = new IncidentRepository();
        List<Incident> result = repository.findAll();

        assertEquals(0, result.size());
    }

    @Test
    public void findAll_givenList_shouldEvaluateNotModifiesTheOriginalList(){
        Incident incident = new Incident(
                1,
                "red",
                IncidentState.OPEN,
                25
        );
        IncidentRepository repository = new IncidentRepository();
        repository.save(incident);
        List<Incident> result = repository.findAll();

        result.removeFirst();

        List<Incident> newConsult = repository.findAll();

        assertEquals(1, newConsult.size());
    }
    @Test
    public void findById_givenExistingId_shouldReturnSameIncident(){
        IncidentRepository repository = new IncidentRepository();
        Incident incident = new Incident(
                1,
                "red",
                IncidentState.OPEN,
                25
        );
        repository.save(incident);

        Optional<Incident> result = repository.findById(incident.getId());
        assertTrue(result.isPresent());
        assertSame(incident, result.get());
    }
    @Test
    public void findById_givenDifferentId_shouldReturnNotFoundId(){
        IncidentRepository repository = new IncidentRepository();
        Incident incident = new Incident(
                1,
                "red",
                IncidentState.OPEN,
                25
        );
        repository.save(incident);
        Optional<Incident> result = repository.findById(2);


        assertTrue(result.isEmpty());
    }
    @Test
    public void save_givenIncident_shouldDifferentIncidentWithTheSameIdThrowAnIllegalArgumentException(){
        IncidentRepository repository = new IncidentRepository();
        Incident incident = new Incident(
                1,
                "red",
                IncidentState.OPEN,
                25
        );
        repository.save(incident);

        Incident incident1 = new Incident(
                1,
                "access",
                IncidentState.OPEN,
                25
        );

       Optional<Incident> result = repository.findById(incident.getId());

        assertThrows(IllegalArgumentException.class, () -> repository.save(incident1));
        assertSame(incident, result.get());
    }
}