package com.example.BookMyShow.Impl;

import com.example.BookMyShow.Dto.ShowRequestDto;
import com.example.BookMyShow.Dto.ShowResponseDto;
import com.example.BookMyShow.Entity.Movie;
import com.example.BookMyShow.Entity.Screen;
import com.example.BookMyShow.Entity.Show;
import com.example.BookMyShow.Repository.MovieRepository;
import com.example.BookMyShow.Repository.ScreenRepository;
import com.example.BookMyShow.Repository.ShowRepository;
import org.springframework.stereotype.Service;
import com.example.BookMyShow.Service.ShowService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;

    public ShowServiceImpl(ShowRepository showRepository,
                           MovieRepository movieRepository,
                           ScreenRepository screenRepository) {
        this.showRepository = showRepository;
        this.movieRepository = movieRepository;
        this.screenRepository = screenRepository;
    }

    private ShowResponseDto toDto(Show s) {
        Movie m = s.getMovie();
        Screen sc = s.getScreen();
        return ShowResponseDto.builder()
                .showid(s.getId())
                .showDate(s.getShowDate())
                .startTime(s.getStartTime())
                .endTime(s.getEndTime())
                .ticketPrice(s.getTicketPrice())
                .format(s.getFormat())
                .language(s.getLanguage())
                .movieId(m != null ? m.getId() : null)
                .movieTitle(m != null ? m.getTitle() : null)
                .screenId(sc != null ? sc.getId() : null)
                .screenName(sc != null ? sc.getName() : null)
                .theatreId(sc != null && sc.getTheatre() != null ? sc.getTheatre().getId() : null)
                .theatreName(sc != null && sc.getTheatre() != null ? sc.getTheatre().getName() : null)
                .build();
    }

    @Override
    public List<ShowResponseDto> getAllShows() {
        return showRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ShowResponseDto> getShowById(Long id) {
        return showRepository.findById(id).map(this::toDto);
    }

    @Override
    public List<ShowResponseDto> getShowsByTheatreAndDate(Long theatreId, LocalDate date) {
        return showRepository.findByScreenTheatreIdAndShowDateOrderByStartTime(theatreId, date)
                .stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public ShowResponseDto createShow(ShowRequestDto dto) {
        // Validate movie + screen
        Movie movie = null;
        if (dto.getMovieId() != null) {
            movie = movieRepository.findById(dto.getMovieId())
                    .orElseThrow(() -> new RuntimeException("Movie not found: " + dto.getMovieId()));
        }
        Screen screen = null;
        if (dto.getScreenId() != null) {
            screen = screenRepository.findById(dto.getScreenId())
                    .orElseThrow(() -> new RuntimeException("Screen not found: " + dto.getScreenId()));
        }

        Show s = new Show();
        s.setShowDate(dto.getShowDate());
        s.setStartTime(dto.getStartTime());
        s.setEndTime(dto.getEndTime());
        s.setTicketPrice(dto.getTicketPrice());
        s.setFormat(dto.getFormat());
        s.setLanguage(dto.getLanguage());
        s.setMovie(movie);
        s.setScreen(screen);

        Show saved = showRepository.save(s);
        return toDto(saved);
    }

    @Override
    public Optional<ShowResponseDto> updateShow(Long id, ShowRequestDto dto) {
        Optional<Show> opt = showRepository.findById(id);
        if (opt.isEmpty()) return Optional.empty();

        Show s = opt.get();

        // Validate foreign keys if provided
        if (dto.getMovieId() != null) {
            Movie movie = movieRepository.findById(dto.getMovieId()).orElse(null);
            s.setMovie(movie);
        }
        if (dto.getScreenId() != null) {
            Screen screen = screenRepository.findById(dto.getScreenId()).orElse(null);
            s.setScreen(screen);
        }

        s.setShowDate(dto.getShowDate());
        s.setStartTime(dto.getStartTime());
        s.setEndTime(dto.getEndTime());
        s.setTicketPrice(dto.getTicketPrice());
        s.setFormat(dto.getFormat());
        s.setLanguage(dto.getLanguage());

        Show saved = showRepository.save(s);
        return Optional.of(toDto(saved));
    }

    @Override
    public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }
}

