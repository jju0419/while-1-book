package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository  {

    private final EntityManager em;

    public void save(Item item) {
        if(item.getId() == null){
            em.persist(item); //DB에 등록된것이 없기때문에 영속성으로 만듦
        }else{
            em.merge(item); //DB에 존재함으로 업데이트
        }
    }


    public Item findOne(Long id) {
        return em.find(Item.class,id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
    public List<Item> findTitle(String keyword) {
        return em.createQuery("select i from Item i where i.name like concat('%', :keyword, '%')", Item.class)
                .setParameter("keyword", keyword)
                .getResultList();
    }

    public List<Item> findTitleAndUni(String keyword, String uni) {
        return em.createQuery("select i from Item i where i.name like concat('%', :keyword, '%') and i.uni like concat('%', :uni, '%')", Item.class)
                .setParameter("keyword", keyword)
                .setParameter("uni", uni)
                .getResultList();
    }

    public List<Item> findUni(String uni) {
        return em.createQuery("select i from Item i where i.uni like concat('%', :uni, '%')", Item.class)
                .setParameter("uni", uni)
                .getResultList();
    }



}
