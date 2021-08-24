package hello.springmvc.basic.requestmapping.response;


import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpLogging;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
//@Controller
//@ResponseBody
//@Controller+ @ResponseBody => @RestController
@RestController
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() throws IOException {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    //    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    @GetMapping("response-body-json-v1")
    public ResponseEntity<HelloData> responseJsonV1() {

        HelloData helloData = new HelloData();

        ResponseEntity<HelloData> responseEntity = new ResponseEntity<HelloData>(helloData, HttpStatus.OK);
        return responseEntity;
    }

    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
    @GetMapping("response-body-json-v2")
    public HelloData responseJsonV2() {

        HelloData helloData = new HelloData();

        return helloData;
    }

    @ResponseBody
    @GetMapping("/aaaaa")
    public ResponseEntity<List<HelloData>> aa() {
        List<HelloData> helloDataList = new ArrayList<>();

        helloDataList.add(new HelloData());
        helloDataList.add(new HelloData());

        ResponseEntity<List<HelloData>> e = new ResponseEntity<List<HelloData>>(helloDataList, HttpStatus.OK);

        return e;
    }
    @ResponseBody
    @GetMapping("/aaaaaa")
    public List<HelloData> aaa() {
        List<HelloData> helloDataList = new ArrayList<>();

        helloDataList.add(new HelloData());
        helloDataList.add(new HelloData());


        return helloDataList;
    }
}
