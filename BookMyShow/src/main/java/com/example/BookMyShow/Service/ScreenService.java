package com.example.BookMyShow.Service;

import com.example.BookMyShow.Dto.ScreenRequestDto;
import com.example.BookMyShow.Dto.ScreenResponseDto;

import java.util.List;

public interface ScreenService {
    List<ScreenResponseDto> getAllScreens();
    ScreenResponseDto getScreenById(Long id);
    List<ScreenResponseDto> getScreensByTheatreId(Long theatreId);
    ScreenResponseDto createScreen(ScreenRequestDto dto);
    ScreenResponseDto updateScreen(Long id, ScreenRequestDto dto);
    void deleteScreen(Long id);
}
