package org.ike.springcloud.feignconsumer.controller;

import org.ike.springcloud.feignconsumer.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    FeignService feignService;

    @RequestMapping(value="/feign-consumer", method = RequestMethod.GET)
    public String helloConsumer() {
        return feignService.hello();
    }
}
