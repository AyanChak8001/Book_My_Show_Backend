package com.example.BookMyShow.Dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingRequestDto {
    private Long userId;
    private Long showId;
    private Integer numberOfSeats;
    private LocalDateTime bookingTime;
}
