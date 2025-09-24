package com.example.BookMyShow.Service;


import com.example.BookMyShow.Entity.Theater;

import java.util.List;

import com.example.BookMyShow.Dto.TheatreRequestDto;
import com.example.BookMyShow.Dto.TheatreResponseDto;

public interface TheatreService {
    List<TheatreResponseDto> getAllTheatres();
    TheatreResponseDto getTheatreById(Long id);
    TheatreResponseDto createTheatre(TheatreRequestDto dto);
    TheatreResponseDto updateTheatre(Long id, TheatreRequestDto dto);
    void deleteTheatre(Long id);
}
