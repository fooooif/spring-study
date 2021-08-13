package hello.servlet.basic.request;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/*
    1.파라미터 전송기능
 */
@WebServlet(name = "RequestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] - start");
        request.getParameterNames().asIterator()
                .forEachRemaining(paraName -> System.out.println(paraName + request.getParameter(paraName)));
        System.out.println("[전체 파라미터 조회] - end");

        System.out.println("[단일 파라미터 조회] - start");
        String name = request.getParameter("username");
        String age = request.getParameter("age");

        System.out.println("age = " + age);
        System.out.println("name = " + name);

        System.out.println("[단일 파라미터 조회] - end");

        System.out.println("[이름이 같은 복 파라미터 조회] - start");
        String[] names = request.getParameterValues("username");
        for (String s : names) {
            System.out.println("s = " + s);
        }
        System.out.println("[이름이 같은 복 파라미터 조회] - end");

        response.getWriter().write("ok");

    }
}
