package com.example.demo.dto;

import java.time.LocalDate;

public record MemberDto (Long id,
                         String firstName,
                         String lastName,
                         String email,
                         LocalDate DateOfBirth
                         ) {


}
