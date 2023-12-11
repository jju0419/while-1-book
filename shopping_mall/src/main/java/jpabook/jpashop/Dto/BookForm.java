package jpabook.jpashop.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BookForm {

    private Long id;

    @NotBlank(message = "상품 명은 필수 입니다")
    private String name;

    @NotNull(message = "가격은 필수 입니다")
    private int price;

    @NotBlank(message = "상태는 필수 입니다")
    private String quality;

    @NotBlank(message = "상품 상세는 필수 입니다")
    private String itemDetail;

    private String registdate;
    private String uni;
    private String department;

    private Member member;


    private int stockQuantity;

    private String author;
    private String isbn; //책 일련 번호 이지만 출판사로 사용

//    private ItemSellStatus itemSellStatus;

    private List<ItemImgForm> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem(){
        return modelMapper.map(this, Item.class);
    }

    public static BookForm of(Item item){
        return modelMapper.map(item,BookForm.class);
    }

}
