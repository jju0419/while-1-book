package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "mamber_id")
    private Long id;

    private String name;
    private String user_id;
    private String password;
    private String email;
    private String number;

    @Embedded //내장 타입 받는 경우 사용
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();


    @OneToMany(mappedBy = "member")
    private List<Item> items = new ArrayList<>();

}
