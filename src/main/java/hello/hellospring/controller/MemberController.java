package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    //memberService를 가져다 써야함
    //아래처럼 new로 가져다 쓸 수 있지만
    //private final MemberService memberService = new MemberService();
    //Spring이 관리를 하게 되면 다 Spring 컨테이너에 등록을 하고 Spring 컨테이너로 받아서 쓰도록 해야함
    //new로 하면 memberController 말고 다른 여러 컨트롤러들이 멤버 서비스를 가져다 쓸 수 있게됨
    //멤버 서비스는 여러개 생성할 필요가 없고 하나만 생성해서 공통으로 쓰면됨
    // -> 스프링 컨테이너에 등록하는 방법이 효율적(@Autowired) (컴포넌트 스캔 방식)

    private final MemberService memberService;

    @Autowired  //스프링 컨테이너에 있는 멤버 서비스를 가져다가 연결시켜줌
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
