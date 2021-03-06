package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.List;

@Controller //스프링 컨테이너에서 스프링 빈이 관리 된다. ==> 스프링이 시작 될 때 Controller 어노테이션이 있는 아이들을 객체화 함.
public class MemberController {

    //매번 New로 객체를 생성하는게 아닌 스프링 컨테이너에 객체를 등록하여 공유하며 사용.
    private final MemberService memberService;

    //command + n => Constructor :: 생성자 생성
    @Autowired // => 스프링이 컨트롤러와 서비스를 연결
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }


    //PostMapping은 data를 form에 넣어서 보냈을 때 해당 값들을 받기 위함.
    @PostMapping("members/new")
    public String create(MemberForm form) throws SQLException {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        System.out.println("name:" + member.getName());

        return "redirect:/"; // 회원 등록 완료 => 홈 화면 전환
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", "members");
        System.out.println(members);
        return "members/memberList";
    }
}
