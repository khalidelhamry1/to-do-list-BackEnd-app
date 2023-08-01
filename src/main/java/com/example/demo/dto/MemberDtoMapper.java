package com.example.demo.dto;

import com.example.demo.entities.Member;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class MemberDtoMapper implements Function<Member,MemberDto> {

    @Override
    public MemberDto apply(Member member) {
        return  new MemberDto(
                member.getId(),
                member.getFirstName(),
                member.getLastName(),
                member.getEmail(),
                member.getDateOfBirth()
        );
    }
}
