package hello.servlet.web.frontcontroller.v4;


import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.Myview;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import org.apache.catalina.connector.Response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontContorllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontContorllerServletV4 extends HttpServlet {
    private Map<String, ControllerV4> controllerV4Map = new HashMap();

    public FrontContorllerServletV4() {
        controllerV4Map.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerV4Map.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerV4Map.put("/front-controller/v4/members", new MemberListControllerV4());

    }

    @Override

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ControllerV4 controller = controllerV4Map.get(request.getRequestURI());

        if (controller == null) {
            response.setStatus(Response.SC_NOT_FOUND);
            return;
        }
        Map<String, String> paraMap = getParaMap(request);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paraMap, model);
        
        Myview myview = viewResolver(viewName);
        myview.render(model,request, response);


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
