package com.example.booking_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long attractionId;

    @Column(name = "booking_time", nullable = false)
    private LocalDateTime bookingTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private BookingStatus status = BookingStatus.pending;

    public enum BookingStatus {
        pending, confirmed, cancelled
    }

    @PrePersist
    protected void onCreate() {
        this.bookingTime = LocalDateTime.now();
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public Long getAttractionId() { return attractionId; }

    public void setAttractionId(Long attractionId) { this.attractionId = attractionId; }

    public LocalDateTime getBookingTime() { return bookingTime; }

    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }

    public BookingStatus getStatus() { return status; }

    public void setStatus(BookingStatus status) { this.status = status; }
}
