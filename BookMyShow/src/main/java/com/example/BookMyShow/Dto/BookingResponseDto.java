package com.example.BookMyShow.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponseDto {
    private Long id;
    private Long userId;
    private String userName;
    private Long showId;
    private String movieTitle;
    private String screenName;
    private LocalDateTime bookingTime;
    private Integer numberOfSeats;
}

