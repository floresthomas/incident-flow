package com.thomasbuilds.incidentflow;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostgresIncidentRepository implements IncidentRepository {
    private final String url;
    private final String user;
    private final String password;

    public PostgresIncidentRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public Incident save(Incident incident) {
        String sql = """
            INSERT INTO incidents (id, category, state, duration)
            VALUES (?, ?, ?, ?)
        """;

        try(
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement statement = connection.prepareStatement(sql)
        ){
            statement.setInt(1, incident.getId());
            statement.setString(2, incident.getCategory());
            statement.setString(3, incident.getState().name());
            statement.setInt(4, incident.getDuration());

            statement.executeUpdate();

            return incident;
        } catch(SQLException error){
            throw new RuntimeException("Could not save incident", error);
        }
    }

    @Override
    public List<Incident> findAll() {
        String sql = """
            SELECT id, category, state, duration
            FROM incidents
            ORDER BY id ASC
            """;

        List<Incident> incidents = new ArrayList<>();

        try (
                Connection connection =
                        DriverManager.getConnection(url, user, password);

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Incident incident = new Incident(
                            resultSet.getInt("id"),
                            resultSet.getString("category"),
                            IncidentState.valueOf(resultSet.getString("state")),
                            resultSet.getInt("duration")
                    );

                    incidents.add(incident);
                }
            }

            return incidents;
        } catch (SQLException error) {
            throw new RuntimeException(
                    "Could not retrieve incidents",
                    error
            );
        }
    }

    @Override
    public Optional<Incident> findById(int id) {
        String sql = """
            SELECT id, category, state, duration
            FROM incidents
            WHERE id = ?
            """;
        try (
                Connection connection =
                        DriverManager.getConnection(url, user, password);

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Incident incident = new Incident(
                            resultSet.getInt("id"),
                            resultSet.getString("category"),
                            IncidentState.valueOf(resultSet.getString("state")),
                            resultSet.getInt("duration")
                    );

                    return Optional.of(incident);
                }
            }

            return Optional.empty();
        } catch (SQLException error) {
            throw new RuntimeException(
                    "Could not find incident with ID " + id,
                    error
            );
        }
    }

    @Override
    public Incident update(Incident incident) {
        String sql = """
            UPDATE incidents
            SET state = ?
            WHERE id = ?
            """;

        try (
                Connection connection =
                        DriverManager.getConnection(url, user, password);

                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ) {
            statement.setString(1, incident.getState().name());
            statement.setInt(2, incident.getId());

            int updatedRows = statement.executeUpdate();

            if (updatedRows == 0) {
                throw new IncidentNotFoundException(incident.getId());
            }

            return incident;
        } catch (SQLException error) {
            throw new RuntimeException(
                    "Could not update incident with ID " + incident.getId(),
                    error
            );
        }
    }
}
