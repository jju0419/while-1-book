package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.BoardRepository;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
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
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item item = itemRepository.findOne(itemId);
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);
    }
}
