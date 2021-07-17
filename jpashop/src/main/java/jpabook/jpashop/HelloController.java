package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping
    public String Hello(Model model) {
        model.addAttribute("data", "hello!!");//key : data value : hello!!
        return "hello"; //templates로 간다!!!
    //resources:templates/ +{ViewName}+ .html
    }
}
