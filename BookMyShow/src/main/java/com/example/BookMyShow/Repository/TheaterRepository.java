package com.example.BookMyShow.Repository;
import com.example.BookMyShow.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    List<Theater> findByCity(String city);
    List<Theater> findByNameContainingIgnoreCase(String nameFragment);
}
