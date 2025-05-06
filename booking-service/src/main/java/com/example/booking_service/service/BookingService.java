package com.example.booking_service.service;


import com.example.booking_service.client.AttractionClient;
import com.example.booking_service.client.UserClient;
import com.example.booking_service.dto.AttractionDto;
import com.example.booking_service.dto.BookingDetailsDto;
import com.example.booking_service.dto.UserDto;
import com.example.booking_service.entity.Booking;
import com.example.booking_service.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserClient userClient;
    private final AttractionClient attractionClient;

    public BookingService(BookingRepository bookingRepository,
                          UserClient userClient,
                          AttractionClient attractionClient) {
        this.bookingRepository = bookingRepository;
        this.userClient = userClient;
        this.attractionClient = attractionClient;
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }

    public List<Booking> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getBookingsByAttractionId(Long attractionId) {
        return bookingRepository.findByAttractionId(attractionId);
    }

    public Optional<BookingDetailsDto> getBookingDetailsById(Long bookingId) {
        return bookingRepository.findById(bookingId).map(booking -> {
            UserDto user = userClient.getUserById(booking.getUserId());
            AttractionDto attraction = attractionClient.getAttractionById(booking.getAttractionId());

            BookingDetailsDto dto = new BookingDetailsDto();
            dto.setBooking(booking);
            dto.setUser(user);
            dto.setAttraction(attraction);

            return dto;
        });
    }


}
