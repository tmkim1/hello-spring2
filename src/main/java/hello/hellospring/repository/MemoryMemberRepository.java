package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;


//implements MemberRepository 작성하고 option + Enter 키 누르면 구현부 생성 시킬 수 있음 imple ~
public class MemoryMemberRepository implements MemberRepository{

    //실무에서는 공유되는 변수에 대해서는 원래 컨클루드 해시맵을 사용해야 함. // 지금은 예시기 때문에 임시 사용.
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // Optional: Java8 기능, 널 값이 들어올 수 있는 영역에 대해 감싸준다. 이래야 클라이언트 쪽에서 오류가 안 난 상태에서 처리가 가능
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 람다식: store 맵에 있는 값을 반복해서 돌리는데 member의 네임이 들어온 네임과 같다면 반환.
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
