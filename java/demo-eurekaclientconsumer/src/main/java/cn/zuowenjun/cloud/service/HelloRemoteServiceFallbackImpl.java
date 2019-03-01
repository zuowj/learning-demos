package cn.zuowenjun.cloud.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Component
public class HelloRemoteServiceFallbackImpl implements HelloRemoteService{

    @Override
    public Object multiply(@PathVariable("a") int x, @PathVariable("b") int y) {
        Map<String,Object> result=new HashMap<>();
        result.put("from","multiply Fallback method");
        result.put("a",x);
        result.put("b",y);

        return  result;
    }
}