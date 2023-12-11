package jpabook.jpashop.controller;


import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jpabook.jpashop.Dto.BookForm;
import jpabook.jpashop.Dto.SearchDto;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private  final ItemService itemService;
    private  final MemberService memberService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("bookForm", new BookForm());
        return "/items/createItems";
    }

    @PostMapping("/items/new")
    public String create(@Valid BookForm form, BindingResult bindingResult, Model model,
                         @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList,
                         HttpSession session){
        System.out.println(session.getAttribute("userId"));
        form.setMember(memberService.findOne(String.valueOf(session.getAttribute("userId"))));

        if(bindingResult.hasErrors()){
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error.getDefaultMessage());
            });
            return "items/createItems";
        }

        if(itemImgFileList.get(0).isEmpty() && form.getId() == null){
            System.out.println("에러1");
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "items/createItems";
        }

        try {
            itemService.saveItem(form, itemImgFileList);
        } catch (Exception e){
            System.out.println("에러2"+e);
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "items/createItems";
        }

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

    @GetMapping("/myItems/{memberId}")
    public String myList(Model model,@PathVariable("memberId") Long memberId) {
        List<Item> items;
        System.out.println("MemberId = "+memberId);
        items = itemService.findItemsByMemberId(memberId);

        model.addAttribute("items", items);

        return "items/myItemList";
    }



    //상품 수정

//    @GetMapping("items/{itemId}/edit")//변화가 가능한 패스{itemId} -> @PathVariable("itemId") 매핑해줌
//    public String updateitemForm(@PathVariable("itemId") Long itemId, Model model){
//        Item item = itemService.findOne(itemId);
//
//        BookForm form = new BookForm();
//        form.setName(item.getName());
//        form.setPrice(item.getPrice());
//        form.setAuthor(item.getAuthor());
//        form.setStockQuantity(item.getStockQuantity());
//        form.setIsbn(item.getIsbn());
//
//        model.addAttribute("form", form);
//        return "items/updateItemForm";
//    }

    @GetMapping(value = "/items/{itemId}")
    public String itemDtl(@PathVariable("itemId") Long itemId, Model model){

        try {
            BookForm bookForm = itemService.getItemDtl(itemId);
            model.addAttribute("bookForm", bookForm);
        } catch(EntityNotFoundException e){
            model.addAttribute("errorMessage", "존재하지 않는 상품 입니다.");
            model.addAttribute("bookForm", new BookForm());
            // session.setAttribute("userId",session.getId());
            return "items/createItems";
        }

        return "items/createItems";
    }

    //@ModelAttribute는 사용자가 요청시 전달하는 값을 오브젝트 형태로 매핑해주는 어노테이션
    @PostMapping(value = "/items/{itemId}")
    public String itemUpdate(@Valid BookForm bookForm, BindingResult bindingResult,
                             @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Model model, @PathVariable("itemId") Long itemId){
//
//        BookForm bookForm = itemService.getItemDtl(itemId);

        if(bindingResult.hasErrors()){
            System.out.println("바인딩 결과 에러");
            return "items/createItems";
        }

        if(itemImgFileList.get(0).isEmpty() && bookForm.getId() == null){
            System.out.println("이미지 안들어옴  /"+itemImgFileList.get(0).isEmpty()+" / "+bookForm.getId());
            model.addAttribute("errorMessage", "'첫번째 상품 이미지는 필수 입력 값 입니다.'");
            return "items/createItems";
        }

        try {
            itemService.updateItem(bookForm, itemImgFileList);
        } catch (Exception e){
            System.out.println("결과 에러"+e+" / "+bookForm+" / "+itemImgFileList.size());
            model.addAttribute("errorMessage", "'상품 수정 중 에러가 발생하였습니다.'");
            return "items/createItems";
        }

        return "redirect:/";
    }





}
