package cn.zuowenjun.cloud.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/*
*  bug-refer https://blog.csdn.net/zlh313_01/article/details/80309144
*  bug-refer https://blog.csdn.net/alinyua/article/details/80070890
 */
//@FeignClient(name= "helloservice",fallback =HelloRemoteServiceFallbackImpl.class )
@FeignClient(name= "helloservice",fallbackFactory = HystrixClientFallbackFactory.class)
public interface HelloRemoteService {

    @RequestMapping("/multiply/{a}/{b}")
    Object  multiply(@PathVariable("a") int x, @PathVariable("b") int y);

}


