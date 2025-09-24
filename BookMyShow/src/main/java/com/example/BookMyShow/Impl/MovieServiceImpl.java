package com.example.BookMyShow.Impl;

import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Service.MovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Dto.MovieRequestDto;
import com.example.BookMyShow.Repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    @Override
    public Movie createMovie(MovieRequestDto dto) {
        Movie movie = Movie.builder()
                .title(dto.getTitle())
                .language(dto.getLanguage())
                .durationMins(dto.getDurationMins())
                .genre(dto.getGenre())
                .certification(dto.getCertification())
                .build();
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Long id, MovieRequestDto dto) {
        Optional<Movie> opt = movieRepository.findById(id);
        if (opt.isEmpty()) return null;
        Movie m = opt.get();
        m.setTitle(dto.getTitle());
        m.setLanguage(dto.getLanguage());
        m.setDurationMins(dto.getDurationMins());
        m.setGenre(dto.getGenre());
        m.setCertification(dto.getCertification());
        return movieRepository.save(m);
    }

    @Override
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
