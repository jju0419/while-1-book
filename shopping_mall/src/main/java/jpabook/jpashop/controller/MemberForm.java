package jpabook.jpashop.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다")//값이 비어있으면 오류
    private  String name;

    @NotEmpty(message = "이메일은 필수 입니다")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입니다")
    private String user_id;

    @NotEmpty(message = "비밀번호는 필수 입니다")//값이 비어있으면 오류
    private String password;

    private String number;



    private String postnum;

    private String street;

    private String jibun;

    private String detail;

    private String zipcode;

}
