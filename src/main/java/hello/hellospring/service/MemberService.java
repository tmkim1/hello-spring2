package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Test Tip:: Command + Shift + T

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    //Repository를 외부에서 넣을수있도록 메서드를 사용 ==> DI: 의존성 주입 Dependency Inpendent
    //Autowired를 통해 인자에 사용되는 MemberRepository 객체를 스프링이 대입해줌 (DI)
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
    - 회원 가입
     */
    public long join(Member member) {
        // 같은 이름 중복 회원 X
        // tip: return 타입 자동 생성: option + command + v
//        Optional<Member> result = memberRepository.findByName(member.getName());
//
//        result.ifPresent(m ->{
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });   

        validateDuplicateMember(member); // 중복 회원 검증.

        memberRepository.save(member);
        return member.getId();
    }
    // option + command + m ==> 메서드로 변경해 줌.
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m ->{
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */

    public List<Member> findMembers () {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long MemberId) {
        return memberRepository.findById(MemberId);
    }
}
