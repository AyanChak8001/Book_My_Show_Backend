package com.example.BookMyShow.Service;


import com.example.BookMyShow.Dto.BookingRequestDto;
import com.example.BookMyShow.Dto.BookingResponseDto;

import java.util.List;
import java.util.Optional;

public interface BookingService {
    List<BookingResponseDto> getAllBookings();
    Optional<BookingResponseDto> getBookingById(Long id);
    List<BookingResponseDto> getBookingsByUserId(Long userId);
    List<BookingResponseDto> getBookingsByShowId(Long showId);
    BookingResponseDto createBooking(BookingRequestDto dto);
    Optional<BookingResponseDto> updateBooking(Long id, BookingRequestDto dto);
    void deleteBooking(Long id);
}

