package cn.zuowenjun.microservice.controller;

import cn.zuowenjun.microservice.Service.DemoService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DemoController implements DemoService {


    @Override
    public String getMessage(@PathVariable String name) {
        System.out.println("request getMessage api ,name:" + name);
        try {
            Thread.sleep(10000);//模拟耗时，以便可以触发重试
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return String.format("你好，【%s】，欢迎跟梦在旅途一起学习玩转Spring Cloud！",name);
    }

    @Override
    public Map<String, Object> getNumbers(@PathVariable int min,@PathVariable int max) {
        Map<String, Object> numbers=new HashMap<>();
        for (int i=min;i<=max;i++){
            numbers.put("number-"+String.valueOf(i),i);
        }
        return numbers;
    }
}
