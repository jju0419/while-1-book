package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j // 로그와 관련된 코드들을 지원하는 LOMBOK 어노테이션
public class HomeController {

    @RequestMapping("/")
    public String home(){
        log.info("home comtroller");
        return "index";
    }
}
