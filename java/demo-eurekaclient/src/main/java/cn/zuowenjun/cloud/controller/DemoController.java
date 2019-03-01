package cn.zuowenjun.cloud.controller;

import cn.zuowenjun.cloud.model.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${spring.application.ip}")
    private String address;

    @Value("${server.port}")
    private String port;

    @Autowired
    DiscoveryClient discoveryClient;


    @GetMapping(value = "/")
    public String index(){
        return "demo service";
    }

    @RequestMapping("/hello")
    public  Object hello(){
        return discoveryClient.getServices();
    }


    @RequestMapping("/info")
    public Result info(){
        Result result = new Result();
        result.setServiceName(serviceName);
        result.setHost(String.format("%s:%s", address, port));
        result.setMessage("hello");
        return result;
    }

    @RequestMapping(value = "/multiply/{a}/{b}")
    public Result multiply(@PathVariable("a") int a,@PathVariable("b") int b){
        Result result = new Result();
        result.setServiceName(serviceName);
        result.setHost(String.format("%s:%s", address, port));
        result.setMessage("ok");
        result.setContent(a * b);
        return result;
    }
}
