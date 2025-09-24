package com.example.BookMyShow.Dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    private String fullName;
    private String email;
    private String phone;
}
