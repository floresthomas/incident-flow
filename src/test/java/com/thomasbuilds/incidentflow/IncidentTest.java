package com.thomasbuilds.incidentflow;

import com.thomasbuilds.incidentflow.domain.Incident;
import com.thomasbuilds.incidentflow.domain.IncidentState;
import com.thomasbuilds.incidentflow.domain.IncidentSummary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IncidentTest {
    @Test
    public void isOpen_givenOpenState_shouldReturnTrue(){
        Incident incident = new Incident(
                1,
                "red",
                IncidentState.OPEN,
                25
        );

        assertTrue(incident.isOpen(), "The result is TRUE because state is opened");
    }

    @Test
    public void isOpen_givenClosedState_shouldReturnFalse(){
        Incident incident = new Incident(
                1,
                "red",
                IncidentState.CLOSED,
                25
        );

        assertFalse(incident.isOpen(), "The result is FALSE because state is closed");
    }

    @Test
    public void isClose_givenState_shouldEvaluateClosedState(){
        Incident incident = new Incident(
                1,
                "red",
                IncidentState.OPEN,
                25
        );
        incident.close();

        assertEquals(IncidentState.CLOSED ,incident.getState());
    }

    @Test
    public void reopen_givenState_shouldEvaluateOpenState(){
        Incident incident = new Incident(
                1,
                "red",
                IncidentState.CLOSED,
                25
        );
        incident.reopen();

        assertEquals(IncidentState.OPEN ,incident.getState());
    }
    @Test
    public void close_givenClosedState_shouldThrowIllegalStateException(){
        Incident incident = new Incident(
                1,
                "red",
                IncidentState.CLOSED,
                25
        );

        assertThrows(IllegalStateException.class, () -> incident.close());
    }
    @Test
    public void reopen_givenOpenState_shouldThrowIllegalStateException(){
        Incident incident = new Incident(
            1,
            "red",
            IncidentState.OPEN,
            25
        );

        IllegalStateException error = assertThrows(IllegalStateException.class, () -> incident.reopen());

        assertEquals("Incident is already open", error.getMessage());
    }
    @Test
    public void testDuration_givenNegativeDuration_shouldThrowIllegalArgumentException(){
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> new Incident(
                1,
                "red",
                IncidentState.OPEN,
                -1
        ));

        assertEquals("Duration cannot be negative", error.getMessage());
    }
    @Test
    public void testId_givenInvalidId_shouldThrowIllegalArgumentException(){
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> new Incident(
                0,
                "red",
                IncidentState.OPEN,
                25
        ));

        assertEquals("ID must be greater than zero", error.getMessage());
    }
    @Test
    public void constructor_givenEmptyCategory_shouldThrowIllegalArgumentException() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> new Incident(
                1,
                "",
                IncidentState.OPEN,
                25
        ));

        assertEquals("Category cannot be empty or null", error.getMessage());
    }
    @Test
    public void constructor_givenNullCategory_shouldThrowIllegalArgumentException() {
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> new Incident(
                1,
                null,
                IncidentState.OPEN,
                25
        ));

        assertEquals("Category cannot be empty or null", error.getMessage());
    }
    @Test
    public void constructor_givenNullState_shouldThrowIllegalArgumentException(){
        IllegalArgumentException error = assertThrows(IllegalArgumentException.class, () -> new Incident(
                1,
                "red",
                null,
                25
        ));
        assertEquals("State cannot be null", error.getMessage());
    }
    @Test
    public void isSlaAtRisk_givenOpenIncidentAboveThreshold_shouldReturnTrue(){
        Incident incident = new Incident(
                1,
                "red",
                IncidentState.OPEN,
                25
        );
        assertTrue(incident.isSlaAtRisk());
    }
    @Test
    public void isSlaAtRisk_givenClosedIncidentAboveThreshold_shouldReturnFalse(){
        Incident incident = new Incident(
                1,
                "red",
                IncidentState.CLOSED,
                25
        );
        assertFalse(incident.isSlaAtRisk());
    }
    @Test
    public void isSlaAtRisk_givenOpenIncidentAtThreshold_shouldReturnFalse(){
        Incident incident = new Incident(
                1,
                "red",
                IncidentState.OPEN,
                20
        );
        assertFalse(incident.isSlaAtRisk());
    }
    @Test
    public void incidentSummary_givenMultipleIncidents_shouldCalculateExpectedTotals() {
        IncidentSummary incSummary = new IncidentSummary();

        Incident incident1 =
                new Incident(1, "red", IncidentState.OPEN, 25);

        Incident incident2 =
                new Incident(2, "red", IncidentState.CLOSED, 5);

        Incident incident3 =
                new Incident(3, "access", IncidentState.OPEN, 21);

        Incident incident4 =
                new Incident(4, "access", IncidentState.CLOSED, 10);

        Incident[] incidents = {
                incident1,
                incident2,
                incident3,
                incident4
        };

        for (Incident incident : incidents) {
            incSummary.addIncident(incident);
        }

        assertEquals(2, incSummary.getIncidentsOpen());
        assertEquals(2, incSummary.getIncidentsClosed());
        assertEquals(46, incSummary.getDurationIncident());
    }
}
