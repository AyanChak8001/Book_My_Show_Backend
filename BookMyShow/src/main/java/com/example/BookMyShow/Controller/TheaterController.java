package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Service.TheatreService;
import org.springframework.web.bind.annotation.*;

import com.example.BookMyShow.Dto.TheatreRequestDto;
import com.example.BookMyShow.Dto.TheatreResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/theatres")
public class TheaterController {

    private final TheatreService theatreService;

    public TheaterController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    @GetMapping
    public ResponseEntity<List<TheatreResponseDto>> getAll() {
        return ResponseEntity.ok(theatreService.getAllTheatres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TheatreResponseDto> getById(@PathVariable Long id) {
        TheatreResponseDto dto = theatreService.getTheatreById(id);
        return (dto == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<TheatreResponseDto> create(@RequestBody TheatreRequestDto dto) {
        TheatreResponseDto created = theatreService.createTheatre(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TheatreResponseDto> update(@PathVariable Long id, @RequestBody TheatreRequestDto dto) {
        TheatreResponseDto updated = theatreService.updateTheatre(id, dto);
        return (updated == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        theatreService.deleteTheatre(id);
        return ResponseEntity.noContent().build();
    }
}
