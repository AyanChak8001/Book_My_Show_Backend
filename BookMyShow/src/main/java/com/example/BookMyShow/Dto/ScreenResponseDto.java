package com.example.BookMyShow.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreenResponseDto {
    private Long id;
    private String name;
    private int seatCapacity;
    private Long theatreId;
    private String theatreName;
}

