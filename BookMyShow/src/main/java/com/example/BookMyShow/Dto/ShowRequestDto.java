package com.example.BookMyShow.Dto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShowRequestDto {
    private LocalDate showDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double ticketPrice;
    private String format;
    private String language;
    private Long movieId;
    private Long screenId;
}

