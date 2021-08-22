package hello.springmvc.basic.requestmapping.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("hellodate = {}", helloData);

        response.getWriter().write("ok");
    }
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws JsonProcessingException {

        log.info("messageBody = {}", messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("hellodate = {}", helloData);

        return "ok";
    }
    //@ModelAttribute로 할수 있다.!!!!.
    //httpConverter를 해준다!!!.
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {

        log.info("hellodate = {}", helloData);

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> helloData) {
        HelloData body = helloData.getBody();
        log.info("hellodate = {}", helloData);

        return "ok";
    }
    //반환이 HelloData가 json으로 바뀐다.
    //json으로 응답해준다!!!!!!!!!!!!!!!!!!!!!!!!!.
    //RequsetBody요청
    //JSON요청 ->HTTP 메시지 컨버터 -> 객체
    //ResponseBody응당
    //객체 -> HTTP 메시지 컨버터 -> JSON응답
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
        log.info("hellodate = {}", data);

        return data;
    }
    @ResponseBody
    @PostMapping("/request-body-json-v6")
    public HttpEntity<HelloData> requestBodyJsonV6(@RequestBody HelloData data) {
        log.info("hellodate = {}", data);
        HttpEntity<HelloData> http = new HttpEntity<>(data);
        log.info("hellodate = {}", http);
        return http;
    }


}
