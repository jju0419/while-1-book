package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {

    private Long id;

    private String name;
    private String quality;
    private String registdate;
    private String uni;
    private String department;

    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;

}
