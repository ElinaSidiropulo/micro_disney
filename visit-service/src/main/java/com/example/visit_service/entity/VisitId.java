package com.example.visit_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class VisitId implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "attraction_id")
    private Long attractionId;

    @Column(name = "visit_time")
    private LocalDateTime visitTime;

    public VisitId() {}

    public VisitId(Long userId, Long attractionId, LocalDateTime visitTime) {
        this.userId = userId;
        this.attractionId = attractionId;
        this.visitTime = visitTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAttractionId() {
        return attractionId;
    }

    public void setAttractionId(Long attractionId) {
        this.attractionId = attractionId;
    }

    public LocalDateTime getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(LocalDateTime visitTime) {
        this.visitTime = visitTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VisitId)) return false;
        VisitId that = (VisitId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(attractionId, that.attractionId) &&
                Objects.equals(visitTime, that.visitTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, attractionId, visitTime);
    }
}
