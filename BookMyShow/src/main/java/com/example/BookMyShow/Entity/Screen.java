package com.example.BookMyShow.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int seatCapacity;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theater theatre;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
    private List<Show> shows;

    @OneToMany(mappedBy = "screen", cascade = CascadeType.ALL)
    private List<Seat> seats;
}
