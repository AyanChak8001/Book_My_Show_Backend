package com.example.BookMyShow.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rowLabel;
    private int seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;
}
