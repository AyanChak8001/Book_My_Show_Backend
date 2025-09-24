package com.example.BookMyShow.Repository;
import com.example.BookMyShow.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByLanguageIgnoreCase(String language);
    List<Movie> findByGenreIgnoreCase(String genre);
    Optional<Movie> findByTitleIgnoreCase(String title);
}
