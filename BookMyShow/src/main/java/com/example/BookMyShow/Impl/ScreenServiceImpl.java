package com.example.BookMyShow.Impl;

import com.example.BookMyShow.Dto.ScreenRequestDto;
import com.example.BookMyShow.Dto.ScreenResponseDto;
import com.example.BookMyShow.Entity.Screen;
import com.example.BookMyShow.Entity.Theater;
import com.example.BookMyShow.Repository.ScreenRepository;
import com.example.BookMyShow.Repository.TheaterRepository;
import com.example.BookMyShow.Service.ScreenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class ScreenServiceImpl implements ScreenService {

    private final ScreenRepository screenRepository;
    private final TheaterRepository theatreRepository;

    public ScreenServiceImpl(ScreenRepository screenRepository, TheaterRepository theatreRepository) {
        this.screenRepository = screenRepository;
        this.theatreRepository = theatreRepository;
    }

    private ScreenResponseDto toDto(Screen s) {
        String theatreName = null;
        if (s.getTheatre() != null) theatreName = s.getTheatre().getName();
        return ScreenResponseDto.builder()
                .id(s.getId())
                .name(s.getName())
                .seatCapacity(s.getSeatCapacity())
                .theatreId(s.getTheatre() != null ? s.getTheatre().getId() : null)
                .theatreName(theatreName)
                .build();
    }

    private Screen toEntity(ScreenRequestDto dto) {
        Screen s = new Screen();
        s.setName(dto.getName());
        s.setSeatCapacity(dto.getSeatCapacity());
        if (dto.getTheatreId() != null) {
            Optional<Theater> t = theatreRepository.findById(dto.getTheatreId());
            t.ifPresent(s::setTheatre);
        }
        return s;
    }

    @Override
    public List<ScreenResponseDto> getAllScreens() {
        return screenRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ScreenResponseDto getScreenById(Long id) {
        return screenRepository.findById(id).map(this::toDto).orElse(null);
    }

    @Override
    public List<ScreenResponseDto> getScreensByTheatreId(Long theatreId) {
        return screenRepository.findByTheatreId(theatreId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ScreenResponseDto createScreen(ScreenRequestDto dto) {
        Screen e = toEntity(dto);
        Screen saved = screenRepository.save(e);
        return toDto(saved);
    }

    @Override
    public ScreenResponseDto updateScreen(Long id, ScreenRequestDto dto) {
        return screenRepository.findById(id).map(existing -> {
            existing.setName(dto.getName());
            existing.setSeatCapacity(dto.getSeatCapacity());
            if (dto.getTheatreId() != null) {
                theatreRepository.findById(dto.getTheatreId()).ifPresent(existing::setTheatre);
            }
            Screen saved = screenRepository.save(existing);
            return toDto(saved);
        }).orElse(null);
    }

    @Override
    public void deleteScreen(Long id) {
        screenRepository.deleteById(id);
    }
}
