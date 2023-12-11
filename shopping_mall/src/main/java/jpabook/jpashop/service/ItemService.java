package jpabook.jpashop.service;

import jakarta.persistence.EntityNotFoundException;
import jpabook.jpashop.Dto.BookForm;
import jpabook.jpashop.Dto.ItemImgForm;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.ItemImg;
import jpabook.jpashop.repository.ItemImgRepository;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemImgService itemImgService;

    private final ItemImgRepository itemImgRepository;


    @Transactional
    public Long  saveItem(BookForm form , List<MultipartFile> itemImgFileList) throws Exception {
        Item item = form.createItem();
        itemRepository.save(item);

        for(int i=0;i<itemImgFileList.size();i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if(i == 0)
                itemImg.setRepimgYn("Y");
            else
                itemImg.setRepimgYn("N");

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }
        return item.getId();
    }


    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }




    /**
     * 특정 단어 검색 기능 (12/08)
     */
    public List<Item> findTitle(String keyword) {
        return itemRepository.findTitle(keyword);
    }


    //둘 다 검색 될때를 가정
    public List<Item> findTitleAndUni(String keyword, String uni) {
        return itemRepository.findTitleAndUni(keyword , uni);
    }

    public List<Item> findUni(String uni) {
        return itemRepository.findUni(uni);
    }
//    public Page<Item> itemSearchNameList(String searchKeyword,  Pageable pageable){
//        return boardRepository.findBynameContaining(searchKeyword,  pageable);
//    }

//    public Page<Item> itemSearchUniList(String searchKeyword,  Pageable pageable){
//        return boardRepository.findByuniContaining(searchKeyword,  pageable);
//    }


    /**
     * 영속성 컨텍스트가 자동 변경 Dto만들어서 setter몰아두는게 더 권장됨
     */
//    @Transactional
//    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
//        Item item = itemRepository.findOne(itemId);
//        item.setName(name);
//        item.setPrice(price);
//        item.setStockQuantity(stockQuantity);
//    }

    @Transactional(readOnly = true)
    public BookForm getItemDtl(Long itemId){
        //해당 상품의 이미지를 조회한다. 등록 순으로 가지고 오기 위해 상품 이미지 아이디 오름차순으로 가지고 온다
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgForm> itemImgDtoList = new ArrayList<>();
        //조회한 ItemImg 엔티티를 ItemImgDto 객체로 만들어서 리스트에 추가한다.
        for (ItemImg itemImg : itemImgList) {
            ItemImgForm itemImgForm = ItemImgForm.of(itemImg);
            itemImgDtoList.add(itemImgForm);
        }
        //상품의 아이디를 통해 상품 엔티티를 조회한다. 존재하지 않을 때는 EntityNotFoundException을 발생시킨다.
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        BookForm bookform = BookForm.of(item);
        bookform.setItemImgDtoList(itemImgDtoList);
        return bookform;
    }
    @Transactional
    public Long updateItem(BookForm bookForm, List<MultipartFile> itemImgFileList) throws Exception{
        //상품 수정
        //상품 등록 화면으로부터 전달 받은 상품 아이디를 이용하여 상품 엔티티를 조회한다.
        Item item = itemRepository.findById(bookForm.getId())
                .orElseThrow(EntityNotFoundException::new);
       // 상품 등록 화면으로부터 전달 받은 ItemFormDto를 통해 상품 엔티티를 업데이트한다.
        item.updateItem(bookForm);


        System.out.println("여기다!!!!"+itemImgFileList.size());
        //상품 이미지를 업데이트하기 위해서 updateItemImg() 메소드에 상품 이미지 아이디와 상품 이미지 파일 정보를 파라미터로 전달한다.
        List<Long> itemImgIds = bookForm.getItemImgIds();

//        List<ItemImgForm> itemImgDtoList = bookForm.getItemImgDtoList();
//        for(int i=0;i<itemImgFileList.size();i++){
//            Long id = itemImgDtoList.get(i).getId();
//            itemImgService.updateItemImg(id,
//                    itemImgFileList.get(i));
//        }


        //이미지 등록
        for(int i=0;i<itemImgFileList.size();i++){
            itemImgService.updateItemImg(itemImgIds.get(i),
                    itemImgFileList.get(i));
        }

        return item.getId();
    }

    public List<Item> findItemsByMemberId(Long memberId) {
        return itemRepository.findItemsByMemberId(memberId);
    }
}
