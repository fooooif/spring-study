package hello.springmvc.basic.requestmapping.response;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {


    @RequestMapping("/response-view-v1")
    public ModelAndView responseView1() {
        ModelAndView modelAndView = new ModelAndView("response/hello")
                .addObject("data", "hello");
        return modelAndView;
    }

    /**
     * 자주씀!!!!!!! 이거임
     * @param model
     * @return
     */
    //Controller에서 String을 반환하면 논리적 view 네임 => 뷰 리졸
    @RequestMapping("/response-view-v2")
    public String responseView2(Model model) {

        model.addAttribute("data", "hello");
        return "response/hello";
    }
    //ResponseBody를 붙히면 view로 가지 않고 body에 Accept에 맞게 바뀐다.
    @ResponseBody
    @RequestMapping("/response-view-v3")
    public String responseView3(Model model) {

        model.addAttribute("data", "hello");
        return "response/hello";
    }
    @RequestMapping("/response/hello")
    public void responseView4(Model model) {

        model.addAttribute("data", "hello");
    }

}
