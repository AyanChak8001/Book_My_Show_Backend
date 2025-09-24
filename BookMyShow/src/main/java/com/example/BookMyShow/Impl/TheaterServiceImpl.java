package com.example.BookMyShow.Impl;


import com.example.BookMyShow.Entity.Theater;
import org.springframework.stereotype.Service;
import com.example.BookMyShow.Service.TheatreService;
import java.util.ArrayList;
import java.util.List;

import com.example.BookMyShow.Dto.TheatreRequestDto;
import com.example.BookMyShow.Dto.TheatreResponseDto;
import com.example.BookMyShow.Entity.Theater;
import com.example.BookMyShow.Repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterServiceImpl implements TheatreService {

    private final TheaterRepository theatreRepository;

    public TheaterServiceImpl(TheaterRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    private TheatreResponseDto toDto(Theater t) {
        return TheatreResponseDto.builder()
                .id(t.getId())
                .name(t.getName())
                .address(t.getAddress())
                .city(t.getCity())
                .state(t.getState())
                .pincode(t.getPincode())
                .build();
    }

    private Theater toEntity(TheatreRequestDto dto) {
        Theater t = new Theater();
        t.setName(dto.getName());
        t.setAddress(dto.getAddress());
        t.setCity(dto.getCity());
        t.setState(dto.getState());
        t.setPincode(dto.getPincode());
        return t;
    }

    @Override
    public List<TheatreResponseDto> getAllTheatres() {
        return theatreRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TheatreResponseDto getTheatreById(Long id) {
        return theatreRepository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public TheatreResponseDto createTheatre(TheatreRequestDto dto) {
        Theater t = toEntity(dto);
        Theater saved = theatreRepository.save(t);
        return toDto(saved);
    }

    @Override
    public TheatreResponseDto updateTheatre(Long id, TheatreRequestDto dto) {
        return theatreRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setAddress(dto.getAddress());
            existing.setCity(dto.getCity());
            existing.setState(dto.getState());
            existing.setPincode(dto.getPincode());
            Theater saved = theatreRepository.save(existing);
            return toDto(saved);
        }).orElse(null);
    }

    @Override
    public void deleteTheatre(Long id) {
        theatreRepository.deleteById(id);
    }
}

