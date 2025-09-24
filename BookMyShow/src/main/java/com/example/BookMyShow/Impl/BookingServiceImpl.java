package com.example.BookMyShow.Impl;

import com.example.BookMyShow.Dto.BookingRequestDto;
import com.example.BookMyShow.Dto.BookingResponseDto;
import com.example.BookMyShow.Entity.Booking;
import com.example.BookMyShow.Entity.Show;
import com.example.BookMyShow.Entity.User;
import com.example.BookMyShow.Repository.BookingRepository;
import com.example.BookMyShow.Repository.ShowRepository;
import com.example.BookMyShow.Repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.BookMyShow.Service.BookingService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              UserRepository userRepository,
                              ShowRepository showRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
    }

    private BookingResponseDto toDto(Booking b) {
        Show show = b.getShow();
        User user = b.getUser();
        return BookingResponseDto.builder()
                .id(b.getId())
                .userId(user != null ? user.getId() : null)
                .userName(user != null ? user.getFullName() : null)
                .showId(show != null ? show.getId() : null)
                .movieTitle(show != null ? show.getMovie().getTitle() : null)
                .screenName(show != null ? show.getScreen().getName() : null)
                .bookingTime(b.getBookingTime())
                .numberOfSeats(b.getNumberOfSeats())
                .build();
    }

    @Override
    public List<BookingResponseDto> getAllBookings() {
        return bookingRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<BookingResponseDto> getBookingById(Long id) {
        return bookingRepository.findById(id).map(this::toDto);
    }

    @Override
    public List<BookingResponseDto> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserId(userId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<BookingResponseDto> getBookingsByShowId(Long showId) {
        return bookingRepository.findByShowId(showId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public BookingResponseDto createBooking(BookingRequestDto dto) {
        Booking b = new Booking();
        b.setNumberOfSeats(dto.getNumberOfSeats());
        b.setBookingTime(dto.getBookingTime());

        if (dto.getUserId() != null) {
            userRepository.findById(dto.getUserId()).ifPresent(b::setUser);
        }

        if (dto.getShowId() != null) {
            showRepository.findById(dto.getShowId()).ifPresent(b::setShow);
        }

        Booking saved = bookingRepository.save(b);
        return toDto(saved);
    }

    @Override
    public Optional<BookingResponseDto> updateBooking(Long id, BookingRequestDto dto) {
        Optional<Booking> opt = bookingRepository.findById(id);
        if (opt.isEmpty()) return Optional.empty();

        Booking b = opt.get();
        b.setNumberOfSeats(dto.getNumberOfSeats());
        b.setBookingTime(dto.getBookingTime());

        if (dto.getUserId() != null) {
            userRepository.findById(dto.getUserId()).ifPresent(b::setUser);
        }

        if (dto.getShowId() != null) {
            showRepository.findById(dto.getShowId()).ifPresent(b::setShow);
        }

        Booking saved = bookingRepository.save(b);
        return Optional.of(toDto(saved));
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
