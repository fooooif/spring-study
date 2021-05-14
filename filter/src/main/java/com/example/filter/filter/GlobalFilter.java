package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

@Slf4j
@WebFilter(urlPatterns = "/api/user/*") // 특정 url(여러가지 가능)에만 필터 넣어주려면 + 돌아가는데에 @ServletComponentScan 적용
//아니면 @Component => 전체에
public class GlobalFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //전처리
        //밑에 두줄은 Content 길이만 생성해주고 밑에 doFilter에서 byteArray에 내용이 들어간다.
        ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response);

        //doFilter이후에 찍어야된다.


        chain.doFilter(httpServletRequest,httpServletResponse);
        String url = httpServletRequest.getRequestURI();
        //후처리
        //req

        String reqContent = new String(httpServletRequest.getContentAsByteArray());
        log.info("request status : {}, requestBody : {}",url,reqContent);


        //여기서 내용을 다빼버리기 떄문에 밑에 copyBodyToResponse() 사용!
        String resContent = new String(httpServletResponse.getContentAsByteArray());
        int httpStatus = httpServletResponse.getStatus();


        httpServletResponse.copyBodyToResponse();

        log.info("response status : {}, responseBody : {}",httpStatus, resContent);
    }
}
