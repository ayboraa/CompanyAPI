package org.company.api.service;


import org.company.api.common.MemberId;
import org.company.api.entity.MemberEntity;
import org.company.api.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;


    public MemberEntity saveMember(Member member){
        MemberEntity entity = new MemberEntity(member.getId().uuid(), member.getName(), member.getSurname(), member.getEmail().email(), member.getSalary(), member.getCurrency().getCurrencyCode(), member.isAdmin());
        memberRepository.save(entity);
        return entity;
    }


    public Optional<MemberEntity> findMemberById(MemberId id) {
        return memberRepository.findById(id.uuid());
    }

}
