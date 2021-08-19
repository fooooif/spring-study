package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handle) throws IOException, ServletException {
        ControllerV3 controller = (ControllerV3) handle;

        Map<String, String> paraMap = getParaMap(request);
        ModelView mv = controller.process(paraMap);

        return mv;
    }
    private Map<String, String> getParaMap(HttpServletRequest request) {
        Map<String, String> paraMap = new HashMap<>();

        request.getParameterNames().asIterator().forEachRemaining(paraName -> {
            paraMap.put(paraName, request.getParameter(paraName));});
        return paraMap;
    }


}
