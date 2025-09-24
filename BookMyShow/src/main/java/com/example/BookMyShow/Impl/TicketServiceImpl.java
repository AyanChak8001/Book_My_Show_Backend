package com.example.BookMyShow.Impl;

import com.example.BookMyShow.Dto.TicketRequestDto;
import com.example.BookMyShow.Dto.TicketResponseDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Seat;
import com.example.BookMyShow.Entity.Ticket;
import com.example.BookMyShow.Repository.BookingRepository;
import com.example.BookMyShow.Repository.SeatRepository;
import com.example.BookMyShow.Repository.TicketRepository;
import com.example.BookMyShow.Service.TicketService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;

    public TicketServiceImpl(TicketRepository ticketRepository,
                             BookingRepository bookingRepository,
                             SeatRepository seatRepository) {
        this.ticketRepository = ticketRepository;
        this.bookingRepository = bookingRepository;
        this.seatRepository = seatRepository;
    }

    private TicketResponseDto toDto(Ticket t) {
        Seat s = t.getSeat();
        return TicketResponseDto.builder()
                .id(t.getId())
                .price(t.getPrice())
                .bookingId(t.getBooking() != null ? t.getBooking().getId() : null)
                .seatId(s != null ? s.getId() : null)
                .seatRowLabel(s != null ? s.getRowLabel() : null)
                .seatNumber(s != null ? s.getSeatNumber() : null)
                .build();
    }

    @Override
    public List<TicketResponseDto> getAllTickets() {
        return ticketRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<TicketResponseDto> getTicketById(Long id) {
        return ticketRepository.findById(id).map(this::toDto);
    }

    @Override
    public List<TicketResponseDto> getTicketsByBookingId(Long bookingId) {
        return ticketRepository.findByBookingId(bookingId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public TicketResponseDto createTicket(TicketRequestDto dto) {
        Ticket t = new Ticket();
        t.setPrice(dto.getPrice());

        if (dto.getBookingId() != null) {
            Optional<Booking> b = bookingRepository.findById(dto.getBookingId());
            b.ifPresent(t::setBooking);
        }

        if (dto.getSeatId() != null) {
            Optional<Seat> s = seatRepository.findById(dto.getSeatId());
            s.ifPresent(t::setSeat);
        }

        Ticket saved = ticketRepository.save(t);
        return toDto(saved);
    }

    @Override
    public Optional<TicketResponseDto> updateTicket(Long id, TicketRequestDto dto) {
        Optional<Ticket> opt = ticketRepository.findById(id);
        if (opt.isEmpty()) return Optional.empty();

        Ticket t = opt.get();
        t.setPrice(dto.getPrice());

        if (dto.getBookingId() != null) {
            Optional<Booking> b = bookingRepository.findById(dto.getBookingId());
            b.ifPresent(t::setBooking);
        }

        if (dto.getSeatId() != null) {
            Optional<Seat> s = seatRepository.findById(dto.getSeatId());
            s.ifPresent(t::setSeat);
        }

        Ticket saved = ticketRepository.save(t);
        return Optional.of(toDto(saved));
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
