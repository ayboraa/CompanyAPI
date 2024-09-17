package org.company.api;


import org.company.api.common.Email;
import org.company.api.entity.MemberEntity;
import org.company.api.repository.MemberRepository;
import org.company.api.service.Member;
import org.company.api.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Currency;
import java.util.Locale;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureTestDatabase
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testInsertMember() {
        Member member = new Member("Jane", "Smith", new Email("janesmith@example.com"), 6000.0f, Currency.getInstance(Locale.GERMANY), true);

        MemberEntity savedMember = memberService.saveMember(member);

        System.out.println("Inserted Member: " + savedMember);

        assertThat(savedMember).isNotNull();
        assertThat(savedMember.getFirstName()).isEqualTo("Jane");
        assertThat(savedMember.getEmail()).isEqualTo("janesmith@example.com");

        MemberEntity foundMember = memberRepository.findById(savedMember.getId()).orElse(null);
        assertThat(foundMember).isNotNull();
        assertThat(foundMember.getFirstName()).isEqualTo("Jane");
        assertThat(foundMember.getEmail()).isEqualTo("janesmith@example.com");
    }
}
