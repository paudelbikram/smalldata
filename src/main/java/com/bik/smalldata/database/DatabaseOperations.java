package com.bik.smalldata.database;

import com.bik.smalldata.config.Configuration;
import com.bik.smalldata.model.Event;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations implements Runnable
{
    // SELECT query
    public List<Event> getEvents() {
        System.out.print("Getting events");
        final String query = "SELECT id, event_name, event_time, event_location FROM events";
        List<Event> events = new ArrayList<>();
        try (Connection conn = Configuration.getDbConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while(rs.next()) {
                final long eventId = rs.getLong("id");
                final String eventName = rs.getString("event_name");
                final Date eventTime = rs.getDate("event_time");
                final String eventLocation = rs.getString("event_location");
                Event event = new Event();
                event.setId(eventId);
                event.setEventName(eventName);
                event.setEventTime(eventTime.toLocalDate());
                event.setEventLocation(eventLocation);
                events.add(event);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return events;
    }

    // UPDATE query
    public long updateEvent(final long eventId, final String newEventLocation) {
        System.out.println("Updating event");
        final String updateSql = "UPDATE events SET event_location = ? where id = ?";

        long affectedRows = 0;
        try (Connection conn = Configuration.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateSql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, newEventLocation);
            pstmt.setLong(2, eventId);
            affectedRows = pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return affectedRows;
    }

    // DELETE query; This errors out because of foreign key constraints.
    public long deleteEvent(final long eventId) {
        System.out.println("Deleting event");
        final String deleteQuery = "DELETE FROM events WHERE id = ?";

        long affectedRows = 0;
        // Use PreparedStatement to avoid SQL Injection
        try (Connection conn = Configuration.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(deleteQuery, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setLong(1, eventId);
            affectedRows = pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return affectedRows;
    }

    // INSERT query
    public long insertEvent(final String eventName, final LocalDate eventTime, final String eventLocation) {
        System.out.println("Inserting Event");
        long id = 0;
        final String query = "INSERT INTO events (event_name, event_time, event_location) VALUES (?, ?, ?)";
        try (Connection conn = Configuration.getDbConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, eventName);
            pstmt.setDate(2, java.sql.Date.valueOf(eventTime));
            pstmt.setString(3, eventLocation);
            long affectedRows = pstmt.executeUpdate(); //There is also executeBatch for batch insert.

            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    @Override
    public void run() {
        System.out.println("Running Database Operations");
        List<Event> events = getEvents();
        printEvents(events);
        insertEvent("Career Fair", LocalDate.now(), "Oklahoma City, Oklahoma");
        List<Event> eventsAfterInsert = getEvents();
        printEvents(eventsAfterInsert);
        updateEvent(1, "New City in New Town");
        List<Event> eventsAfterUpdate = getEvents();
        printEvents(eventsAfterUpdate);
        deleteEvent(1); //throws exception because of foreign key
        List<Event> eventsAfterDelete = getEvents();
        printEvents(eventsAfterDelete);
    }


    public void printEvents(List<Event> events) {
        for (Event event: events) {
            System.out.println(event.toString());
        }
    }
}
