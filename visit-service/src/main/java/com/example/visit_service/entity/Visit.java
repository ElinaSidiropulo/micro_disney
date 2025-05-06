package com.example.visit_service.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "visits")
public class Visit {

    @EmbeddedId
    private VisitId id;

    @Column(name = "ticket_id")
    private Long ticketId;

    public Visit() {}

    public Visit(VisitId id, Long ticketId) {
        this.id = id;
        this.ticketId = ticketId;
    }

    public VisitId getId() {
        return id;
    }

    public void setId(VisitId id) {
        this.id = id;
    }

    public Long getUserId() {
        return id != null ? id.getUserId() : null;
    }

    public Long getAttractionId() {
        return id != null ? id.getAttractionId() : null;
    }

    public LocalDateTime getVisitTime() {
        return id != null ? id.getVisitTime() : null;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", ticketId=" + ticketId +
                '}';
    }
}
