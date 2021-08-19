package hello.servlet.web.frontcontroller.v3;


import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.Myview;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import org.apache.catalina.connector.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontContorllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontContorllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerV3Map = new HashMap();

    public FrontContorllerServletV3() {
        controllerV3Map.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerV3Map.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerV3Map.put("/front-controller/v3/members", new MemberListControllerV3());

    }

    @Override

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControllerV3 controller = controllerV3Map.get(request.getRequestURI());

        if (controller == null) {
            response.setStatus(Response.SC_NOT_FOUND);
            return;
        }
        Map<String, String> paraMap = getParaMap(request);

        ModelView mv = controller.process(paraMap);

        String viewName = mv.getViewName();//논리 이름!!

        Myview myview = viewResolver(viewName);
        myview.render(mv.getModel(),request, response);


    }

    private Map<String, String> getParaMap(HttpServletRequest request) {
        Map<String, String> paraMap = new HashMap<>();

        request.getParameterNames().asIterator().forEachRemaining(paraName -> {
            paraMap.put(paraName, request.getParameter(paraName));});
        return paraMap;
    }

    private Myview viewResolver(String viewName) {
        return new Myview("/WEB-INF/views/" + viewName + ".jsp");
    }
}
