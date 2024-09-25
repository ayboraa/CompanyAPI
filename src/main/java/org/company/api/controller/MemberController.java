package org.company.api.controller;


import org.company.api.common.MemberId;
import org.company.api.service.Member;
import org.company.api.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/")
    public ResponseEntity<List<Member>> getMembers() {
        List<Member> members = memberService.getAllMembers();

        return ResponseEntity.ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberById(@PathVariable UUID id) {
        Member member = memberService.findMemberById(new MemberId(id));
        return ResponseEntity.ok(member);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable UUID id, @RequestBody Member newMember) {
        Member updatedMemberData = memberService.updateMember(new MemberId(id), newMember);
        return ResponseEntity.ok(updatedMemberData);
    }

    @PostMapping("/")
    public ResponseEntity<Member> createMember(@RequestBody Member newMember) {
        Member createdMember = memberService.saveMember(newMember);
        return ResponseEntity.ok(createdMember);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable UUID id) {
        memberService.deleteMember(new MemberId(id));
        return ResponseEntity.ok().build();
    }



}
