package com.example.BookMyShow.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TheatreRequestDto {
    private String name;
    private String address;
    private String city;
    private String state;
    private String pincode;
}
