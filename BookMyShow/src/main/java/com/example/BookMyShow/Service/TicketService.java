package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.TicketRequestDto;
import com.example.BookMyShow.Dto.TicketResponseDto;

import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<TicketResponseDto> getAllTickets();
    Optional<TicketResponseDto> getTicketById(Long id);
    List<TicketResponseDto> getTicketsByBookingId(Long bookingId);
    TicketResponseDto createTicket(TicketRequestDto dto);
    Optional<TicketResponseDto> updateTicket(Long id, TicketRequestDto dto);
    void deleteTicket(Long id);
}

