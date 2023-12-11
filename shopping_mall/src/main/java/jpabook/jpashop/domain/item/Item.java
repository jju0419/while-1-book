package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.Dto.BookForm;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
//Inheritance : 상속관계 매핑 ->객체의 상속 구조와 DB의 슈퍼타입 서브타입 관계를 매핑
@Getter @Setter
public class Item {//영화 책 앨범으로 나누어야 하기때문에 추상 클래스로 지정

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private String quality;

    private String itemDetail;

    private String registdate;
    private String uni;


    private int stockQuantity;

    private String author;
    private String isbn;






    @ManyToOne(fetch = LAZY) //다대일 관계 : fetch = LAZY 를 통해 지연 로딩이 필수이다
    @JoinColumn(name = "member_id") //해당 외례 키로 조인 함
    private Member member;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL) //일대다 관계 (Order 안에 있는 FK : member에 의해 매핑되어 있음을 알림)
    private List<OrderItem> OrderItems = new ArrayList<>();



    //==비지니스 로직==//
    //stockQuantity를 가지고있는 이곳에서 핵심 비지니스 로직을 사용하는것이 객체지향적으로 좋다
    //stockQuantity를 직접 컨트롤 해야 함으로
    /**
     * Stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * Stock 감소
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("need more stock"); //Exception을 직접 작성
        }
        this.stockQuantity =restStock;
    }

    public void updateItem(BookForm bookForm){
        this.name = bookForm.getName();
        this.price = bookForm.getPrice();
        this.stockQuantity = bookForm.getStockQuantity();
        this.itemDetail = bookForm.getItemDetail();
        this.quality = bookForm.getQuality();
        this.registdate = bookForm.getRegistdate();
        this.uni = bookForm.getUni();
        this.author = bookForm.getAuthor();
        this.isbn = bookForm.getIsbn();
    }

}
