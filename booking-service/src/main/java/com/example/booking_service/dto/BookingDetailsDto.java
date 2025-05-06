package com.example.booking_service.dto;

import com.example.booking_service.entity.Booking;

public class BookingDetailsDto {
    private Booking booking;
    private UserDto user;
    private AttractionDto attraction;

    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }

    public UserDto getUser() { return user; }
    public void setUser(UserDto user) { this.user = user; }

    public AttractionDto getAttraction() { return attraction; }
    public void setAttraction(AttractionDto attraction) { this.attraction = attraction; }
}
