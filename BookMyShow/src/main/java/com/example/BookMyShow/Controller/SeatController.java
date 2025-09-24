package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dto.SeatRequestDto;
import com.example.BookMyShow.Dto.SeatResponseDto;
import com.example.BookMyShow.Service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping
    public ResponseEntity<List<SeatResponseDto>> getAll(@RequestParam(value = "screenId", required = false) Long screenId) {
        if (screenId != null) {
            return ResponseEntity.ok(seatService.getSeatsByScreenId(screenId));
        } else {
            return ResponseEntity.ok(seatService.getAllSeats());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatResponseDto> getById(@PathVariable Long id) {
        return seatService.getSeatById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SeatResponseDto> create(@RequestBody SeatRequestDto dto) {
        SeatResponseDto created = seatService.createSeat(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatResponseDto> update(@PathVariable Long id, @RequestBody SeatRequestDto dto) {
        return seatService.updateSeat(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        seatService.deleteSeat(id);
        return ResponseEntity.noContent().build();
    }
}

