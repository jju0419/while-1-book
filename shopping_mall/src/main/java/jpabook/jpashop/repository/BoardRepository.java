package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository                                         //어떤엔티티를 넣을것이냐, 엔티티의 id의 타입을
public interface BoardRepository extends JpaRepository<Item, Long> {
    /*검색기능-1*/
    Page<Item> findBynameContaining(String searchKeyword,  Pageable pageable);
    //Page<Item> findByuniContaining(String searchKeyword,  Pageable pageable);

}