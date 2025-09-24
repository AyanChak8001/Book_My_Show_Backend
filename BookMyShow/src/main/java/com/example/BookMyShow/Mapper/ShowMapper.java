package com.example.BookMyShow.Mapper;


import com.example.BookMyShow.Dto.ShowResponseDto;
import com.example.BookMyShow.Entity.Show;
import org.springframework.stereotype.Component;

@Component
public class ShowMapper {
    public ShowResponseDto toDto(Show show) {
        return ShowResponseDto.builder()
                .showid(show.getId())
                .movieTitle(show.getMovie().getTitle())
                .screenName(show.getScreen().getName())
                .showDate(show.getShowDate())
                .startTime(show.getStartTime())
                .endTime(show.getEndTime())
                .format(show.getFormat())
                .language(show.getLanguage())
                .ticketPrice(show.getTicketPrice())
                .build();
    }
}
