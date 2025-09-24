package com.example.BookMyShow.Dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketResponseDto {
    private Long id;
    private Double price;
    private Long bookingId;
    private Long seatId;

    // convenience fields (read-only)
    private String seatRowLabel;
    private Integer seatNumber;
}
