package hello.springmvc.basic.requestmapping.request;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@RestController
@Slf4j
public class RequestHeaderController {

    @GetMapping("/header")
    public String header(HttpServletRequest request,
                         HttpServletResponse response,
                         HttpMethod httpMethod,
                         Locale locale,
                         @RequestHeader MultiValueMap<String, String> headerMap,
                         @RequestHeader("host") String host,
                         @CookieValue(value= "myCookie", required = false) String cookie
                         )
    {
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);
        return "ok";


    }
}
