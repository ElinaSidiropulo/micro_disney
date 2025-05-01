package com.example.ticket_service.controller;

import com.example.ticket_service.entity.Ticket;
import com.example.ticket_service.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@Valid @RequestBody Ticket ticket) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.saveTicket(ticket));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getById(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        return ticket != null ? ResponseEntity.ok(ticket) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Ticket> getAll() {
        return ticketService.getAllTickets();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> update(@PathVariable Long id, @Valid @RequestBody Ticket ticket) {
        Ticket updated = ticketService.updateTicket(id, ticket);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return ticketService.deleteTicket(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ticket>> getByUser(@PathVariable Long userId) {
        List<Ticket> list = ticketService.getTicketsByUserId(userId);
        return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
    }

    @GetMapping("/validity")
    public ResponseEntity<List<Ticket>> getByValidity(
            @RequestParam String start,
            @RequestParam String end) {
        List<Ticket> list = ticketService.getTicketsByValidityPeriod(start, end);
        return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ticket> patch(@PathVariable Long id, @RequestBody Ticket ticket) {
        Ticket updated = ticketService.partialUpdateTicket(id, ticket);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }
}
