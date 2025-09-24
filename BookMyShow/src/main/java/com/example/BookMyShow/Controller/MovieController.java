package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Dto.MovieRequestDto;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable Long id) {
        Movie m = movieService.getMovieById(id);
        return (m == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(m);
    }

    // <<-- This is the important POST method you need
    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody MovieRequestDto dto) {
        Movie created = movieService.createMovie(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody MovieRequestDto dto) {
        Movie updated = movieService.updateMovie(id, dto);
        return (updated == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}
