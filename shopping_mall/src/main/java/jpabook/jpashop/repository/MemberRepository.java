package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.http.HttpSession;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository {

    //알아서 엔티티 메니저를 자동 주입 시켜줌 <- (public MemberRepository(EntityManager em) {this.em = em;})
    //스프링 데이터 JAP로 인해 @Autowired 사용가능
    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class , id);
    }

    public List<Member> findAll() {
        //createQuery(JPQL , 반환 타입)
        return em.createQuery("select m from Member m", Member.class)//SQL과 다르게 객체를 대상으로 쿼리를 씀
                .getResultList();//가져온걸 List로 바꿔줌
    }


    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name) //여기로 들어온 파라미터 값이 위 :name으로 바인딩
                .getResultList();
    }

    // 01_01. 회원 로그인체크
    public Boolean loginCheck(Member member) {
        Optional<Member> user = null;
        try{
            user = Optional.ofNullable(em.createQuery("select m from Member m where m.user_id = :user_id and m.password = :password", Member.class)
                    .setParameter("user_id", member.getUser_id())
                    .setParameter("password", member.getPassword())
                    .getSingleResult());
        }catch (NoResultException e){
            System.out.println("###" + e);
            user = Optional.empty();
        }

            System.out.println(user.isEmpty());
            return user.isPresent();



    }

    // 01_02. 회원 로그인 정보
    public Member viewMember(Member member) {
        return em.createQuery("select m from Member m where m.user_id = :user_id and m.password = :password", Member.class)
                .setParameter("user_id", member.getUser_id())
                .setParameter("password", member.getPassword())
                .getSingleResult();
    }
    // 02. 회원 로그아웃
    public void logout(HttpSession session) {
    }
}
