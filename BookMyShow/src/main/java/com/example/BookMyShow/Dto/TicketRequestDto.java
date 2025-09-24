package com.example.BookMyShow.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketRequestDto {
    private Double price;
    private Long bookingId;
    private Long seatId;
}
