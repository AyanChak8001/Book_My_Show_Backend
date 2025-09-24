package com.example.BookMyShow.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatRequestDto {
    private String rowLabel;
    private int seatNumber;
    private String seatType; // "REGULAR", "PREMIUM", "RECLINER" - maps to enum
    private Long screenId;
}

