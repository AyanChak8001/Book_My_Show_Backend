package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.SeatRequestDto;
import com.example.BookMyShow.Dto.SeatResponseDto;

import java.util.List;
import java.util.Optional;

public interface SeatService {
    List<SeatResponseDto> getAllSeats();
    Optional<SeatResponseDto> getSeatById(Long id);
    List<SeatResponseDto> getSeatsByScreenId(Long screenId);
    SeatResponseDto createSeat(SeatRequestDto dto);
    Optional<SeatResponseDto> updateSeat(Long id, SeatRequestDto dto);
    void deleteSeat(Long id);
}

