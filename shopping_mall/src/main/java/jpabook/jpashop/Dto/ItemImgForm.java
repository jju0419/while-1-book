package jpabook.jpashop.Dto;

import jpabook.jpashop.domain.item.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ItemImgForm {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgForm of(ItemImg itemImg) {
        return modelMapper.map(itemImg,ItemImgForm.class);
    }

}