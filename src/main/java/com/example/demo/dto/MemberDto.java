package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MemberDto (Long id,
                         String firstName,
                         String lastName,
                         String email,
                         LocalDateTime DateOfBirth
                         ) {

}
