package jpabook.jpashop.controller;


import jakarta.servlet.http.HttpSession;
import jpabook.jpashop.domain.SearchDto;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private  final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "/items/createItems";
    }

    @PostMapping("/items/new")
    public String create(BookForm form, HttpSession session){



        Book book = new Book();
        //createBook같은걸 만들어서 setter를 다 없애는게 좋음
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setAuthor(form.getAuthor());
        book.setStockQuantity(form.getStockQuantity());
        book.setIsbn(form.getIsbn());
        book.setQuality(form.getQuality());
        book.setUni(form.getUni());

        itemService.saveItem(book);

        return "redirect:/";
    }

//    @GetMapping("/items")
//    public String list(Model model){
//        List<Item> items = itemService.findItems();
//        model.addAttribute("items", items);
//        return "items/itemList";
//    }
    /**
     * 특정 단어 검색 기능 (12/08)
     */

    @GetMapping("/items")
    public String list(Model model, @ModelAttribute("keyword") SearchDto keyword){
        List<Item> items;
        if(keyword.getTitle() == null && keyword.getUni() == null) {
            items = itemService.findItems();
        }else if(keyword.getTitle() != null && keyword.getUni() == null){
            items = itemService.findTitle(keyword.getTitle());
        }else if(keyword.getTitle() == null && keyword.getUni() != null) {
            System.out.println("동작 확인");
            items = itemService.findUni(keyword.getUni());
        }else {
            items = itemService.findTitleAndUni(keyword.getTitle(),keyword.getUni());
        }
        model.addAttribute("items", items);
        model.addAttribute("title", keyword.getTitle()); //왜인지 모르지만 <input name = ""/>과 매핑됨
        model.addAttribute("uni", keyword.getUni());

        return "items/itemList";
    }



    //상품 수정

    @GetMapping("items/{itemId}/edit")//변화가 가능한 패스{itemId} -> @PathVariable("itemId") 매핑해줌
    public String updateitemForm(@PathVariable("itemId") Long itemId, Model model){
        Book item = (Book)itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setAuthor(item.getAuthor());
        form.setStockQuantity(item.getStockQuantity());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    //@ModelAttribute는 사용자가 요청시 전달하는 값을 오브젝트 형태로 매핑해주는 어노테이션
    @PostMapping(value = "/items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") BookForm form){


        /**
         * 상품 수정, 권장 코드
         */
        itemService.updateItem(itemId, form.getName(), form.getPrice(),
                form.getStockQuantity());
        return "redirect:/items";


    }




}
