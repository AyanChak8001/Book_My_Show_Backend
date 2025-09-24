package com.example.BookMyShow.Repository;
import com.example.BookMyShow.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface ShowRepository extends JpaRepository<Show, Long> {
    List<Show> findByScreenTheatreIdAndShowDateOrderByStartTime(Long theatreId, LocalDate date);

    List<Show> findByMovieIdAndShowDateOrderByStartTime(Long movieId, LocalDate date);

    // upcoming shows in a theatre between two dates (inclusive)
    List<Show> findByScreenTheatreIdAndShowDateBetweenOrderByShowDateAscStartTimeAsc(Long theatreId,
                                                                                     LocalDate startDate,
                                                                                     LocalDate endDate);

    // JPQL: fetch show with movie and screen eagerly (avoids lazy N+1 if used carefully)
    @Query("SELECT s FROM Show s JOIN FETCH s.movie m JOIN FETCH s.screen sc WHERE sc.theatre.id = :theatreId AND s.showDate = :showDate ORDER BY s.startTime")
    List<Show> findShowsWithMovieAndScreenByTheatreAndDate(@Param("theatreId") Long theatreId,
                                                           @Param("showDate") LocalDate showDate);

    // Example native aggregation (MySQL): aggregated show times per movie for the theatre/date
    // NOTE: This returns rows of (movie_title, times_csv) — you would map this to a DTO manually.
    @Query(value = "SELECT m.title AS movie_title, GROUP_CONCAT(DISTINCT DATE_FORMAT(s.start_time, '%H:%i') ORDER BY s.start_time SEPARATOR ', ') AS times " +
            "FROM show s " +
            "JOIN movie m ON s.movie_id = m.id " +
            "JOIN screen sc ON s.screen_id = sc.id " +
            "WHERE sc.theatre_id = :theatreId AND s.show_date = :showDate " +
            "GROUP BY m.id, m.title " +
            "ORDER BY MIN(s.start_time)",
            nativeQuery = true)
    List<Object[]> findAggregatedShowTimesByTheatreAndDate(@Param("theatreId") Long theatreId,
                                                           @Param("showDate") LocalDate showDate);
}
