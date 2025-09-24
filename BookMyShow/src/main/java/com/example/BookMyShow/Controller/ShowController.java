package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dto.ShowResponseDto;
import com.example.BookMyShow.Service.ShowService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import com.example.BookMyShow.Dto.ShowRequestDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping
    public ResponseEntity<List<ShowResponseDto>> getAll() {
        return ResponseEntity.ok(showService.getAllShows());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowResponseDto> getById(@PathVariable Long id) {
        return showService.getShowById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get shows for a theatre on a date.
     * Example: GET /shows/theatre/1?date=2025-09-24
     */
    @GetMapping("/theatre/{theatreId}")
    public ResponseEntity<List<ShowResponseDto>> getByTheatreAndDate(
            @PathVariable Long theatreId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(showService.getShowsByTheatreAndDate(theatreId, date));
    }

    @PostMapping
    public ResponseEntity<ShowResponseDto> create(@RequestBody ShowRequestDto dto) {
        ShowResponseDto created = showService.createShow(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowResponseDto> update(@PathVariable Long id, @RequestBody ShowRequestDto dto) {
        return showService.updateShow(id, dto).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        showService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }
}
