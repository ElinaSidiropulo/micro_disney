package com.example.ticket_service.service;

import com.example.ticket_service.entity.Ticket;
import com.example.ticket_service.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket saveTicket(Ticket ticket) {
        if (ticket.getValidFrom().isAfter(ticket.getValidTo())) {
            throw new IllegalArgumentException("Valid from must be before valid to");
        }
        return ticketRepository.save(ticket);
    }

    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket updateTicket(Long id, Ticket ticket) {
        Ticket existing = ticketRepository.findById(id).orElse(null);
        if (existing == null) return null;
        existing.setUserId(ticket.getUserId());
        existing.setTicketType(ticket.getTicketType());
        existing.setPrice(ticket.getPrice());
        existing.setValidFrom(ticket.getValidFrom());
        existing.setValidTo(ticket.getValidTo());
        return ticketRepository.save(existing);
    }

    public boolean deleteTicket(Long id) {
        if (ticketRepository.existsById(id)) {
            ticketRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Ticket> getTicketsByUserId(Long userId) {
        return ticketRepository.findByUserId(userId);
    }

    public List<Ticket> getTicketsByValidityPeriod(String start, String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return ticketRepository.findByValidFromBetween(startDate, endDate);
    }

    public Ticket partialUpdateTicket(Long id, Ticket ticket) {
        Ticket existing = ticketRepository.findById(id).orElse(null);
        if (existing == null) return null;

        if (ticket.getTicketType() != null) existing.setTicketType(ticket.getTicketType());
        if (ticket.getPrice() != null) existing.setPrice(ticket.getPrice());
        if (ticket.getValidFrom() != null) existing.setValidFrom(ticket.getValidFrom());
        if (ticket.getValidTo() != null) existing.setValidTo(ticket.getValidTo());

        return ticketRepository.save(existing);
    }
}
