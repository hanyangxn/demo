package com.example.demo2.test;

import com.alibaba.fastjson.JSONObject;
import com.example.demo2.testReflectionUtil.bo.RequestBo;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import parsii.eval.Expression;
import parsii.eval.Parser;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.demo2.testReflectionUtil.enums.DateType.THREE_YEAR;
import static com.example.demo2.testReflectionUtil.enums.ServiceType.SERVICE1;

public class Test3 {
    public static void main(String[] args) throws URISyntaxException{
        postForm();
    }

    public static void postForm() throws URISyntaxException {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(stringHttpMessageConverter);
        messageConverters.add(new FormHttpMessageConverter());
        RestTemplate restTemplate = new RestTemplate(messageConverters);
        String url = "http://10.13.136.129:8082/getWind";
        URI uri = new URI(url);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type",MediaType.APPLICATION_JSON_VALUE);
//        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
//        map.add("wind", "184690");
        Map<String, String> map= new HashMap<>();
        map.put("wind", "184690");
        HttpEntity<String> httpEntity = new HttpEntity<>(JSONObject.toJSONString(map), headers);
        String ss = restTemplate.postForObject( uri, httpEntity , String.class );
        System.out.println(ss);
    }

    public void pstJava() throws URISyntaxException {
        //        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//        messageConverters.add(stringHttpMessageConverter);
//        messageConverters.add(new FormHttpMessageConverter());
//        RestTemplate restTemplate = new RestTemplate(messageConverters);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        RequestBo requestBo = new RequestBo(SERVICE1, THREE_YEAR);
        String b = JSONObject.toJSONString(requestBo);
        HttpEntity<String> httpEntity = new HttpEntity<>(b, httpHeaders);
        URI uri = new URI("http://10.13.136.129:8082/reflectionTest");
        String s = restTemplate.postForObject(uri, httpEntity, String.class);
        System.out.println(s);


    }
}
