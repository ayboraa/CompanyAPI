package org.company.api.service.impl;

import org.company.api.common.Email;
import org.company.api.common.MemberId;
import org.company.api.entity.MemberEntity;
import org.company.api.service.Mapper;
import org.company.api.service.Member;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MemberMapper implements Mapper<Member, MemberEntity> {
    @Override
    public MemberEntity toEntity(Member member) {
        return new MemberEntity(member.getId().uuid(), member.getName(), member.getSurname(), member.getEmail().email(), member.getSalary(), member.getCurrency().getCurrencyCode(), member.isAdmin());
    }

    @Override
    public Member toDTO(MemberEntity entity) {
        return new Member(new MemberId(entity.getId()), entity.getFirstName(), entity.getLastName(), new Email(entity.getEmail()), entity.getSalary(), Currency.getInstance(entity.getCurrency()), entity.isAdmin());
    }

    @Override
    public List<Member> toDTOList(List<MemberEntity> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

    }
}
