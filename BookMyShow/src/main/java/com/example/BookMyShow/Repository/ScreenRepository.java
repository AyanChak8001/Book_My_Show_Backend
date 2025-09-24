package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.example.BookMyShow.Entity.Screen;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
    List<Screen> findByTheatreId(Long theatreId);
}
