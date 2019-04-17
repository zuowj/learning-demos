package cn.zuowenjun.microservice.Remote;

import cn.zuowenjun.microservice.Service.DemoService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * DemoService远程调用接口，同时通过内部静态类实现该接口作为fallback，以省去单独定义fallback实现类文件
 */
//@FeignClient(name = "testservice",url="http://localhost:1008/testservice",fallback =DemoRemoteService.DemoRemoteServiceFallback.class )
@FeignClient(name = "zuulapigateway",fallback =DemoRemoteService.DemoRemoteServiceFallback.class )
public interface DemoRemoteService extends DemoService {

    /**
     * 内部静态类实现DemoService的fallback类
     */
    @Component
    public static class DemoRemoteServiceFallback implements DemoRemoteService{

        @Override
        public String getMessage(String name) {
            return "远程服务不可用，这是降级处理结果：Welcome,"+name;
        }

        @Override
        public Map<String, Object> getNumbers(int min, int max) {
            Map<String, Object> map=new HashMap<>();
            map.put("Error","远程服务不可用，这是降级处理结果");
            map.put("min",min);
            map.put("max",max);
            return map;
        }
    }
}
