package com.example.BookMyShow.Impl;

import com.example.BookMyShow.Dto.SeatRequestDto;
import com.example.BookMyShow.Dto.SeatResponseDto;
import com.example.BookMyShow.Entity.Seat;
import com.example.BookMyShow.Entity.Screen;
import com.example.BookMyShow.Entity.SeatType;
import com.example.BookMyShow.Repository.SeatRepository;
import com.example.BookMyShow.Repository.ScreenRepository;
import org.springframework.stereotype.Service;
import com.example.BookMyShow.Service.SeatService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final ScreenRepository screenRepository;

    public SeatServiceImpl(SeatRepository seatRepository, ScreenRepository screenRepository) {
        this.seatRepository = seatRepository;
        this.screenRepository = screenRepository;
    }

    private SeatResponseDto toDto(Seat s) {
        return SeatResponseDto.builder()
                .id(s.getId())
                .rowLabel(s.getRowLabel())
                .seatNumber(s.getSeatNumber())
                .seatType(s.getSeatType() != null ? s.getSeatType().name() : null)
                .screenId(s.getScreen() != null ? s.getScreen().getId() : null)
                .screenName(s.getScreen() != null ? s.getScreen().getName() : null)
                .build();
    }

    @Override
    public List<SeatResponseDto> getAllSeats() {
        return seatRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<SeatResponseDto> getSeatById(Long id) {
        return seatRepository.findById(id).map(this::toDto);
    }

    @Override
    public List<SeatResponseDto> getSeatsByScreenId(Long screenId) {
        return seatRepository.findByScreenId(screenId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public SeatResponseDto createSeat(SeatRequestDto dto) {
        Seat s = new Seat();
        s.setRowLabel(dto.getRowLabel());
        s.setSeatNumber(dto.getSeatNumber());
        if (dto.getSeatType() != null) {
            try {
                s.setSeatType(SeatType.valueOf(dto.getSeatType()));
            } catch (IllegalArgumentException e) {
                // ignore or throw custom BadRequest; for now set null
                s.setSeatType(null);
            }
        }
        if (dto.getScreenId() != null) {
            Optional<Screen> sc = screenRepository.findById(dto.getScreenId());
            sc.ifPresent(s::setScreen);
        }
        Seat saved = seatRepository.save(s);
        return toDto(saved);
    }

    @Override
    public Optional<SeatResponseDto> updateSeat(Long id, SeatRequestDto dto) {
        Optional<Seat> opt = seatRepository.findById(id);
        if (opt.isEmpty()) return Optional.empty();

        Seat s = opt.get();
        s.setRowLabel(dto.getRowLabel());
        s.setSeatNumber(dto.getSeatNumber());
        if (dto.getSeatType() != null) {
            try {
                s.setSeatType(SeatType.valueOf(dto.getSeatType()));
            } catch (IllegalArgumentException e) {
                // invalid enum - ignore or handle properly
            }
        }
        if (dto.getScreenId() != null) {
            Optional<Screen> sc = screenRepository.findById(dto.getScreenId());
            sc.ifPresent(s::setScreen); // if not found, we simply don't change screen
        }
        Seat saved = seatRepository.save(s);
        return Optional.of(toDto(saved));
    }

    @Override
    public void deleteSeat(Long id) {
        seatRepository.deleteById(id);
    }
}
