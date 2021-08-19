package hello.servlet.web.frontcontroller.v1.controller;


import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MemberFormControllerv1 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        //dispatcher.forward 다른 서블릿이나 jsp로 이동할 수 있는 기능이다. 서버 내부에서 다시 호출 가능..!!!
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
        requestDispatcher.forward(request,response);

    }
}
