package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dto.ScreenRequestDto;
import com.example.BookMyShow.Dto.ScreenResponseDto;
import com.example.BookMyShow.Service.ScreenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/screens")
public class ScreenController {

    private final ScreenService screenService;

    public ScreenController(ScreenService screenService) {
        this.screenService = screenService;
    }

    @GetMapping
    public ResponseEntity<List<ScreenResponseDto>> getAll(@RequestParam(value = "theatreId", required = false) Long theatreId) {
        if (theatreId != null) {
            return ResponseEntity.ok(screenService.getScreensByTheatreId(theatreId));
        } else {
            return ResponseEntity.ok(screenService.getAllScreens());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScreenResponseDto> getById(@PathVariable Long id) {
        ScreenResponseDto dto = screenService.getScreenById(id);
        return (dto == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ScreenResponseDto> create(@RequestBody ScreenRequestDto dto) {
        ScreenResponseDto created = screenService.createScreen(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScreenResponseDto> update(@PathVariable Long id, @RequestBody ScreenRequestDto dto) {
        ScreenResponseDto updated = screenService.updateScreen(id, dto);
        return (updated == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        screenService.deleteScreen(id);
        return ResponseEntity.noContent().build();
    }
}
