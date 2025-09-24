package com.example.BookMyShow.Config;

import com.example.BookMyShow.Entity.*;
import com.example.BookMyShow.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class DataInitializer {

    @Bean
    @Profile("dev")
    CommandLineRunner initData(TheaterRepository theatreRepo,
                               ScreenRepository screenRepo,
                               MovieRepository movieRepo,
                               ShowRepository showRepo,
                               UserRepository userRepo) {
        return args -> {
            if (theatreRepo.count() > 0) return;

            Theater t = Theater.builder()
                    .name("PVR: Nexus")
                    .address("Mall Road, Floor 3")
                    .city("Hyderabad")
                    .state("Telangana")
                    .pincode("500001")
                    .build();
            theatreRepo.save(t);

            Screen s1 = Screen.builder().name("Screen 1").seatCapacity(200).theatre(t).build();
            Screen s2 = Screen.builder().name("Screen 2").seatCapacity(120).theatre(t).build();
            screenRepo.saveAll(List.of(s1, s2));

            Movie m1 = Movie.builder().title("Dasara").language("Telugu").durationMins(150).genre("Drama").certification("U/A").build();
            Movie m2 = Movie.builder().title("Kisi Ka Bhai Kisi Ki Jaan").language("Hindi").durationMins(160).genre("Action").certification("U/A").build();
            Movie m3 = Movie.builder().title("Avatar: The Way of Water").language("English").durationMins(192).genre("Sci-Fi").certification("U/A").build();
            movieRepo.saveAll(List.of(m1, m2, m3));

            LocalDate sampleDate = LocalDate.of(2025,4,25);
            Show sh1 = Show.builder().movie(m1).screen(s1).showDate(sampleDate).startTime(LocalTime.of(12,15)).endTime(LocalTime.of(14,45)).ticketPrice(180.0).format("2D").language("Telugu").build();
            Show sh2 = Show.builder().movie(m2).screen(s1).showDate(sampleDate).startTime(LocalTime.of(15,0)).endTime(LocalTime.of(17,40)).ticketPrice(200.0).format("2D").language("Hindi").build();
            Show sh3 = Show.builder().movie(m2).screen(s2).showDate(sampleDate).startTime(LocalTime.of(16,20)).endTime(LocalTime.of(18,50)).ticketPrice(220.0).format("2D").language("Hindi").build();
            Show sh4 = Show.builder().movie(m3).screen(s2).showDate(sampleDate).startTime(LocalTime.of(19,0)).endTime(LocalTime.of(22,12)).ticketPrice(300.0).format("3D").language("English").build();
            showRepo.saveAll(List.of(sh1, sh2, sh3, sh4));

            User u = User.builder().fullName("Demo User").email("demo@example.com").phone("9999999999").build();
            userRepo.save(u);
        };
    }
}
