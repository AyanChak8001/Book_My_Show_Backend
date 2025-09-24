package com.example.BookMyShow.Service;

import com.example.BookMyShow.Entity.Movie;

import java.util.List;

import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Dto.MovieRequestDto;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie getMovieById(Long id);
    Movie createMovie(MovieRequestDto dto);
    Movie updateMovie(Long id, MovieRequestDto dto);
    void deleteMovie(Long id);
}

