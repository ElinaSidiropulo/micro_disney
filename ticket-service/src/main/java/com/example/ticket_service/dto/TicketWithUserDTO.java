package com.example.ticket_service.dto;

import com.example.ticket_service.entity.Ticket;

public class TicketWithUserDTO {
    private Ticket ticket;
    private UserDTO user;

    public TicketWithUserDTO(Ticket ticket, UserDTO user) {
        this.ticket = ticket;
        this.user = user;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
