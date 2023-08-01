package com.example.demo.repository;

import com.example.demo.entities.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Test
    public void saveMember(){
        Member member=Member.builder()
                .email("khalid@gmail.com")
                .firstName("khalid")
                .lastName("elhamry")
                .password("root")
                //.dateOfBirth(2002-02-02)
                .build();
        memberRepository.save(member);
    }

}