package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.ShowResponseDto;

import java.time.LocalDate;
import java.util.List;

import com.example.BookMyShow.Dto.ShowRequestDto;
import com.example.BookMyShow.Dto.ShowResponseDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShowService {
    List<ShowResponseDto> getAllShows();
    Optional<ShowResponseDto> getShowById(Long id);
    List<ShowResponseDto> getShowsByTheatreAndDate(Long theatreId, LocalDate date);
    ShowResponseDto createShow(ShowRequestDto dto);
    Optional<ShowResponseDto> updateShow(Long id, ShowRequestDto dto);
    void deleteShow(Long id);
}
