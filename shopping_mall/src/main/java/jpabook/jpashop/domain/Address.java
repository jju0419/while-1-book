package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable //내장 타입임을 알림
@Getter
public class Address {

    private String postnum;

    private String street;

    private String jibun;

    private String detail;

    private String zipcode;

    protected Address() { //jpa에선 기본생성자가 있어야 함(프록시 사용 유무 때문) 실제 사용할땐 밑 생성자
    }

    public Address(String postnum,String jibun, String detail, String street, String zipcode) { //값이 변하면 안됨으로 생성자로 직접 주입시켜줌
        this.postnum = postnum;
        this.street = street;
        this.zipcode = zipcode;
        this.jibun = jibun;
        this.detail = detail;
    }



}
