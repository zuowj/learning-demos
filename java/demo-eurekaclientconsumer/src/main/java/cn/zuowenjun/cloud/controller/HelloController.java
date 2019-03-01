package cn.zuowenjun.cloud.controller;

import cn.zuowenjun.cloud.service.HelloRemoteService;
import cn.zuowenjun.cloud.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private  HelloService helloService;

    @Autowired
    private HelloRemoteService helloRemoteService;

    @RequestMapping("/x")
    public Object multiplyForRestTemplate(@RequestParam int a, @RequestParam int b) {
       return   helloService.multiply(a,b);
    }

    @RequestMapping("/multiply/{a}/{b}")
    public Object multiplyForFeignClient(@PathVariable int a, @PathVariable int b) {
        return helloRemoteService.multiply(a,b);
    }
}
