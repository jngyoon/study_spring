package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();    //테스트마다 store를 비워줌
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");   //member에 name을 spring으로 지정

        repository.save(member);    //repository에 member 저장

        Member result =  repository.findById(member.getId()).get(); //findById로 id 가져와서 검증
        //테스트 방법1(Assertions : junit)
        //Assertions.assertEquals(member, result);

        //테스트 방법2(Assertions : assertj)
        assertThat(member).isEqualTo(result); //member가 result랑 똑같은 경우
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();  //위에꺼 복붙 후 shift+F6 누르면 rename 가능
        member2.setName("spring2");
        repository.save(member2);

        Member result =  repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
