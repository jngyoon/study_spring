package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")    // 'localhost:8080/hello' hello 메소드 호출
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello"; //hello.html 찾아서 렌더링해라

    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http의 body부에 이 데이터를 직접 넣겠다는 선언
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //"hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); //객체생성
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        //getter, setter 생성 단축키 : alt+Insert
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}
