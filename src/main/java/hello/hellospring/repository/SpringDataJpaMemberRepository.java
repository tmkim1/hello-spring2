package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//Spring Data가 JpaRepository를 상속 받은 인터페이스를 알아서 스프링 빈으로 등 시킴.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //메서드 규칙에 따라 jpql로 변환 -> select m from Member m where m.name = ?
    //따라서 아래와 같은 단순 쿼리는 SpringDataJpa를 사용하면 인터페이스로 처리가 가능하다.
    @Override
    Optional<Member> findByName(String name);
}
