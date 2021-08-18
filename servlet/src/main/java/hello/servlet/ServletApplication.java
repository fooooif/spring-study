package hello.servlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan // 서블릿 자동등록
@SpringBootApplication
//요청 데이터
//parameter =>get 방식만.
//html form =>post 방식만.
//message body => json,text,xml (post put patch).

//응답 데이터.
//텍스트.
//html
//json
public class ServletApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServletApplication.class, args);
    }


}
