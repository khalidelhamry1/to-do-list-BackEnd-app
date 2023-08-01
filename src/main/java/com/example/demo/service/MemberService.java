package com.example.demo.service;

import com.example.demo.dto.MemberDto;
import com.example.demo.dto.MemberDtoMapper;
import com.example.demo.entities.Member;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.MemberRepository;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberDtoMapper memberDtoMapper;
    public MemberService(MemberRepository memberRepository, MemberDtoMapper memberDtoMapper) {
        this.memberRepository = memberRepository;
        this.memberDtoMapper = memberDtoMapper;
    }
    public List<MemberDto> getall() {
        return memberRepository.findAll()
                .stream()
                .map(memberDtoMapper)
                .collect(Collectors.toList());

    }
    public void addMember(Member member) {
         memberRepository.save(member);
    }

    public MemberDto getMember(Long memberId) throws ResourceNotFoundException {
       return memberRepository.findById(memberId)
               .map(memberDtoMapper)
               .orElseThrow(()->new ResourceNotFoundException("member not found"));
    }
    public void updateMember(Long memberId,Member member) {
        Member member1= memberRepository.findById(memberId)
        .orElseThrow(()->new ResourceNotFoundException("member not found"));

        member1.setFirstName(member.getFirstName());
        member1.setLastName(member.getLastName());
        member1.setEmail(member.getEmail());
        member1.setDateOfBirth(member.getDateOfBirth());
        memberRepository.save(member1);
    }
    public void deleteMember(Long memberId) {
        Member member1= memberRepository.findById(memberId)
                .orElseThrow(()->new ResourceNotFoundException("member not found"));
        memberRepository.deleteById(memberId);
    }
}
