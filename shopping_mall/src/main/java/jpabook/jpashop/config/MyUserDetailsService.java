//package jpabook.jpashop.config;
//
//import jpabook.jpashop.domain.Member;
//import jpabook.jpashop.service.MemberService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
//@Component
//@RequiredArgsConstructor
//public class MyUserDetailsService implements UserDetailsService {
//
//    private final MemberService memberService;
//
//    @Override
//    public UserDetails loadUserByUsername(String insertedUserId) throws UsernameNotFoundException {
//        Optional<Member> findOne = memberService.findOne(insertedUserId);
//        Member member = findOne.orElseThrow(() -> new UsernameNotFoundException("없는 회원입니다 ㅠ"));
//
//        return User.builder()
//                .username(member.getUser_id())
//                .password(member.getPassword())
//                .roles(member.getRoles())
//                .build();
//    }
//}