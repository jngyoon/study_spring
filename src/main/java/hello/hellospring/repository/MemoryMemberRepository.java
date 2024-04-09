package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;  //sequence : 0,1,2 ... 이런식으로 키 값을 생성해주는 것
    @Override
    public Member save(Member member) {
        member.setId(++sequence); //sequence 값 올려주기
        store.put(member.getId(), member); //store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //store에서 꺼내기, null 반환을 방지하기 위해 Optional.ofNullable로 감싸줌
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    //member.name이 파라미터로 넘어온 name이랑 같은지 filter로 확인
                .findAny();     //하나라도 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store의 member들 반환
    }

    public void clearStore(){
        store.clear();      //store를 비워줌
    }
}
