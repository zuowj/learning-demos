package cn.zuowenjun.cloud.service;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HystrixClientFallbackFactory implements FallbackFactory<HelloRemoteService> {

    public HelloRemoteService create(Throwable throwable) {
        //TODO:这里可以根据throwable的不同生成不同的HelloRemoteService的Fallback的实例
        return  new HelloRemoteService() {//这里是匿名实现接口，也可以用lambda表达式或具体的接口实现类，如：HelloRemoteServiceFallbackImpl

            @Override
            public Object multiply(int x, int y) {
                //TODO:这里可以根据throwable的不同执行不同的逻辑
                Map<String,Object> result=new HashMap<>();
                result.put("from","multiply FallbackFactory method");
                result.put("a",x);
                result.put("b",y);
                result.put("ex",throwable.getMessage());
                return  result;
            }
        };
    }
}
