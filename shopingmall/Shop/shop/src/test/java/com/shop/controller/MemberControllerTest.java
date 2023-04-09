package com.shop.controller;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import com.shop.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc//1
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
class MemberControllerTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc; //2

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(String email, String password){//3
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail(email);
        memberFormDto.setName("홍길동");
        memberFormDto.setAddress("서울시 마포구 합정동");
        memberFormDto.setPassword(password);
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        return memberService.saveMember(member);
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccessTest() throws Exception{
        String email = "test@email.com";
        String password = "1234";
        this.createMember(email, password);
        mockMvc.perform(formLogin().userParameter("email")
                        .loginProcessingUrl("/members/login")
                        .user(email).password(password))//4
                .andExpect(SecurityMockMvcResultMatchers.authenticated());//5
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    public void loginFailTest() throws Exception{
        String email = "test@email.com";
        String password = "1234";
        this.createMember(email, password);
        mockMvc.perform(formLogin().userParameter("email")
                        .loginProcessingUrl("/members/login")
                        .user(email).password("12345"))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());//6
    }

}
/*
1-> MockMvc 테스트를 위해 @AutoconfigureMockMvc 어노테이션을 선언합니다

 2->MockMvc 클래스를 이용해 실제 객체와 비슷하지만 테스트에 필요한 기능만 가지는 가짜 객체입니다.

 MockMvc 객체를 이용하면 웹 브라우저에서 요청을 하는 것처럼 테스트 할 수 있습니다.

 3 로그인 예제 진행을 위해서 로그인 전 회원을 등록하는 메소드를 만들어준다.

 4 회원 가입 메소드를 실행 후 가입된 회원 정보로 로그인이 되는지를 테스트를 진행합니다.

 userParameter()를 이요하여 이메일을 아이디로 세팅하고 로그인 URL에 요청합니다.

 5 로그인이 성공하여 인증되었다면 테스트 코드가 통과합니다.

6 회원 가입은 정상적으로 진행 하였지만 회원 가입 시 입력한 비밀번호 가 아닌 다른 비밀 번호로 로그인을 시도하여 인증되지 않은 결과 값이 출력되어

테스트가 통과합니다
 */
//참고서적: 백견이불여일타 스프링부트와 쇼핑몰 with JPA
