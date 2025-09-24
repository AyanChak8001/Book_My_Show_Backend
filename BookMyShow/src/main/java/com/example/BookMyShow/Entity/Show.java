package com.example.BookMyShow.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "movie_show")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate showDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double ticketPrice;
    private String format;
    private String language;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<Booking> bookings;
}
