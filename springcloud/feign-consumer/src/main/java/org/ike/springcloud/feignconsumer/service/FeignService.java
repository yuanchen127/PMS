package org.ike.springcloud.feignconsumer.service;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("hello-service")
public interface FeignService {

    @RequestMapping("/hello")
    String hello();
}
