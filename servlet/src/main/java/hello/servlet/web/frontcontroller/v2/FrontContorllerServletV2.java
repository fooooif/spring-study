package hello.servlet.web.frontcontroller.v2;


import hello.servlet.web.frontcontroller.Myview;
import hello.servlet.web.frontcontroller.v1.ControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerv1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import org.apache.catalina.connector.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontContorllerServletV2", urlPatterns = "/front-controller/v1/*")
public class FrontContorllerServletV2 extends HttpServlet {
    private Map<String, ControllerV2> controllerV2Map = new HashMap();

    public FrontContorllerServletV2() {
        controllerV2Map.put("/front-controller/v1/members/new-form", new MemberFormControllerV2());
        controllerV2Map.put("/front-controller/v1/members/save", new MemberSaveControllerV2());
        controllerV2Map.put("/front-controller/v1/members", new MemberListControllerV2());

    }

    @Override

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControllerV2 controller = controllerV2Map.get(request.getRequestURI());

        if (controller == null) {
            response.setStatus(Response.SC_NOT_FOUND);
            return;
        }
        Myview myview = controller.process(request, response);
        myview.render(request, response);
    }
}
