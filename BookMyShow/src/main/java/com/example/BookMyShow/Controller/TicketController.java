package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dto.TicketRequestDto;
import com.example.BookMyShow.Dto.TicketResponseDto;
import com.example.BookMyShow.Service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // GET /tickets
    @GetMapping
    public ResponseEntity<List<TicketResponseDto>> getAll(@RequestParam(value = "bookingId", required = false) Long bookingId) {
        if (bookingId != null) {
            return ResponseEntity.ok(ticketService.getTicketsByBookingId(bookingId));
        }
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    // GET /tickets/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDto> getById(@PathVariable Long id) {
        return ticketService.getTicketById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // POST /tickets
    @PostMapping
    public ResponseEntity<TicketResponseDto> create(@RequestBody TicketRequestDto dto) {
        TicketResponseDto created = ticketService.createTicket(dto);
        return ResponseEntity.created(URI.create("/tickets/" + created.getId())).body(created);
    }

    // PUT /tickets/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDto> update(@PathVariable Long id, @RequestBody TicketRequestDto dto) {
        return ticketService.updateTicket(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // DELETE /tickets/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}

