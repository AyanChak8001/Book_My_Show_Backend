package com.example.BookMyShow.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieRequestDto {
    private String title;
    private String language;
    private int durationMins;
    private String genre;
    private String certification;
}

