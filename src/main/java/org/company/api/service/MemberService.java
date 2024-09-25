package org.company.api.service;


import jakarta.transaction.Transactional;
import org.company.api.common.MemberId;
import org.company.api.controller.exception.ResourceNotFoundException;
import org.company.api.entity.MemberEntity;
import org.company.api.repository.MemberRepository;
import org.company.api.service.impl.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    private final MemberMapper _mapper = new MemberMapper();

    public Member saveMember(Member member){
        MemberEntity entity = new MemberEntity(new MemberId().uuid(), member.getName(), member.getSurname(), member.getEmail().email(), member.getSalary(), member.getCurrency().getCurrencyCode(), member.isAdmin());
        memberRepository.save(entity);
        return _mapper.toDTO(entity);
    }


    public Member findMemberById(MemberId id) {

        Optional<MemberEntity> opt = memberRepository.findById(id.uuid());
        if(opt.isEmpty())
            throw new ResourceNotFoundException("Member with ID " + id.uuid() + " not found.");
        else
            return _mapper.toDTO(opt.get());

    }

    public void deleteMember(MemberId id) {

        if (!memberRepository.existsById(id.uuid())) {
            throw new ResourceNotFoundException("Member not found with id: " + id.uuid());
        }

        memberRepository.deleteById(id.uuid());

    }

    public List<Member> getAllMembers() {
        List<MemberEntity> entities = memberRepository.findAll();
        return _mapper.toDTOList(entities);
    }

    @Transactional
    public Member updateMember(MemberId id, Member newMember) {
        return memberRepository.findById(id.uuid())
                .map(memberEntity -> {
                    memberEntity.setFirstName(newMember.getName());
                    memberEntity.setLastName(newMember.getSurname());
                    memberEntity.setEmail(newMember.getEmail().email());
                    memberEntity.setCurrency(newMember.getCurrency().getCurrencyCode());
                    memberEntity.setSalary(newMember.getSalary());
                    memberEntity.setAdmin(newMember.isAdmin());
                    return _mapper.toDTO(memberRepository.save(memberEntity));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with ID: " + id.uuid()));
    }

}
