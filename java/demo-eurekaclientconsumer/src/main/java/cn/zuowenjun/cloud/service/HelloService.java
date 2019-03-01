package cn.zuowenjun.cloud.service;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.helloServiceProvider}")
    private String  helloServiceName;

    @HystrixCommand(fallbackMethod = "multiplyFallback",
            commandProperties ={
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "3")
            } )
    public Object multiply(int a,int b){
        String url="http://"+ helloServiceName +"/multiply/" + a +"/" + b;
        return restTemplate.getForObject(url,String.class);

//        throw new RuntimeException("consumer exception");
    }

    private  Object multiplyFallback(int x,int y,Throwable e){

        //TODO:额外增加的Throwable e，以便可以根据throwable的不同执行不同的逻辑
        Map<String,Object> result=new HashMap<String, Object>();
        result.put("from","multiply Fallback method");
        result.put("a",x);
        result.put("b",y);
        result.put("ex",e.getMessage());

        return  result;

    }


}
