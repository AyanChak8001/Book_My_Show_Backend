package com.example.BookMyShow.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatResponseDto {
    private Long id;
    private String rowLabel;
    private int seatNumber;
    private String seatType;
    private Long screenId;
    private String screenName;
}
