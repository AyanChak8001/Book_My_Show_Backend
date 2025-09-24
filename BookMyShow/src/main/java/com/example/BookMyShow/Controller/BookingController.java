package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dto.BookingRequestDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Service.BookingService;
import org.springframework.web.bind.annotation.*;

import com.example.BookMyShow.Dto.BookingRequestDto;
import com.example.BookMyShow.Dto.BookingResponseDto;
import com.example.BookMyShow.Service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // GET all bookings or filter by userId/showId
    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getAll(
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestParam(value = "showId", required = false) Long showId
    ) {
        if (userId != null) {
            return ResponseEntity.ok(bookingService.getBookingsByUserId(userId));
        } else if (showId != null) {
            return ResponseEntity.ok(bookingService.getBookingsByShowId(showId));
        }
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // GET by id
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDto> getById(@PathVariable Long id) {
        return bookingService.getBookingById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // POST create
    @PostMapping
    public ResponseEntity<BookingResponseDto> create(@RequestBody BookingRequestDto dto) {
        BookingResponseDto created = bookingService.createBooking(dto);
        return ResponseEntity.created(URI.create("/bookings/" + created.getId())).body(created);
    }

    // PUT update
    @PutMapping("/{id}")
    public ResponseEntity<BookingResponseDto> update(@PathVariable Long id, @RequestBody BookingRequestDto dto) {
        return bookingService.updateBooking(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
