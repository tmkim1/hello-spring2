package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) throws SQLException {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    //기본키로 조회하지 않는 경우 jpql(객체를 기준으로 하여 조회)을 사용 --> 이 또한 대체가 가능
    @Override
    public Optional<Member> findByName(String name) {
         List<Member> members = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name" , name)
                .getResultList();

         return members.stream().findAny();
    }

    @Override
    public List<Member> findAll() {

        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }


}
