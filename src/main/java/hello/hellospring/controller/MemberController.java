package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //스프링 컨테이너에서 스프링 빈이 관리 된다. ==> 스프링이 시작 될 때 Controller 어노테이션이 있는 아이들을 객체화 함.
public class MemberController {
    private final MemberService memberService;

    //command + n => Constructor :: 생성자 생성
    @Autowired // => 스프링이 컨트롤러와 서비스를 연결
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
