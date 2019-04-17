package cn.zuowenjun.microservice.Service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

public interface DemoService {

    @RequestMapping(value = "/demo/message/{name}")
    String getMessage(@PathVariable("name") String name);

    @RequestMapping(value = "/demo/numbers/{min}/{max}",produces ="application/json;charset=utf-8" )
    Map<String,Object> getNumbers(@PathVariable("min") int min,@PathVariable("max") int max);
}
