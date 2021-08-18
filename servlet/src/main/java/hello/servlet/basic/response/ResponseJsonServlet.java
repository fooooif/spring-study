package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//요청 데이터
//parameter =>get 방식만.
//html form =>post 방식만.
//message body => json,text,xml (post put patch).

//응답 데이터.
//텍스트.
//html
//json
@WebServlet(name = "responseJsonServlet",urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");

        HelloData helloData = new HelloData();
        helloData.setAge(1);
        helloData.setUsername("kim");
        String s = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(s);

    }
}
