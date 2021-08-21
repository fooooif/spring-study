package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller 는 리턴에 view 네임이 반환된다.
@RestController // 리턴에 string이 그대로 반환된다.
@Slf4j
public class LogTestController {
//    private final Logger log = LoggerFactory.getLogger(getClass());
//@Slf4j쓰면 알아서 넣어줌.
    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name);

        log.trace("trace log = {}", name);
        //trace로그를 쓰지 않는데 먼저 더해준다 따라서 쓸모없는 연산을 수행 이거 사용하지 않는다
//       log.trace("trace log = "+ name);
        log.debug("debug log = {}", name);
        log.info(" info log= {}", name);
        log.warn(" warn log= {}", name);
        log.error("error log= {}", name);

        return "ok";

    }
}
