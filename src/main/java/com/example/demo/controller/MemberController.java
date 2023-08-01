package com.example.demo.controller;

import com.example.demo.dto.MemberDto;
import com.example.demo.entities.Member;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping
    public List<MemberDto> allMember(){
         return memberService.getall();
    }
    @PostMapping
    public void addMember(@RequestBody Member member){
         memberService.addMember(member);
    }
    @GetMapping("{MemberId}")
    public MemberDto getMemberById(@PathVariable("MemberId") Long MemberId) {
        return memberService.getMember(MemberId);
    }
    @PutMapping("{MemberId}")
    public void updateMember(@PathVariable("MemberId") Long MemberId,@RequestBody Member member) {
         memberService.updateMember(MemberId,member);
    }
    @DeleteMapping("{MemberId}")
    public void deleteMember(@PathVariable("MemberId") Long MemberId) {
        memberService.deleteMember(MemberId);
    }

}
