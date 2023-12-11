package jpabook.jpashop.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jpabook.jpashop.Dto.MemberForm;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/creatememberForm";
    }

    @PostMapping("/members/new")
    //@Valid 여러가지 확인절차를 스프링에서 해줌 null확인 등 BindingResult result :오류가 발생할경우 result에 담겨서 함수 실행
    public String create(@Valid MemberForm form, BindingResult result){

        //result에 해당에러가 있을시 이 코드를 탊
        if(result.hasErrors()){
            return "members/creatememberForm";
        }

        Address address = new Address(form.getPostnum(), form.getStreet(), form.getZipcode() , form.getJibun(),form.getDetail());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());
        member.setUser_id(form.getUser_id());
        member.setRoles("USER");

        memberService.join(member);

        return "redirect:/";
    }


    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }


    // 01. 로그인 화면
    @GetMapping("/members/login")
    public String login(Model model){
        model.addAttribute("userForm", new Member());
        return "members/logInMember";    // views/member/login.jsp로 포워드
    }

    // 02. 로그인 처리
    @PostMapping("/members/login")
    public String loginCheck(Model model,Member userForm, HttpSession session){
        boolean result = memberService.loginCheck(userForm, session);
        if (result) { // 로그인 성공
            Member member = memberService.viewMember(userForm);
            session.setAttribute("id", member.getId());
            session.setAttribute("userId", member.getUser_id());
            session.setAttribute("userName", member.getName());
            // main.jsp로 이동
            model.addAttribute("msg", "success");
            return "redirect:/";
        } else {    // 로그인 실패
            model.addAttribute("msg", "failure");
            model.addAttribute("userForm", new Member());
            // login.jsp로 이동
            return "members/logInMember";
        }
    }

    // 03. 로그아웃 처리
    @GetMapping ("/members/logout")
    public String logout(HttpSession session){
        memberService.logout(session);
        return "redirect:/";
    }
}
