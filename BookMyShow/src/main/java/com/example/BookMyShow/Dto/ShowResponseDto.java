package com.example.BookMyShow.Dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowResponseDto {
    private Long showid;
    private LocalDate showDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double ticketPrice;
    private String format;
    private String language;
    private Long movieId;
    private String movieTitle;
    private Long screenId;
    private String screenName;
    private Long theatreId;    // convenience (screen.theatre.id)
    private String theatreName; // convenience
}

