package com.example.ticket_service.repository;

import com.example.ticket_service.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByValidFromBetween(LocalDate startDate, LocalDate endDate);
    List<Ticket> findByUserId(Long userId);
}
