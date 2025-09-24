package com.example.BookMyShow.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreenRequestDto {
    private String name;
    private int seatCapacity;
    private Long theatreId; // id of the theatre this screen belongs to
}
